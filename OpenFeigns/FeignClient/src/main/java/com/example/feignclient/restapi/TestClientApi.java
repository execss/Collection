package com.example.feignclient.restapi;

import com.example.feignclient.model.UserVO;
import com.example.feignclient.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
public class TestClientApi {

    @Resource
    TestService testService;

    @GetMapping("/tc")
    ResponseEntity<?> t1() {

        log.info("tc-ok");
        log.info(testService.test1().toString());
        log.info(testService.test2("t2 ok").toString());
        log.info(testService.test3(new UserVO("admins", "123456789")).toString());
        return ResponseEntity.ok("");
    }


    @PostMapping("/t")
    ResponseEntity<?> t2(@RequestBody MultipartFile files) throws IOException {

        InputStream inputStream = files.getInputStream();


        log.info(files.getOriginalFilename());
        log.info(testService.test4(files).toString());


        return ResponseEntity.ok("");
    }
}
