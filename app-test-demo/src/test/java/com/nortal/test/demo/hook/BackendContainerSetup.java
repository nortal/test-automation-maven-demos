package com.nortal.test.demo.hook;

import com.nortal.test.testcontainers.AbstractTestableContainerSetup;
import com.nortal.test.testcontainers.images.builder.ImageFromDockerfile;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.testcontainers.images.builder.dockerfile.DockerfileBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BackendContainerSetup extends AbstractTestableContainerSetup {
    private static final String BASE_IMAGE = "vilmrunas/todo-app-backend";

    @NotNull
    @Override
    public String applicationName() {
        return "todo-app-backend";
    }

    @NotNull
    @Override
    public String maxMemory() {
        return "512m";
    }

    @NotNull
    @Override
    public Map<String, String> getTargetContainerEnvConfig() {
        Map<String, String> environment = new HashMap<>();
        environment.put("TODO_DB_HOSTNAME", "todo-db");
        environment.put("TODO_DB_PORT", "5432");
        environment.put("TODO_DB_NAME", "postgres");

        return environment;
    }

    @NotNull
    @Override
    protected ImageFromDockerfile build() {
        return new ImageFromDockerfile(BASE_IMAGE)
                .withDockerfileFromBuilder(builder -> builder.from(BASE_IMAGE).build());
    }

    @Override
    public void additionalBuilderConfiguration(@NotNull DockerfileBuilder dockerfileBuilder) {
        //do nothing
    }

    @NotNull
    @Override
    public List<String> additionalCommandParts() {
        return Collections.emptyList();
    }

    @Override
    public void additionalImageFromDockerfileConfiguration(@NotNull ImageFromDockerfile imageFromDockerfile) {
        //do nothing
    }

    @Override
    public void onContainerStartupInitiated() {
        //do nothing
    }
}
