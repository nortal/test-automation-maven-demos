/**
 * Copyright (c) 2022 Nortal AS
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.nortal.test.feign.demo.glue;

import com.nortal.test.core.services.ScenarioContext;
import com.nortal.test.feign.demo.api.CatApi;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class TestPageStepDefs {
    private static final String RESPONSE = "response";
    private static final int CURRENT_PAGE = 1;
    private final CatApi catApi;
    private final ScenarioContext scenarioContext;

    @When("user requests cat fact")
    public void getCatFact() {
        try {
            final ResponseEntity<CatApi.Fact> responseEntity = catApi.getFact();
            scenarioContext.putStepData(RESPONSE, responseEntity);
        } catch (HttpClientErrorException ex) {
            scenarioContext.putStepData(RESPONSE, ex);
        }
    }

    @Then("user should get response with cat fact")
    public void assertCatFact() {
        ResponseEntity<CatApi.Fact> responseEntity = scenarioContext.getStepData(RESPONSE);
        assertResponse(responseEntity);

        assertNotNull(responseEntity.getBody().getFact(), "Fact should not be null");
        assertNotNull(responseEntity.getBody().getLength(), "Length should not be null");
    }

    @When("user requests a list of cat facts")
    public void getCatFacts() {
        try {
            final ResponseEntity<CatApi.FactsList> responseEntity = catApi.getFacts();
            scenarioContext.putStepData(RESPONSE, responseEntity);
        } catch (HttpClientErrorException ex) {
            scenarioContext.putStepData(RESPONSE, ex);
        }
    }

    @Then("user should get response with a list of cat facts")
    public void assertCatFacts() {
        ResponseEntity<CatApi.FactsList> responseEntity = scenarioContext.getStepData(RESPONSE);
        assertResponse(responseEntity);

        assertEquals(CURRENT_PAGE, responseEntity.getBody().getCurrent_page(), "Unexpected current page");
        assertNotNull(responseEntity.getBody().getLast_page(), "Last page should not be null");

        assertTrue(responseEntity.getBody().getData().size() >= 1, "Unexpected number of facts");
        responseEntity.getBody().getData().forEach(fact -> {
            assertNotNull(fact.getFact(), "Fact should not be null");
            assertNotNull(fact.getLength(), "Length should not be null");
        });
    }

    @When("user requests a list of cat breeds")
    public void getBreeds() {
        try {
            final ResponseEntity<CatApi.BreedsList> responseEntity = catApi.getBreeds();
            scenarioContext.putStepData(RESPONSE, responseEntity);
        } catch (HttpClientErrorException ex) {
            scenarioContext.putStepData(RESPONSE, ex);
        }
    }

    @Then("user should get response with a list of cat breeds")
    public void assertBreeds() {
        ResponseEntity<CatApi.BreedsList> responseEntity = scenarioContext.getStepData(RESPONSE);
        assertResponse(responseEntity);

        assertEquals(CURRENT_PAGE, responseEntity.getBody().getCurrent_page(), "Unexpected current page");
        assertNotNull(responseEntity.getBody().getLast_page(), "Last page should not be null");

        assertTrue(responseEntity.getBody().getData().size() >= 1, "Unexpected number of breeds");
        responseEntity.getBody().getData().forEach(breed -> {
            assertNotNull(breed.getBreed(), "Breed should not be null");
            assertNotNull(breed.getCountry(), "Country should not be null");
            assertNotNull(breed.getOrigin(), "Origin should not be null");
            assertNotNull(breed.getCoat(), "Coat should not be null");
            assertNotNull(breed.getPattern(), "Pattern should not be null");
        });
    }

    private <T> void assertResponse(ResponseEntity<T> responseEntity) {
        assertNotNull(responseEntity, "Response should not be null");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected HTTP status code");
    }
}
