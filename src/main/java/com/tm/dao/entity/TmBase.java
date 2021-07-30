package com.tm.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public @Data class TmBase {

	@Column(name="VISIBLE")
	private boolean visible;

	@CreatedDate
	@Column(name="DT_CREATED")
	private Timestamp dtCreated;

	@LastModifiedDate
	@Column(name="DT_MODIFIED")
	private Timestamp dtModified;
}
