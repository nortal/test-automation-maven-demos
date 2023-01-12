package com.nortal.test.feign.demo.api;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "catApi", url = "https://catfact.ninja")
public interface CatApi {

    @GetMapping("/fact")
    ResponseEntity<Fact> getFact();

    @GetMapping("/facts")
    ResponseEntity<FactsList> getFacts();

    @GetMapping("/breeds")
    ResponseEntity<BreedsList> getBreeds();

    @Data
    class Fact {
        private String fact;
        private Integer length;
    }

    @Data
    class FactsList {
        private List<Fact> data;
        private Integer current_page;
        private Integer last_page;
    }

    @Data
    class Breed {
        private String breed;
        private String country;
        private String origin;
        private String coat;
        private String pattern;
    }

    @Data
    class BreedsList {
        private List<Breed> data;
        private Integer current_page;
        private Integer last_page;
    }
}
