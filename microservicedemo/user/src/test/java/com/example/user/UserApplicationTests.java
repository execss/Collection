package com.example.user;

import com.example.user.mbg.entity.Menu;
import com.example.user.mbg.entity.Role;
import com.example.user.mbg.entity.RoleMenu;
import com.example.user.mbg.entity.User;
import com.example.user.mbg.mapper.RoleMapper;
import com.example.user.mbg.mapper.RoleMenuMapper;
import com.example.user.mbg.mapper.UserMapper;
import com.example.user.model.dto.MenuVODTO;
import com.example.user.model.vo.MenuVO;
import com.example.user.model.vo.MenuVOT;
import com.example.user.model.vo.UserVO;
import com.example.user.util.BeanCopyUtils;
import com.example.user.util.Jackson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        List<MenuVO> menuVOList = menuVOsByRoleCodes.stream().map(menuVO -> BeanCopyUtils.copyObject(menuVO, MenuVODTO.class)).map(menuVODTO -> BeanCopyUtils.copyObject(menuVODTO,MenuVO.class)).collect(Collectors.toList());

        List<MenuVO> menuVOList1 = menuVOsByRoleCodes.stream()
                .filter(menuVO -> menuVO.getParentId().equals(-1L))
                .peek(menuVO -> menuVO.setSubMenus(menuVOsByRoleCodes.stream().filter(menuVO1 -> menuVO1.getParentId().equals(menuVO.getId())).collect(Collectors.toList()))).collect(Collectors.toList());
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
    void t1(){

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

    }

}
