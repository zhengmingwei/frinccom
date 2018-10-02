package com.cfcp.incc.dao;

import com.cfcp.incc.entity.BaseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhyj on 2016/11/24.
 */

@Repository
public interface GenericDao<T extends BaseEntity> {
    T get(String id);
    int insert(T entity);
    int update(T entity);
    int delete(String id);
    double count(Map<String, Object> conditions);
    List<T> query(Map<String, Object> conditions);
}
