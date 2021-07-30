package com.tm.service.impl;

import com.tm.dao.db.ModuleDao;
import com.tm.dao.db.ProjectDao;
import com.tm.dao.entity.TmModule;
import com.tm.dao.entity.TmProject;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.model.ui.ProjectBean;
import com.tm.service.ProjectService;
import com.tm.service.helper.ModuleHelper;
import com.tm.util.exceptions.DaoException;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl extends DtoAssemblerFacadeImpl<TmProject, ProjectBean> implements ProjectService {

	private final ProjectDao projectDao;
	private final ModuleDao moduleDao;
	
	@Override
	public List<ProjectBean> getAllProjects(final Long userId) throws DtoConversionException {
		final List<TmProject> projectEntities = projectDao.findByProjOwner(userId);
		final List<ProjectBean> projectBeanList = new ArrayList<ProjectBean>();
		for(final TmProject projectEntity : projectEntities) {
			projectBeanList.add(toBean(projectEntity));
		}
		
		return projectBeanList;
	}

	@Override
	public ProjectBean addProject(final ProjectBean projectBean, final boolean addDefaultModules) throws DtoConversionException, DaoException {
		TmProject projectEntity = toEntity(projectBean);
		if(addDefaultModules) {
			projectEntity = projectDao.save(projectEntity);
			for(final TmModule moduleEntity : ModuleHelper.createDefaultModuleEntities(projectEntity.getId())) {
				moduleEntity.setProjId(projectEntity.getId());
				moduleDao.save(moduleEntity);
			}
		} else {
			projectDao.save(projectEntity);
		}
		return toBean(projectEntity);
	}
	
	@Override
	public ProjectBean editProject(final ProjectBean projectBean) throws DtoConversionException {
		final TmProject projectEntity = projectDao.findById(projectBean.getId()).get();
		projectEntity.setProjName(projectBean.getProjName());
		projectEntity.setProjDesc(projectBean.getProjDesc());
		projectDao.save(projectEntity);
		return toBean(projectEntity);
	}
	
	@Override
	public void disableProject(final long id) {
		final TmProject projectEntity = projectDao.findById(id).get();
		projectEntity.setVisible(false);
		projectDao.save(projectEntity);
	}
	
	@Override
	public void enableProject(final long id) {
		final TmProject projectEntity = projectDao.findById(id).get();
		projectEntity.setVisible(true);
		projectDao.save(projectEntity);
	}

	@Override
	public void deleteProject(final long id) {
		final TmProject projectEntity = projectDao.findById(id).get();
		projectDao.save(projectEntity);
	}
}
