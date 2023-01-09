package com.nortal.test.demo.glue.provider;

import com.nortal.test.demo.dto.Todo;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TodoProvider {
    private static final String DESCRIPTION = "test";
    private static final Date DEADLINE = new Date();
    private static final boolean IS_COMPLETED = false;

    public Todo getTodo() {
        return Todo.builder()
                .description(DESCRIPTION)
                .deadline(DEADLINE)
                .isCompleted(IS_COMPLETED)
                .build();
    }
}
