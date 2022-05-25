package com.example.user;

import com.example.user.mbg.entity.*;
import com.example.user.mbg.mapper.RoleMapper;
import com.example.user.mbg.mapper.RoleMenuMapper;
import com.example.user.mbg.mapper.RoleUserMapper;
import com.example.user.mbg.mapper.UserMapper;
import com.example.user.model.dto.MenuVODTO;
import com.example.user.model.dto.RoleDTO;
import com.example.user.model.vo.MenuVO;
import com.example.user.model.vo.LoginUserVO;
import com.example.user.model.vo.UserVO;
import com.example.user.util.BeanCopyUtils;
import com.example.user.util.Jackson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class UserApplicationTests {

    @Resource
    RoleMenuMapper roleMenuMapper;

    @Test
    void contextLoads() {
//        List<RoleMenu> roleMenuList = roleMenuMapper.selectAll();
//        log.info(Jackson.toString(roleMenuList));
//
//        log.info(Jackson.toString(roleMenuMapper.findMenusByRoleCodes()));

        List<MenuVO> menuVOsByRoleCodes = roleMenuMapper.findMenuVOsByRoleCodes();
        log.info(Jackson.toString(menuVOsByRoleCodes));
        for (MenuVO menuVOsByRoleCode : menuVOsByRoleCodes) {
            System.out.println(System.identityHashCode(menuVOsByRoleCode));
        }


        List<MenuVO> menus = new ArrayList<>();
        for (MenuVO menuVO : menuVOsByRoleCodes) {
            if (menuVO.getParentId().equals(-1L)) {
                menus.add(menuVO);
            }
            for (MenuVO menu : menuVOsByRoleCodes) {
                if (menu.getParentId().equals(menuVO.getId())) {
                    if (menuVO.getSubMenus() == null) {
                        menuVO.setSubMenus(new ArrayList<>());
                    }
                    menuVO.getSubMenus().add(menu);
                }
            }
        }
        System.out.println("\n\n\n");
        log.info(Jackson.toString(menus));
        for (MenuVO menu : menus) {
            System.out.println(System.identityHashCode(menu));
        }

        List<MenuVO> menuVOList = menuVOsByRoleCodes.stream().map(menuVO -> BeanCopyUtils.copyObject(menuVO, MenuVODTO.class)).map(menuVODTO -> BeanCopyUtils.copyObject(menuVODTO, MenuVO.class)).collect(Collectors.toList());

        List<MenuVO> menuVOList1 = menuVOList.stream()
                .filter(menuVO -> menuVO.getParentId().equals(-1L))
                .peek(menuVO -> menuVO.setSubMenus(menuVOList.stream().filter(menuVO1 -> menuVO1.getParentId().equals(menuVO.getId())).collect(Collectors.toList()))).collect(Collectors.toList());
        System.out.println("\n\n\n");
        log.info(Jackson.toString(menuVOList1));
        for (MenuVO menuVO : menuVOList1) {
            System.out.println(System.identityHashCode(menuVO));
        }

    }

    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;


    @Test
    void t1() {

        User user = userMapper.selectByPrimaryKey(1);
        log.info(Jackson.toString(user));
        LoginUserVO loginUserVO = BeanCopyUtils.copyObject(user, LoginUserVO.class);
        loginUserVO.setUserId(user.getOpenId());
        loginUserVO.setAccountNonExpired(true);
        loginUserVO.setAccountNonLocked(true);
        loginUserVO.setCredentialsNonExpired(true);
        loginUserVO.setDel(false);

        List<Role> rolesByUserId = roleMapper.findRolesByUserId(1);
        List<MenuVO> menusByRoleIds = roleMenuMapper.findMenusByRoleIds();
        loginUserVO.setRoles(rolesByUserId);
        loginUserVO.setPermissions(menusByRoleIds.stream().map(menu -> menu.getPath()).collect(Collectors.toSet()));
        log.info(Jackson.toString(loginUserVO));

    }

    @Resource
    RoleUserMapper roleUserMapper;

    @Test
    void t2() {
        List<User> userList = userMapper.selectAll();
        List<RoleUser> roleUserList = roleUserMapper.selectAll();
        List<Role> roleList = roleMapper.selectAll();

        List<User> userList1 = userList.stream().filter(user -> !user.getIsDel()).collect(Collectors.toList());
        List<RoleUser> roleUserList1 = roleUserList.stream()
                .filter(roleUser -> userList1.stream()
                        .map(User::getId)
                        .collect(Collectors.toList())
                        .contains(roleUser.getUserId().longValue()))
                .collect(Collectors.toList());

        List<Role> roleList1 = roleList.stream().filter(role -> roleUserList1.stream().map(RoleUser::getRoleId).collect(Collectors.toList()).contains(role.getId())).collect(Collectors.toList());

        log.info(Jackson.toString(roleUserList1));
        log.info(Jackson.toString(roleList1));

        List<RoleDTO> roleDTOList = roleUserList1.stream().map(roleUser -> {
                    RoleDTO roleDTO = BeanCopyUtils.copyObject(
                            roleList1.stream()
                                    .filter(role -> role.getId().equals(roleUser.getRoleId()))
                                    .findFirst().get(), RoleDTO.class);
                    roleDTO.setUserId(roleUser.getUserId().longValue());

                    return roleDTO;
                }
        ).collect(Collectors.toList());
        List<UserVO> userVOList = userList1.stream().map(user ->
                {
                    UserVO userVO = BeanCopyUtils.copyObject(user, UserVO.class);
                    userVO.setRoleDTOs(roleDTOList.stream().filter(roleDTO -> roleDTO.getUserId().equals(user.getId())).collect(Collectors.toList()));
                    return userVO;
                }
        ).collect(Collectors.toList());
        log.info(Jackson.toString(userList));
        log.info(Jackson.toString(roleDTOList));
        log.info(Jackson.toString(userVOList));
    }


}
