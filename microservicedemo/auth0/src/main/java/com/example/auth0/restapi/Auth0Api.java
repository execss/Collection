package com.example.auth0.restapi;

import com.example.auth0.model.Result;
import com.example.auth0.model.Token;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import sun.security.util.SecurityConstants;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Auth0Api {

    @GetMapping("/validata/code/{deviceId}")
    public void getCode(@PathVariable String deviceId, HttpServletResponse response) throws IOException {
        Assert.notNull(deviceId, "机器码不能为空");
        // 设置请求头为输出图片类型
        CaptchaUtil.setHeader(response);
        // 三个参数分别为宽、高、位数
        GifCaptcha gifCaptcha = new GifCaptcha(100, 35, 4);
        // 设置类型：字母数字混合
        gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        // 保存验证码
        // 输出图片流
        gifCaptcha.out(response.getOutputStream());
    }

    @ResponseBody
    @PostMapping("/oauth/token")
    public Result<?> getOuthToken() {

        return Result.builder()
                .resp_code(0)
                .resp_msg("")
                .datas(
                        Token.builder()
                                .access_token("3b74770d-8aca-42b3-9b56-d16349fe019f")
                                .token_type("bearer")
                                .refresh_token("486618b9-a37b-495e-88c3-9b6d82013971")
                                .expires_in(3599).scope("app").build()
                )
                .build();
    }


}
