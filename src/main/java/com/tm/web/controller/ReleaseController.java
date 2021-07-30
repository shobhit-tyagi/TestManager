package com.tm.web.controller;

import com.tm.model.ui.ReleaseBean;
import com.tm.service.ReleaseService;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tmRelease")
public class ReleaseController {
	
	private final ReleaseService releaseService;
	
	@RequestMapping(method = RequestMethod.GET, value="/getReleasesByModule")
	public List<ReleaseBean> getReleasesByModule(@RequestParam("moduleId") final long moduleId) throws InternalApplicationException {
		try {
			return releaseService.getReleasesByModule(moduleId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/addReleaseToModule")
	public ReleaseBean addIssueToModule(@RequestBody final ReleaseBean releaseBean) throws InternalApplicationException {
		try {
			return releaseService.addReleaseToModule(releaseBean);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
}
