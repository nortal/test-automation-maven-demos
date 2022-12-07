package com.nortal.test.reportportal.demo.glue;

import io.cucumber.java.en.Step;
import lombok.SneakyThrows;

public class StepDefs {

    @Step("A step is called")
    public void stepIsCalled() {
        doSleep();
    }

    @Step("Something is called")
    public void stepIsCall() {
        doSleep();
    }

    @Step("Something is done")
    public void stepIsDone() {
        doSleep();
    }

    @SneakyThrows
    private void doSleep() {
        Thread.sleep(50L);
    }
}
