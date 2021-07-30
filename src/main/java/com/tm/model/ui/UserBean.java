package com.tm.model.ui;

import java.io.Serializable;

import com.tm.model.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

@Dto
@EqualsAndHashCode(callSuper=false)
public @Data class UserBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 6051125450649862016L;

	@DtoField
	private long id;

	@DtoField
	private String userEmail;

	@DtoField
	private String userGroupId;

	@DtoField
	private String userId;

	@DtoField
	private byte[] userImage;

	@DtoField
	private String userName;

	@DtoField
	private String userPass;

	@DtoField
	private String userPhone;

	@DtoField
	private String userType;
}
