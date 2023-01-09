package com.nortal.test.demo.hook;

import com.nortal.test.testcontainers.AbstractAuxiliaryContainer;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.GenericContainer;

@Component
public class FrontendContextualContainer extends AbstractAuxiliaryContainer<FrontendContextualContainer.FrontendContainer>{
    private static final String CONTAINER_IMAGE = "vilmrunas/todo-app-frontend";
    public static final int UI_PORT = 80;

    public static class FrontendContainer extends GenericContainer<FrontendContainer> {
        public FrontendContainer() {
            super(CONTAINER_IMAGE);
        }
    }

    @NotNull
    @Override
    public String getConfigurationKey() {
        return "todo-frontend";
    }

    @NotNull
    @Override
    public FrontendContainer configure() {
        return new FrontendContainer()
                .withExposedPorts(UI_PORT)
                .withNetworkAliases("todo-frontend");
    }

    public String getUiUrl() {
        return new HttpUrl.Builder()
                .scheme("http")
                .host(getTestContainer().getHost())
                .port(getTestContainer().getMappedPort(UI_PORT))
                .build().toString();
    }
}
