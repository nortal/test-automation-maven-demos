package com.nortal.test.demo.api;

import com.nortal.test.demo.dto.Todo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "todoApi", path = "/api/v1")
public interface TodoApiService {

    @GetMapping("todo")
    ResponseEntity<List<Todo>> getTodoList();

    @GetMapping("todo/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable("id") Long todoId);

    @PostMapping("todo")
    ResponseEntity<Todo> postTodo(@RequestBody Todo todo);

    @DeleteMapping("todo/{id}")
    ResponseEntity<Object> deleteTodo(@PathVariable("id") Long todoId);
}
