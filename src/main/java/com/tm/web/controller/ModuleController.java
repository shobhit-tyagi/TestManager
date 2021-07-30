package com.tm.web.controller;

import com.tm.model.ui.ModuleBean;
import com.tm.service.ModuleService;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tmModule")
public class ModuleController {

	private final ModuleService moduleService;
	
	@RequestMapping(method = RequestMethod.GET, value="/getProjectModules")
	public List<ModuleBean> getProjectModules(@RequestParam("id") final long projectId) throws InternalApplicationException {
		try {
			return moduleService.getProjectModules(projectId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/addModuleToProject")
	public ModuleBean addModuleToProject(@RequestBody final ModuleBean moduleBean) throws InternalApplicationException {
		try {
			return moduleService.addModuleToProject(moduleBean);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/deleteModule")
	public void deleteModule(@RequestParam("id") final long id) {
		moduleService.deleteModule(id);
	}
}
