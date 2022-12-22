package com.nortal.test.feign.demo.api;

import com.nortal.test.feign.demo.configuration.TestDemoConfiguration;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "catApi", url = "https://catfact.ninja", configuration = TestDemoConfiguration.class)
public interface CatApi {

    @GetMapping("/fact")
    ResponseEntity<Fact> getFact();

    @Data
    class Fact {
        private String fact;
        private Integer length;
    }
}
