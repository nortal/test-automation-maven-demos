package com.nortal.test.demo.hook;

import com.nortal.test.jdbc.JdbcUrlProvider;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresJdbcUrlProvider implements JdbcUrlProvider {
    private final PostgresContextualContainer postgresContextualContainer;

    @NotNull
    @Override
    public String getInternalJdbcUrl() {
        return postgresContextualContainer.getInternalJdbcUrl();
    }

    @NotNull
    @Override
    public String getJdbcUrl() {
        return postgresContextualContainer.getJdbcUrl();
    }
}
