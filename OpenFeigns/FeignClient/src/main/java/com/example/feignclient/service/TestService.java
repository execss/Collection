package com.example.feignclient.service;

import com.example.feignclient.model.UserVO;
import com.example.feignclient.service.fallback.TestServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "server",fallbackFactory = TestServiceFallbackFactory.class, decode404 = true)
public interface TestService {

    @GetMapping("/t1")
    ResponseEntity<String> test1();

    @PostMapping("/t2")
    ResponseEntity<String> test2(@RequestBody String ss);

    @PostMapping("/t3")
    ResponseEntity<String> test3(@RequestBody UserVO userVO);

    @PostMapping(value = "t4" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> test4(@RequestBody MultipartFile file);


}
