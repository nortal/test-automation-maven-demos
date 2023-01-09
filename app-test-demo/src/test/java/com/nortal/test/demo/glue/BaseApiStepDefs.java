package com.nortal.test.demo.glue;

import com.nortal.test.asserts.Assertion;
import com.nortal.test.asserts.AssertionOperation;
import com.nortal.test.asserts.ValidationService;
import com.nortal.test.demo.database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class BaseApiStepDefs {
    @Autowired
    protected DatabaseService databaseService;
    @Autowired
    protected ValidationService validationService;

    protected Assertion equalsAssertion(Object expectedValue, String expression, String message) {
        return new Assertion.Builder()
                .message(message)
                .expression(expression)
                .expectedValue(expectedValue)
                .operation(AssertionOperation.EQUALS).build();
    }

    protected Assertion notNullAssertion(String expression, String message) {
        return new Assertion.Builder()
                .message(message)
                .expression(expression)
                .operation(AssertionOperation.NOT_NULL).build();
    }

    protected Assertion statusCodeAssertion(HttpStatus expectedStatusCode) {
        return new Assertion.Builder()
                .message("Assert status code")
                .expression("statusCode")
                .expectedValue(expectedStatusCode)
                .operation(AssertionOperation.EQUALS).build();
    }
}
