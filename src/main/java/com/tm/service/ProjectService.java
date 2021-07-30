package com.tm.service;

import com.tm.dao.entity.TmProject;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.ProjectBean;
import com.tm.util.exceptions.DaoException;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;

public interface ProjectService extends DtoAssemblerFacade<TmProject, ProjectBean> {

	List<ProjectBean> getAllProjects(Long userId) throws DtoConversionException;

	ProjectBean addProject(ProjectBean projectBean, boolean addDefaultModules) throws DtoConversionException, DaoException;

	void disableProject(long id);

	void enableProject(long id);
	
	void deleteProject(long id);

	ProjectBean editProject(ProjectBean projectBean) throws DtoConversionException;
}
