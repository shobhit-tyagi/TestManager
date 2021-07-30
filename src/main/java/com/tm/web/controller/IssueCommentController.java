package com.tm.web.controller;

import com.tm.model.ui.IssueCommentBean;
import com.tm.service.IssueCommentService;
import com.tm.util.exceptions.InternalApplicationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@AllArgsConstructor
@RequestMapping("/tmCommentIssue")
public class IssueCommentController {
	
	private final IssueCommentService issueCommentService;
	
	@RequestMapping(method = RequestMethod.POST, value="/addCommentToIssue")
	public IssueCommentBean addCommentToIssue(@RequestBody final IssueCommentBean issueCommentBean) throws InternalApplicationException {
		try {
			return issueCommentService.addCommentToIssue(issueCommentBean);
		} catch(final Exception e) {
			throw new InternalApplicationException("Something went wrong with the application", e);
		}
	}
}
