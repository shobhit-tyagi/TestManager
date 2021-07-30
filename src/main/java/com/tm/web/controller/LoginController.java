package com.tm.web.controller;

import com.tm.model.ui.UserBean;
import com.tm.service.UserService;
import com.tm.util.exceptions.InternalApplicationException;
import com.tm.util.exceptions.LoginValidationFailedException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/tmLogin")
public class LoginController {
	
	private final UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value="/validateLogin")
	public UserBean validateLogin(@RequestBody final UserBean userBean) {
		try {
			return userService.validateLogin(userBean);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
}
