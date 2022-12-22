package com.nortal.test.selenide.demo.mediator;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.nortal.test.core.services.CucumberScenarioProvider;
import com.nortal.test.core.util.RetryingInvoker;
import com.nortal.test.selenide.demo.configuration.TestDemoProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class UiMediator {
    private final AtomicInteger screenshotCounter = new AtomicInteger();
    private final TestDemoProperties testDemoProperties;
    private final CucumberScenarioProvider scenarioProvider;

    public enum ElementType {XPATH, ID, CLASS_NAME, NAME, TAG_NAME, CSS}

    private void demoMode(String element, ElementType type) {
        final String script = resolveDemoScript(element, type);
        log.trace("Executing javascript snippet: {}", script);

        Optional.ofNullable(script)
                .ifPresent(scr -> RetryingInvoker.retry(() -> Selenide.executeJavaScript(scr)));

    }
/**
 * This method is purely for demonstration purposes.
 * We manipulate DOM to show dashed borders on element that we are interacting with during test runtime.
 * */

    private String resolveDemoScript(String element, ElementType type) {
        final String styleAttribute = "setAttribute('style', 'border:4px dashed #ffdd99')";

        switch (type) {
            case XPATH:
                return "var query = document.evaluate(\"" + element + "\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);"
                        + "  var results = Array(query.snapshotLength).fill(0).map((element, index) =>  query.snapshotItem(index));"
                        + "   for (let element of results) { element." + styleAttribute + "}";
            case ID:
                return "document.getElementById(\"" + element + "\")." + styleAttribute + ";";
            case CLASS_NAME:
                return "for (let element of document.getElementsByClassName(\"" + element + "\")) { element." + styleAttribute + " };";
            case NAME:
                return "for (let element of document.getElementsByName(\"" + element + "\")) { element." + styleAttribute + " };";
            case TAG_NAME:
                return "for (let element of document.getElementsByTagName(\"" + element + "\")) { element." + styleAttribute + " };";
            case CSS:
                return "for (let elementByCss of document.querySelectorAll(\"" + element + "\")) { elementByCss." + styleAttribute + " };";
        }
        return null;
    }

    private void takeScreenshot() {
        byte[] data = Selenide.screenshot(OutputType.BYTES);
        String name = "screenshot-" + screenshotCounter.incrementAndGet();
        scenarioProvider.getCucumberScenario().attach(data, "image/png", name);
    }

    public SelenideElement elementXpath(String xpath, boolean highlightOverride) {

        if (testDemoProperties.isDemoMode() && highlightOverride) {
            demoMode(xpath, ElementType.XPATH);
        }

        SelenideElement action = RetryingInvoker.retry(() -> Selenide.element(By.xpath(xpath)));
        if (testDemoProperties.isScreenshotsSteps()) {
            takeScreenshot();
        }

        return action;
    }
}
