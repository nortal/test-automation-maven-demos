package com.nortal.test.demo.configuration;

import com.nortal.test.demo.api.TodoApiService;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {TodoApiService.class})
public class TestDemoConfiguration {
}
