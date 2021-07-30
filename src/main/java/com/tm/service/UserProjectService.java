package com.tm.service;

import com.tm.dao.entity.TmUserProject;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.UserBean;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;

public interface UserProjectService extends DtoAssemblerFacade<TmUserProject, UserBean> {

	List<UserBean> getProjectTeam(long projectId) throws DtoConversionException;

	void addUserToProject(long userId, long projectId);

}
