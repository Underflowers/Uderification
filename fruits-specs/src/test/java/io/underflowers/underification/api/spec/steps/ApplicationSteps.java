package io.underflowers.underification.api.spec.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.underflowers.underification.ApiException;
import io.underflowers.underification.ApiResponse;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.dto.Application;
import io.underflowers.underification.api.spec.helpers.Environment;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationSteps {

    private Environment environment;
    private DefaultApi api;

    Application application;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String lastReceivedLocationHeader;
    private Application lastReceivedApplication;

    public ApplicationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("I have an application payload")
    public void i_have_an_application_payload() throws Throwable {
        application = new Application()
                .name("My Application");
    }

    /*@When("^I POST the fruit payload to the /applications endpoint$")
    public void i_POST_the_fruit_payload_to_the_fruits_endpoint() throws Throwable {
        try {
            lastApiResponse = api.createApplicationWithHttpInfo(application);
            processApiResponse(lastApiResponse);
        } catch (ApiException e) {
            processApiException(e);
        }
    }*/

    @When("^I send a GET to the /applications endpoint$")
    public void iSendAGETToTheApplicationsEndpoint() {
        try {
            lastApiResponse = api.getApplicationsWithHttpInfo();
            processApiResponse(lastApiResponse);
        } catch (ApiException e) {
            processApiException(e);
        }
    }

    /*@When("I send a GET to the URL in the location header")
    public void iSendAGETToTheURLInTheLocationHeader() {
        Integer id = Integer.parseInt(lastReceivedLocationHeader.substring(lastReceivedLocationHeader.lastIndexOf('/') + 1));
        try {
            lastApiResponse = api.getApplicationsWithHttpInfo(id);
            processApiResponse(lastApiResponse);
            lastReceivedApplication = (Application)lastApiResponse.getData();
        } catch (ApiException e) {
            processApiException(e);
        }
    }*/

    @And("I receive a payload that is the same as the application payload")
    public void iReceiveAPayloadThatIsTheSameAsTheApplicationPayload() {
        assertEquals(application, lastReceivedApplication);
    }

    private void processApiResponse(ApiResponse apiResponse) {
        lastApiResponse = apiResponse;
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiResponse.getStatusCode();
        List<String> locationHeaderValues = (List<String>)lastApiResponse.getHeaders().get("Location");
        lastReceivedLocationHeader = locationHeaderValues != null ? locationHeaderValues.get(0) : null;
    }

    private void processApiException(ApiException apiException) {
        lastApiCallThrewException = true;
        lastApiResponse = null;
        lastApiException = apiException;
        lastStatusCode = lastApiException.getCode();
    }
}
