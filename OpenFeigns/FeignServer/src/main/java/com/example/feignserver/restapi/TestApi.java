package com.example.feignserver.restapi;

import com.example.feignserver.model.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping()
public class TestApi {

    @GetMapping("/t1")
    ResponseEntity<String> test1(){

        log.info("-----");
        return ResponseEntity.ok("ok test1");
    }

    @PostMapping("/t2")
    ResponseEntity<String> test2(@RequestBody String ss){

        log.info(ss);
        log.info("-----");
        return ResponseEntity.ok("ok test2");
    }

    @PostMapping("t3")
    ResponseEntity<String> test3(@RequestBody UserVO userVO){

        log.info(userVO.toString());
        log.info("-----");
        return ResponseEntity.ok("ok test3"+userVO);
    }

    @PostMapping("t4")
    ResponseEntity<String> test4(@RequestBody MultipartFile file){

        log.info(file.getName()+file.getContentType());
        log.info("-----");
        return ResponseEntity.ok("ok test4"+file.getOriginalFilename());
    }

}
