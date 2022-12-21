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
package com.nortal.test.restassured.demo.glue;

import com.nortal.test.restassured.demo.dto.BreedsList;
import com.nortal.test.restassured.demo.dto.Fact;
import com.nortal.test.restassured.demo.dto.FactsList;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPageStepDefs {
    private static final String BASE_URI = "https://catfact.ninja";
    private static final String FACT_ENDPOINT = "/fact";
    private static final String FACTS_LIST_ENDPOINT = "/facts";
    private static final String BREEDS_LIST_ENDPOINT = "/breeds";
    private static final int CURRENT_PAGE = 1;
    private Response response;

    @When("user requests cat fact")
    public void getCatFact() {
        sendGetRequest(FACT_ENDPOINT);
    }

    @Then("user should get response with cat fact")
    public void assertCatFact() {
        assertResponse(response);

        Fact fact = response.getBody().as(Fact.class);
        assertNotNull(fact.getFact(), "Fact should not be null");
        assertNotNull(fact.getLength(), "Length should not be null");
    }

    @When("user requests a list of cat facts")
    public void getCatFacts() {
        sendGetRequest(FACTS_LIST_ENDPOINT);
    }

    @Then("user should get response with a list of cat facts")
    public void assertCatFacts() {
        assertResponse(response);

        FactsList factsList = response.getBody().as(FactsList.class);
        assertEquals(CURRENT_PAGE, factsList.getCurrent_page(), "Unexpected current page");
        assertNotNull(factsList.getLast_page(), "Last page should not be null");

        assertTrue(factsList.getData().size() >= 1, "Unexpected number of facts");
        factsList.getData().forEach(fact -> {
            assertNotNull(fact.getFact(), "Fact should not be null");
            assertNotNull(fact.getLength(), "Length should not be null");
        });
    }

    @When("user requests a list of cat breeds")
    public void getBreeds() {
        sendGetRequest(BREEDS_LIST_ENDPOINT);
    }

    @Then("user should get response with a list of cat breeds")
    public void assertBreeds() {
        assertResponse(response);

        BreedsList breedsList = response.getBody().as(BreedsList.class);
        assertEquals(CURRENT_PAGE, breedsList.getCurrent_page(), "Unexpected current page");
        assertNotNull(breedsList.getLast_page(), "Last page should not be null");

        assertTrue(breedsList.getData().size() >= 1, "Unexpected number of breeds");
        breedsList.getData().forEach(breed -> {
            assertNotNull(breed.getBreed(), "Breed should not be null");
            assertNotNull(breed.getCountry(), "Country should not be null");
            assertNotNull(breed.getOrigin(), "Origin should not be null");
            assertNotNull(breed.getCoat(), "Coat should not be null");
            assertNotNull(breed.getPattern(), "Pattern should not be null");
        });
    }

    private void sendGetRequest(String endpoint) {
        RestAssured.baseURI = BASE_URI;
        RequestSpecification request = RestAssured.given();
        response = request.get(endpoint);
    }

    private void assertResponse(Response response) {
        assertNotNull(response, "Response should not be null");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode(), "Unexpected HTTP status code");
    }
}
