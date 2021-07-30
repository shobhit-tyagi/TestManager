package com.tm.service;

import com.tm.dao.entity.TmModule;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.ModuleBean;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;

public interface ModuleService extends DtoAssemblerFacade<TmModule, ModuleBean> {

	List<ModuleBean> getProjectModules(long projectId) throws DtoConversionException;

	ModuleBean addModuleToProject(ModuleBean moduleBean) throws DtoConversionException;

	void deleteModule(long id);

}
