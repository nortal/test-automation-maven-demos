package com.nortal.test.demo.hook;

import com.nortal.test.testcontainers.AbstractAuxiliaryContainer;
import com.nortal.test.testcontainers.configuration.TestableContainerJacocoProperties;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT;

@Component
@RequiredArgsConstructor
public class PostgresContextualContainer extends AbstractAuxiliaryContainer<PostgresContextualContainer.TestPostgreSQLContainer>{
    private static final String POSTGRES_IMAGE = "postgres:14";
    private final TestableContainerJacocoProperties jacocoProperties;

    public static class TestPostgreSQLContainer extends PostgreSQLContainer<TestPostgreSQLContainer> {
        public TestPostgreSQLContainer() {
            super(POSTGRES_IMAGE);
        }
    }

    @NotNull
    @Override
    public String getConfigurationKey() {
        return "todo-db";
    }

    @NotNull
    @Override
    public TestPostgreSQLContainer configure() {
        return new TestPostgreSQLContainer()
                .withDatabaseName("postgres")
                .withUsername("postgres")
                .withPassword("postgres")
                .withNetworkAliases("todo-db");
    }

    @NotNull
    public String getInternalJdbcUrl() {
        return createDatabaseUrl("todo-db", POSTGRESQL_PORT);
    }

    public String getJdbcUrl() {
        return createDatabaseUrl(jacocoProperties.getHost(), getTestContainer().getMappedPort(POSTGRESQL_PORT));
    }

    private String createDatabaseUrl(String host, int port) {
        Assertions.assertTrue(isRunning());
        return String.format("jdbc:postgresql://%s:%d/%s",
                host,
                port,
                getTestContainer().getDatabaseName());
    }
}
