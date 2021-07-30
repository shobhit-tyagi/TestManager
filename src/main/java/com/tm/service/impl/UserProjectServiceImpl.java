package com.tm.service.impl;

import com.tm.dao.db.UserProjectDao;
import com.tm.dao.entity.TmUserProject;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.model.ui.UserBean;
import com.tm.service.UserProjectService;
import com.tm.service.UserService;
import com.tm.util.exceptions.DtoConversionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserProjectServiceImpl extends DtoAssemblerFacadeImpl<TmUserProject, UserBean> implements UserProjectService {

	private final UserProjectDao userProjectDao;
	private final UserService userService;

	@Override
	public List<UserBean> getProjectTeam(final long projectId) throws DtoConversionException {
		final List<TmUserProject> userProjList = userProjectDao.findByProjId(projectId);
		return userService.getUsersFromUserProjectList(userProjList);
	}

	@Override
	public void addUserToProject(final long userId, final long projectId) {
		final TmUserProject userProject = new TmUserProject();
		userProject.setProjId(projectId);
		userProject.setUserId(userId);
		userProjectDao.save(userProject);
	}
}
