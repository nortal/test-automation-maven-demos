package com.nortal.test.demo.glue;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.nortal.test.demo.hook.FrontendContextualContainer;
import com.nortal.test.demo.mediator.UiMediator;
import io.cucumber.java.en.Step;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoUiStepDefinitions {
    private static final String MAIN_PAGE_HEADER = "//nav/a";
    private static final String TODO_TABLE_ROWS = "//div/table/tbody/tr";
    private static final String BUTTON_REMOVE_TODO = "//div/table/tbody/tr[1]/td[4]/button";
    private static final String BUTTON_ADD_NEW_TODO = "//div/button";
    public static final String ADD_TODO_PAGE_HEADER = "//div/h1";
    public static final String DESCRIPTION_INPUT_FIELD = "//div/form/fieldset[1]/input";
    public static final String SAVE_TODO_BUTTON = "//div/form/button";
    public static final String DESCRIPTION_VALUE = "test";
    private final UiMediator uiMediator;
    private final FrontendContextualContainer frontendContainer;
    private int initialTodosCount;

    @Step("user has opened Todo application in browser")
    public void openPageInBrowser() {
        Selenide.open(frontendContainer.getUiUrl());
        uiMediator.elementXpath(MAIN_PAGE_HEADER, true).shouldBe(Condition.visible);

        initialTodosCount = uiMediator.elementsXpath(TODO_TABLE_ROWS, true).size();
    }

    @Step("user clicks on Add New button")
    public void userClicksOnAddNewButton() {
        uiMediator.elementXpath(BUTTON_ADD_NEW_TODO, true).click();
    }

    @Step("user should see Add New Todo page")
    public void userShouldSeeAddNewTodoPage() {
        uiMediator.elementXpath(ADD_TODO_PAGE_HEADER, true).shouldBe(Condition.visible);
    }

    @Step("user enters todo information")
    public void userEntersTodoInformation() {
        uiMediator.elementXpath(DESCRIPTION_INPUT_FIELD, true).val(DESCRIPTION_VALUE);
        uiMediator.elementXpath(SAVE_TODO_BUTTON, true).click();
    }

    @Step("user should see that todo is added")
    public void userShouldSeeTodoAddedToTheList() {
        uiMediator.elementXpath(MAIN_PAGE_HEADER, true).shouldBe(Condition.visible);

        uiMediator.elementsXpath(TODO_TABLE_ROWS, true)
                .shouldBe(CollectionCondition.sizeGreaterThan(initialTodosCount));
    }

    @Step("user clicks on Remove button")
    public void userClicksOnRemoveButton() {
        uiMediator.elementXpath(BUTTON_REMOVE_TODO, true).click();
    }

    @Step("user should see that todo is deleted")
    public void userShouldSeeThatTodoIsDeleted() {
        uiMediator.elementXpath(MAIN_PAGE_HEADER, true).shouldBe(Condition.visible);

        uiMediator.elementsXpath(TODO_TABLE_ROWS, true)
                .shouldBe(CollectionCondition.sizeLessThan(initialTodosCount));
    }
}
