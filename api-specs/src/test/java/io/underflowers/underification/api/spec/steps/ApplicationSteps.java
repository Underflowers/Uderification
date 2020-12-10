package io.underflowers.underification.api.spec.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.underflowers.underification.ApiException;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.dto.Application;
import io.underflowers.underification.api.dto.RegisteringApplication;
import io.underflowers.underification.api.dto.Token;
import io.underflowers.underification.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationSteps {

    private Environment environment;
    private BasicSteps basicSteps;
    private DefaultApi api;

    private RegisteringApplication application;
    private String receivedToken;

    public ApplicationSteps(Environment environment, BasicSteps basicSteps) {
        this.environment = environment;
        this.basicSteps = basicSteps;

        this.api = environment.getApi();
    }

    @Given("I have an application payload")
    public void i_have_an_application_payload() throws Throwable {
        application = new RegisteringApplication()
                .name("test_application")
                .description("This is a test application")
                .url("URL");
    }

    @When("^I POST the application payload to the /applications endpoint$")
    public void i_POST_the_application_payload_to_the_applications_endpoint() throws Throwable {
        try {
            basicSteps.processApiResponse(api.registerApplicationWithHttpInfo(application));
            receivedToken = ((Token)basicSteps.getLastApiResponse().getData()).getToken();
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I send a GET to the /applications endpoint$")
    public void iSendAGETToTheApplicationsEndpoint() {
        try {
            basicSteps.processApiResponse(api.getApplicationsWithHttpInfo());
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @And("I receive a non-null application token")
    public void iReceiveANonNullApplicationToken() {
        assertNotNull(receivedToken);
    }

    public String getReceivedToken() {
        return receivedToken;
    }
}
