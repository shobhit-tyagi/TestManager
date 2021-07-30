package com.tm.model.ui;

import java.io.Serializable;

import com.tm.model.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

@Dto
@EqualsAndHashCode(callSuper=false)
public @Data class ReleaseBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@DtoField
	private long id;

	@DtoField
	private long modId;

	@DtoField
	private String relDesc;

	@DtoField
	private String relName;

	@DtoField
	private String relStatus;

	@DtoField
	private long userId;
	
	private String userIdString;
}