package com.example.user.restapi;

import com.example.user.mbg.entity.Role;
import com.example.user.mbg.entity.User;
import com.example.user.mbg.mapper.RoleMapper;
import com.example.user.mbg.mapper.RoleMenuMapper;
import com.example.user.mbg.mapper.UserMapper;
import com.example.user.model.vo.MenuVO;
import com.example.user.model.vo.UserVO;
import com.example.user.util.BeanCopyUtils;
import com.example.user.util.Jackson;
import com.example.user.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
public class LoginAuth {

    @Resource
    RoleMenuMapper roleMenuMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;


    @GetMapping("/users/current")
    public Result<UserVO> getLoginAppUser() {
        User user = userMapper.selectByPrimaryKey(1);
        log.info(Jackson.toString(user));
        UserVO userVO = BeanCopyUtils.copyObject(user, UserVO.class);
        userVO.setUserId(user.getOpenId());
        userVO.setAccountNonExpired(true);
        userVO.setAccountNonLocked(true);
        userVO.setCredentialsNonExpired(true);
        userVO.setDel(false);

        List<Role> rolesByUserId = roleMapper.findRolesByUserId(1);
        List<MenuVO> menusByRoleIds = roleMenuMapper.findMenusByRoleIds();
        userVO.setRoles(rolesByUserId);
        userVO.setPermissions(menusByRoleIds.stream().map(menu -> menu.getPath()).collect(Collectors.toSet()));
        log.info(Jackson.toString(userVO));

        return Result.succeed(userVO);
    }


    @GetMapping("/menus/current")
    public List<MenuVO> findMyMenu() {

        List<MenuVO> menus =roleMenuMapper.findMenuVOsByRoleCodes() ;
        log.info(Jackson.toString(menus));
        return treeBuilder(menus);
    }

    public static List<MenuVO> treeBuilder(List<MenuVO> menuVOS) {
        List<MenuVO> menus = new ArrayList<>();
        for (MenuVO menuVO : menuVOS) {
            if (menuVO.getParentId().equals(-1L)) {
                menus.add(menuVO);
            }
            for (MenuVO menu : menuVOS) {
                if (menu.getParentId().equals(menuVO.getId())) {
                    if (menuVO.getSubMenus() == null) {
                        menuVO.setSubMenus(new ArrayList<>());
                    }
                    menuVO.getSubMenus().add(menu);
                }
            }
        }
        return menus;
    }
}
