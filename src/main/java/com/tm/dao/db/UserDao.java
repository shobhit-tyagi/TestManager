package com.tm.dao.db;

import com.tm.dao.entity.TmUserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<TmUserInfo, Long> {

	TmUserInfo findByUserId(String userId);
}
