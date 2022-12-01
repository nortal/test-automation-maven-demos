package com.nortal.test.demo.api;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "catApi", url = "https://catfact.ninja")
public interface CatApi {

    @GetMapping("/fact")
    ResponseEntity<Fact> getFact();

    @Data
    class Fact {
        private String fact;
        private Integer length;
    }
}
