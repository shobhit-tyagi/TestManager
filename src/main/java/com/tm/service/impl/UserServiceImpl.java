package com.tm.service.impl;

import com.tm.dao.entity.TmUserInfo;
import com.tm.dao.entity.TmUserProject;
import com.tm.dao.db.UserDao;
import com.tm.model.ui.UserBean;
import com.tm.service.UserService;
import com.tm.model.assembler.DtoAssemblerFacadeImpl;
import com.tm.util.cipher.CipherUtils;
import com.tm.util.exceptions.CipherException;
import com.tm.util.exceptions.DtoConversionException;
import com.tm.util.exceptions.FileLoadException;
import com.tm.util.exceptions.LoginValidationFailedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserServiceImpl extends DtoAssemblerFacadeImpl<TmUserInfo, UserBean> implements UserService {

	private final UserDao userDao;

	@Override
	public UserBean validateLogin(final UserBean userBean) throws LoginValidationFailedException, DtoConversionException {
		final UserBean userBeanRet = getUserByUserId(userBean.getUserId());
		if(userBeanRet == null) {
			throw new LoginValidationFailedException("Invalid Details");
		} else {
			String password = null;
			try {
				password = CipherUtils.decrypt(userBeanRet.getUserPass());
			} catch (final Exception e) {
				throw new LoginValidationFailedException("Login credentials could not be validated at this moment", e);
			}
			if(!password.equals(userBean.getUserPass())) {
				throw new LoginValidationFailedException("Invalid Details");
			}
		}
		return userBeanRet;
	}

	@Override
	public UserBean updateUserProfile(final UserBean userBean) throws DtoConversionException {
		final TmUserInfo userEntity = userDao.findById(userBean.getId()).get();
		updateUserEntity(userEntity, userBean);
		return toBean(userDao.save(userEntity));
	}
	
	private void updateUserEntity(final TmUserInfo userEntity, final UserBean userBean) {
		userEntity.setUserEmail(userBean.getUserEmail());
		userEntity.setUserPhone(userBean.getUserPhone());
		userEntity.setUserPass(userBean.getUserPass());
	}

	@Override
	public String changePassword(final long id, final String password) throws FileLoadException, CipherException {
		final TmUserInfo userEntity = userDao.findById(id).get();
		final String encryptedPassword = CipherUtils.encrypt(password);
		userEntity.setUserPass(encryptedPassword);
		userDao.save(userEntity);
		return encryptedPassword;
	}

	@Override
	public String requestAdminPrivilege(final long userId) {
		return null;
	}

	@Override
	public Map<String, String> getUserGroups() {
		final List<TmUserInfo> userEntityList = StreamSupport.stream(userDao.findAll().spliterator(), false)
				.collect(Collectors.toList());
		final Map<String, String> userGroups = new HashMap<String, String>();
		for(final TmUserInfo userEntity : userEntityList) {
			userGroups.put(userEntity.getUserId(), userEntity.getUserGroupId());
		}
		return userGroups;
	}

	@Override
	public List<UserBean> getAllUsers() throws DtoConversionException {
		final List<TmUserInfo> userEntityList = StreamSupport.stream(userDao.findAll().spliterator(), false)
				.collect(Collectors.toList());
		final List<UserBean> userBeanList = new ArrayList<UserBean>();
		for(final TmUserInfo userEntity : userEntityList) {
			userBeanList.add(toBean(userEntity));
		}
		return userBeanList;
	}

	@Override
	public List<UserBean> getUsersFromUserProjectList(final List<TmUserProject> userProjList) throws DtoConversionException {
		final List<UserBean> userList = new ArrayList<UserBean>();
		for(final TmUserProject userProj : userProjList) {
			userList.add(toBean(userDao.findById(userProj.getUserId()).get()));
		}
		return userList;
	}

	@Override
	public UserBean getUserByUserId(final String userId)
			throws DtoConversionException {
		return toBean(userDao.findByUserId(userId));
	}
}
