package com.nortal.test.demo.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "test-automation.custom")
public class TestDemoProperties {
    /**
     * Enable demo mode which highlights elements.
     */
    private final boolean demoMode;
    private final boolean screenshotsSteps;

    public TestDemoProperties(boolean demoMode, boolean screenshotsSteps) {
        this.demoMode = demoMode;
        this.screenshotsSteps = screenshotsSteps;
    }
}
