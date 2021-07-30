package com.tm.web.controller;

import com.tm.model.ui.UserBean;
import com.tm.service.UserService;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tmUser")
public class UserController {
	
	private final UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value="/updateUserProfile")
	public UserBean updateUserProfile(@RequestBody final UserBean userBean) throws InternalApplicationException {
		try {
			return userService.updateUserProfile(userBean);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getAllUsers")
	public List<UserBean> getAllUsers() throws InternalApplicationException {
		try {
			return userService.getAllUsers();
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/changePassword")
	public @ResponseBody String changePassword(@RequestParam("password") final String password, @RequestParam("id") final long userId) throws InternalApplicationException {
		try {
			return userService.changePassword(userId, password);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/requestAdminPrivilege")
	public @ResponseBody String requestAdminPrivilege(@RequestParam("id") final long userId) throws InternalApplicationException {
		try {
			return userService.requestAdminPrivilege(userId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
}
