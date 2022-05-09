package com.example.feignclient.service.fallback;

import com.example.feignclient.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
@Slf4j
public class TestServiceFallbackFactory implements FallbackFactory<TestService> {
    @Override
    public TestService create(Throwable cause) {
        return null;
    }
}
