package com.tm.web.controller;

import com.tm.model.ui.IssueBean;
import com.tm.model.ui.IssueHistoryBean;
import com.tm.service.IssueService;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tmIssue")
public class IssueController {
	
	private final IssueService issueService;
	
	@RequestMapping(method = RequestMethod.GET, value="/getIssuesByModule")
	public List<IssueBean> getIssuesByModule(@RequestParam("moduleId") final long moduleId) throws InternalApplicationException {
		try {
			return issueService.getIssuesByModule(moduleId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/addIssueToModule")
	public IssueBean addIssueToModule(@RequestBody final IssueBean issueBean) throws InternalApplicationException {
		try {
			return issueService.addIssueToModule(issueBean);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/acceptIssue")
	public IssueHistoryBean acceptIssue(@RequestParam("id") final long issueId) throws InternalApplicationException {
		try {
			return issueService.acceptIssue(issueId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/rejectIssue")
	public IssueHistoryBean rejectIssue(@RequestParam("id") final long issueId) throws InternalApplicationException {
		try {
			return issueService.rejectIssue(issueId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/reAssignIssue")
	public IssueHistoryBean reAssignIssue(@RequestParam("id") final long issueId, @RequestParam("userId") final String userId) throws InternalApplicationException {
		try {
			return issueService.rejectIssue(issueId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/completeIssue")
	public IssueHistoryBean completeIssue(@RequestParam("id") final long issueId) throws InternalApplicationException {
		try {
			return issueService.completeIssue(issueId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/reOpenIssue")
	public IssueHistoryBean reOpenIssue(@RequestParam("id") final long issueId) throws InternalApplicationException {
		try {
			return issueService.reOpenIssue(issueId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/fixIssue")
	public IssueHistoryBean fixIssue(@RequestParam("id") final long issueId) throws InternalApplicationException {
		try {
			return issueService.fixIssue(issueId);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
}
