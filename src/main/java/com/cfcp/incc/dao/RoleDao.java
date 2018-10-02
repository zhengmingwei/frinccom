package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Role;

public interface RoleDao {
    int delete(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role get(String roleId);

    int updateSelective(Role record);

    int update(Role record);
}