package com.tm.dao.db;

import com.tm.dao.entity.TmNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao extends CrudRepository<TmNotification, Long> {

	List<TmNotification> findByUserId(long userId);
}
