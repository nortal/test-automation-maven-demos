package com.nortal.test.demo.database;

import com.nortal.test.jdbc.SqlScriptExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseService {
    private final ObjectFactory<SqlScriptExecutor> sqlScriptExecutor;

    public void addTodoToDatabase() {
        sqlScriptExecutor.getObject().executeFromClasspath("query/addTodo.sql");
    }
}
