package com.tm.dao.db;

import com.tm.dao.entity.TmNotificationVariable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationVariableDao extends CrudRepository<TmNotificationVariable, Long> {

	List<TmNotificationVariable> findByNotId(long notId);
}
