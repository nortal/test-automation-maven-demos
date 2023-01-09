package com.nortal.test.demo.glue;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.nortal.test.demo.page.AddTodoPageObj;
import com.nortal.test.demo.page.MainPageObj;
import io.cucumber.java.en.Step;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoUiStepDefinitions extends BaseUiStepDefs {
    private static final String DESCRIPTION_VALUE = "test";
    private final MainPageObj mainPageObj;
    private final AddTodoPageObj addTodoPageObj;
    private int initialTodosCount;

    @Step("user has opened Todo application in browser")
    public void openPageInBrowser() {
        Selenide.open(frontendContainer.getUiUrl());
        mainPageObj.header().shouldBe(Condition.visible);
        initialTodosCount = mainPageObj.rowsTodoTable().size();
        takeScreenshot();
    }

    @Step("user clicks on Add New button")
    public void userClicksOnAddNewButton() {
        mainPageObj.buttonAddNewTodo().click();
    }

    @Step("user should see Add New Todo page")
    public void userShouldSeeAddNewTodoPage() {
        addTodoPageObj.header().shouldBe(Condition.visible);
    }

    @Step("user enters todo information")
    public void userEntersTodoInformation() {
        addTodoPageObj.inputFieldDescription().setValue(DESCRIPTION_VALUE);
        addTodoPageObj.buttonSaveTodo().click();
    }

    @Step("user should see that todo is added")
    public void userShouldSeeTodoAddedToTheList() {
        mainPageObj.header().shouldBe(Condition.visible);
        mainPageObj.rowsTodoTable().shouldBe(CollectionCondition.sizeGreaterThan(initialTodosCount));
        takeScreenshot();
    }

    @Step("user clicks on Remove button")
    public void userClicksOnRemoveButton() {
        mainPageObj.buttonRemoveTodo().click();
    }

    @Step("user should see that todo is deleted")
    public void userShouldSeeThatTodoIsDeleted() {
        mainPageObj.header().shouldBe(Condition.visible);
        mainPageObj.rowsTodoTable().shouldBe(CollectionCondition.sizeLessThan(initialTodosCount));
        takeScreenshot();
    }
}
