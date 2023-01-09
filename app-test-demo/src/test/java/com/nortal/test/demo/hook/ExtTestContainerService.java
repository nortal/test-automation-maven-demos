package com.nortal.test.demo.hook;

import com.nortal.test.testcontainers.TestContainerNetworkProvider;
import com.nortal.test.testcontainers.TestContainerService;
import com.nortal.test.testcontainers.configuration.TestableContainerProperties;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

@Slf4j
@Primary
@Service
public class ExtTestContainerService extends TestContainerService {

    public ExtTestContainerService(
            @NotNull TestContainerNetworkProvider testContainerNetworkProvider,
            @NotNull TestableContainerProperties testableContainerProperties) {
        super(testContainerNetworkProvider, testableContainerProperties);
    }

    @Override
    protected void startContainer(@NotNull final GenericContainer<?> applicationContainer) {
        //Adding additional wait condition. It's completely optional.
//        applicationContainer.waitingFor(Wait.forLogMessage(".*Started Main in.*", 1));

        super.startContainer(applicationContainer);

        attachLogger(applicationContainer);
    }

    private void attachLogger(final GenericContainer<?> applicationContainer) {
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log).withSeparateOutputStreams();
        applicationContainer.followOutput(logConsumer);
    }
}
