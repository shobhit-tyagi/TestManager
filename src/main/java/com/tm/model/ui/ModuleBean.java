package com.tm.model.ui;

import java.io.Serializable;

import com.tm.model.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

@Dto
@EqualsAndHashCode(callSuper=false)
public @Data class ModuleBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@DtoField
	private long id;

	@DtoField
	private String modDesc;

	@DtoField
	private String modName;

	@DtoField
	private String modStatus;

	@DtoField
	private long projId;
}