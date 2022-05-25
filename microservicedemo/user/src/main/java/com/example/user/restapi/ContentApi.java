package com.example.user.restapi;

import com.example.user.model.vo.UserVO;
import com.example.user.service.UserService;
import com.example.user.util.PageResult;
import com.example.user.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ContentApi {
    @Autowired
    private UserService userService;

    /**
     * 用户查询
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "用户查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/users")
    public PageResult<?> findUsers(@RequestParam Map<String, Object> params) {
        List<UserVO> userVOList = userService.findUsers();
        return PageResult.<UserVO>builder()
                .data(userVOList)
                .code(0)
                .count(userVOList.size())
                .build();
    }
}
