package com.nortal.test.demo.glue;

import com.codeborne.selenide.Selenide;
import com.nortal.test.core.services.CucumberScenarioProvider;
import com.nortal.test.demo.hook.FrontendContextualContainer;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class BaseUiStepDefs {
    private final AtomicInteger screenshotCounter = new AtomicInteger();
    @Autowired
    protected FrontendContextualContainer frontendContainer;
    @Autowired
    private CucumberScenarioProvider scenarioProvider;

    protected void takeScreenshot() {
        byte[] data = Selenide.screenshot(OutputType.BYTES);
        String name = "screenshot-" + screenshotCounter.incrementAndGet();
        scenarioProvider.getCucumberScenario().attach(data, "image/png", name);
    }
}
