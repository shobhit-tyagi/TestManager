package com.tm.dao.db;

import com.tm.dao.entity.TmCalendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarDao extends CrudRepository<TmCalendar, Long> {

	List<TmCalendar> findByUserId(long userId);
}
