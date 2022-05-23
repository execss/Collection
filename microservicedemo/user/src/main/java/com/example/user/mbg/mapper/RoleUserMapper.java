package com.example.user.mbg.mapper;

import com.example.user.mbg.entity.RoleUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleUserMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int insert(RoleUser record);

    List<RoleUser> selectAll();
}