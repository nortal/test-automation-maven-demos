package com.nortal.test.demo.glue;

import com.codeborne.selenide.Selenide;
import com.nortal.test.asserts.Assertion;
import com.nortal.test.asserts.AssertionOperation;
import com.nortal.test.asserts.Validation;
import com.nortal.test.demo.dto.Todo;
import io.cucumber.java.Before;
import io.cucumber.java.en.Step;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TodoApiStepDefinitions extends BaseStepDefs {
    private static final Long TODO_ID = 1L;
    private ResponseEntity<List<Todo>> responseTodoList;
    private ResponseEntity<Todo> responseTodo;
    private ResponseEntity<Object> responseEmpty;

    @Step("user has todos")
    public void setupTodosInDatabase() {
        databaseService.addTodoToDatabase();
    }

    @Step("user requests todo list")
    public void getTodoList() {
        responseTodoList = todoApiService.getTodoList();
    }

    @Step("user should get response with a list of todos")
    public void assertTodoList() {
        final Validation.Builder validationBuilder = new Validation.Builder()
                .context(responseTodoList)
                .title("Validate get todo list response")
                .assertion(statusCodeAssertion(HttpStatus.OK))
                .assertion(notNullAssertion("body[0]", "Todo is not null"));
        validationService.validate(validationBuilder.build());
    }

    @Step("user requests todo details")
    public void getTodoDetails() {
        responseTodo = todoApiService.getTodoById(TODO_ID);
    }

    @Step("user should get response with todo details")
    public void assertTodoDetails() {
        final Validation.Builder validationBuilder = new Validation.Builder()
                .context(responseTodo)
                .title("Validate get todo details response")
                .assertion(statusCodeAssertion(HttpStatus.OK))
                .assertion(equalsAssertion(TODO_ID, "body.id", "Todo id is as expected"))
                .assertion(notNullAssertion("body.description", "Todo description is not null"))
                .assertion(notNullAssertion("body.deadline", "Todo deadline is not null"))
                .assertion(notNullAssertion("body.completed", "Todo completed is not null"))
                .assertion(notNullAssertion("body.uri", "Todo uri is not null"));
        validationService.validate(validationBuilder.build());
    }

    @Step("user adds a todo")
    public void addTodo() {
        responseTodo = todoApiService.postTodo(todoProvider.getTodo());
    }

    @Step("user should get response with added todo")
    public void assertAddTodoResponse() {
        Todo expectedTodo = todoProvider.getTodo();

        final Validation.Builder validationBuilder = new Validation.Builder()
                .context(responseTodo)
                .title("Validate add todo response")
                .assertion(statusCodeAssertion(HttpStatus.OK))
                .assertion(notNullAssertion("body.id", "Todo id is not null"))
                .assertion(equalsAssertion(expectedTodo.getDescription(), "body.description", "Todo description is as expected"))
                .assertion(equalsAssertion(expectedTodo.getDeadline(), "body.deadline", "Todo deadline is as expected"))
                .assertion(equalsAssertion(expectedTodo.isCompleted(), "body.completed", "Todo completed is as expected"))
                .assertion(notNullAssertion("body.uri", "Todo uri is not null"));
        validationService.validate(validationBuilder.build());
    }

    @Step("user deletes a todo")
    public void deleteTodo() {
        responseEmpty = todoApiService.deleteTodo(TODO_ID);
    }

    @Step("user should get success response")
    public void assertDeleteTodoResponse() {
        final Validation.Builder validationBuilder = new Validation.Builder()
                .context(responseEmpty)
                .title("Validate delete todo response")
                .assertion(statusCodeAssertion(HttpStatus.OK))
                .assertion(new Assertion.Builder()
                        .message("Response is empty")
                        .expression("body")
                        .operation(AssertionOperation.NULL).build());
        validationService.validate(validationBuilder.build());
    }

    //TODO: Remove this method when fix for Selenide webdriver completed and Feign module will work properly together with Selenide module
    @Before
    public void openBrowser() {
        Selenide.open("http://localhost:3000/");
    }
}
