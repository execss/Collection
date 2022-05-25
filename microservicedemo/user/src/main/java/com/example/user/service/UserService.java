package com.example.user.service;

import com.example.user.model.vo.UserVO;

import java.util.List;

public interface UserService {
    List<UserVO> findUsers();
}
