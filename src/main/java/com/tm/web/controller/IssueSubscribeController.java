package com.tm.web.controller;

import com.tm.model.ui.IssueSubscribeBean;
import com.tm.service.IssueSubscribeService;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@AllArgsConstructor
@RequestMapping("/tmSubscribeIssue")
public class IssueSubscribeController {
	
	private final IssueSubscribeService issueSubscribeService;
	
	@RequestMapping(method = RequestMethod.POST, value="/addSubscription")
	public IssueSubscribeBean addSubscription(@RequestParam("userId") final long userId,
											  @RequestParam("issueId") final long issueId) throws InternalApplicationException {
		try {
			return issueSubscribeService.addSubscription(userId, issueId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/removeSubscription")
	public void removeSubscription(@RequestParam("issueSubscribeId") final long id) {
		issueSubscribeService.removeSubscription(id);
	}
}
