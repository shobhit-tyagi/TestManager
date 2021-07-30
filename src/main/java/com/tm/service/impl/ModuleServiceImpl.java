package com.tm.service.impl;

import com.tm.dao.entity.TmModule;
import com.tm.dao.db.ModuleDao;
import com.tm.model.ui.ModuleBean;
import com.tm.model.ui.ModuleStatus;
import com.tm.service.ModuleService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ModuleServiceImpl extends DtoAssemblerFacadeImpl<TmModule, ModuleBean> implements ModuleService {

	private final ModuleDao moduleDao;

	@Override
	public List<ModuleBean> getProjectModules(final long projectId) throws DtoConversionException {
		final List<TmModule> moduleEntityList = moduleDao.findByProjId(projectId);
		final List<ModuleBean> moduleBeanList = new ArrayList<ModuleBean>();
		for(final TmModule moduleEntity : moduleEntityList) {
			moduleBeanList.add(toBean(moduleEntity));
		}
		return moduleBeanList;
	}

	@Override
	public ModuleBean addModuleToProject(final ModuleBean moduleBean) throws DtoConversionException {
		final TmModule moduleEntity = toEntity(moduleBean);
		moduleEntity.setModStatus(ModuleStatus.STARTED.toString());
		return toBean(moduleDao.save(moduleEntity));
	}

	@Override
	public void deleteModule(final long id) {
		moduleDao.deleteById(id);
	}
}
