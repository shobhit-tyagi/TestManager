package com.tm.model.ui;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tm.model.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

@Dto
@EqualsAndHashCode(callSuper=false)
public @Data class IssueBean extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DtoField
	private long id;

	@DtoField
	private String issDesc;

	@DtoField
	private String issName;

	@DtoField
	private String issPriority;

	@DtoField
	private String issStatus;

	@DtoField
	private long modId;

	@DtoField
	private long userId;
	
	private String userIdString;
	
	@DtoField
	private long issOwner;
	
	private String issOwnerString;
	
	private List<IssueCommentBean> issComments;
	
	private List<IssueAttachmentBean> issAttachments;
	
	private List<IssueHistoryBean> issHistory;
	
	private List<IssueSubscribeBean> issSubscribe;
	
	private Map<String, Integer> issStatusCoordinates;
}
