package com.tm.service;

import com.tm.dao.entity.TmIssueAttachment;
import com.tm.model.assembler.DtoAssemblerFacade;
import com.tm.model.ui.IssueAttachmentBean;
import com.tm.util.exceptions.DtoConversionException;

import java.util.List;


public interface IssueAttachmentService extends DtoAssemblerFacade<TmIssueAttachment, IssueAttachmentBean> {
	
	List<IssueAttachmentBean> getIssueAttachments(long issueId) throws DtoConversionException;
}
