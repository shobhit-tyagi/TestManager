package com.tm.model.ui;

import java.io.Serializable;
import java.sql.Blob;

import com.tm.model.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

@Dto
@EqualsAndHashCode(callSuper=false)
public @Data class IssueAttachmentBean extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@DtoField
	private long id;

	@DtoField
	private Blob attContent;

	@DtoField
	private long issId;

	@DtoField
	private long userId;
	
	private String userIdString;
}
