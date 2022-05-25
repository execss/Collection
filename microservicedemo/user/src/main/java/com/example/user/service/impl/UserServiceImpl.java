package com.example.user.service.impl;

import com.example.user.mbg.entity.Role;
import com.example.user.mbg.entity.RoleUser;
import com.example.user.mbg.entity.User;
import com.example.user.mbg.mapper.RoleMapper;
import com.example.user.mbg.mapper.RoleUserMapper;
import com.example.user.mbg.mapper.UserMapper;
import com.example.user.model.dto.RoleDTO;
import com.example.user.model.vo.UserVO;
import com.example.user.service.UserService;
import com.example.user.util.BeanCopyUtils;
import com.example.user.util.Jackson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    RoleUserMapper roleUserMapper;
    @Resource
    RoleMapper roleMapper;


    @Override
    public List<UserVO> findUsers() {
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

//        log.info(Jackson.toString(roleUserList1));
//        log.info(Jackson.toString(roleList1));

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
        return userVOList;
    }
}
