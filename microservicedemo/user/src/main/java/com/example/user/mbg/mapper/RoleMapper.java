package com.example.user.mbg.mapper;

import com.example.user.mbg.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}