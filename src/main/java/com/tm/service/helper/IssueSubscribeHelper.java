package com.tm.service.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tm.dao.entity.TmIssueSubscribe;

public class IssueSubscribeHelper {

	public static List<TmIssueSubscribe> getIssueSubscribeEntity(final long userIdOwner, final long userIdAssignee) {
		final TmIssueSubscribe issueSubscribeEntityOwner = new TmIssueSubscribe();
		issueSubscribeEntityOwner.setSubCreated(new Timestamp(new Date().getTime()));
		issueSubscribeEntityOwner.setUserId(userIdOwner);
		
		final TmIssueSubscribe issueSubscribeEntityAssignee = new TmIssueSubscribe();
		issueSubscribeEntityAssignee.setSubCreated(new Timestamp(new Date().getTime()));
		issueSubscribeEntityAssignee.setUserId(userIdAssignee);
		
		final List<TmIssueSubscribe> issueSubscribeEntityList = new ArrayList<TmIssueSubscribe>();
		issueSubscribeEntityList.add(issueSubscribeEntityOwner);
		issueSubscribeEntityList.add(issueSubscribeEntityAssignee);
		
		return issueSubscribeEntityList;
	}
}
