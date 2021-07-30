package com.tm.service;

import com.tm.dao.entity.TmUserInfo;
import com.tm.dao.entity.TmUserProject;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.UserBean;
import com.tm.util.exceptions.CipherException;
import com.tm.util.exceptions.DtoConversionException;
import com.tm.util.exceptions.FileLoadException;
import com.tm.util.exceptions.LoginValidationFailedException;

import java.util.List;
import java.util.Map;

public interface UserService extends DtoAssemblerFacade<TmUserInfo, UserBean> {

	UserBean validateLogin(UserBean userBean) throws LoginValidationFailedException, DtoConversionException;

	UserBean updateUserProfile(UserBean userBean) throws DtoConversionException;

	String changePassword(long id, String password) throws FileLoadException, CipherException;
	
	String requestAdminPrivilege(long userId);
	
	Map<String, String> getUserGroups();

	List<UserBean> getAllUsers() throws DtoConversionException;
	
	UserBean getUserByUserId(String userId) throws DtoConversionException;

	List<UserBean> getUsersFromUserProjectList(List<TmUserProject> userProjList) throws DtoConversionException;
}
