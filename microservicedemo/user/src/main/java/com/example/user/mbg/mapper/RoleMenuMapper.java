package com.example.user.mbg.mapper;

import com.example.user.mbg.entity.Menu;
import com.example.user.mbg.entity.RoleMenu;
import java.util.List;
import java.util.Set;

import com.example.user.model.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    int insert(RoleMenu record);

    List<RoleMenu> selectAll();
    List<Menu> findMenusByRoleCodes();

    List<MenuVO> findMenusByRoleIds();
    List<MenuVO> findMenuVOsByRoleCodes();
}