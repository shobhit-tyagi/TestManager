package com.tm.web.controller;

import com.tm.model.ui.UserBean;
import com.tm.service.UserProjectService;
import com.tm.util.exceptions.DtoConversionException;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tmUserProject")
public class UserProjectController {

	private final UserProjectService userProjectService;

	@RequestMapping(method = RequestMethod.GET, value="/getProjectTeam")
	public List<UserBean> getProjectTeam(@RequestParam("id") final long projectId) throws InternalApplicationException {
		try {
			return userProjectService.getProjectTeam(projectId);
		} catch (final DtoConversionException e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/addUserToProject")
	public @ResponseBody String addUserToProject(@RequestParam("userId") final long userId, @RequestParam("projectId") final long projectId) {
		userProjectService.addUserToProject(userId, projectId);
		return "";
	}
}
