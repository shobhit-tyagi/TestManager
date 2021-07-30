package com.tm.web.controller;

import com.tm.model.ui.ProjectBean;
import com.tm.service.ProjectService;
import com.tm.util.exceptions.DtoConversionException;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tmProject")
public class ProjectController {
	
	private final ProjectService projectService;
	
	@RequestMapping(method = RequestMethod.GET, value="/getAllUserProjects")
	public List<ProjectBean> getAllUserProjects(@RequestParam("id") final long userId) throws InternalApplicationException {
		try {
			return projectService.getAllProjects(userId);
		} catch (final DtoConversionException e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/addProject")
	public ProjectBean addProject(@RequestBody final ProjectBean projectBean, @RequestParam("addDefaultModules") final boolean addDefaultModules) throws InternalApplicationException {
		try {
			return projectService.addProject(projectBean, addDefaultModules);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/editProject")
	public ProjectBean editProject(@RequestBody final ProjectBean projectBean) throws InternalApplicationException {
		try {
			return projectService.editProject(projectBean);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/disableProject")
	public @ResponseBody String disableProject(@RequestParam("id") final long id) {
		projectService.disableProject(id);
		return "Success";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/enableProject")
	public @ResponseBody String enableProject(@RequestParam("id") final long id) {
		projectService.enableProject(id);
		return "Success";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/deleteProject")
	public @ResponseBody String deleteProject(@RequestParam("id") final long id) {
		projectService.deleteProject(id);
		return "Success";
	}
}
