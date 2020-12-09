package io.underflowers.underification.api.spec.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.underflowers.underification.ApiException;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.dto.Badge;
import io.underflowers.underification.api.dto.PointScale;
import io.underflowers.underification.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;

public class PointScaleSteps {

    private Environment environment;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private DefaultApi api;

    private PointScale pointScale;
    private PointScale lastReceivedPointScale;

    public PointScaleSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps) {
        this.environment = environment;
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;

        this.api = environment.getApi();
    }

    @Given("^I have a point scale payload$")
    public void i_have_a_point_scale_payload() throws Throwable {
        pointScale = new PointScale()
                .name("test_point_scale_1");
    }

    @When("^I POST the point scale payload to the /pointScales endpoint$")
    public void i_POST_the_point_scale_payload_to_the_pointscales_endpoint() throws Throwable {
        try {
            api.getApiClient().addDefaultHeader("X-API-KEY", applicationSteps.getReceivedToken());
            basicSteps.processApiResponse(api.createPointScaleWithHttpInfo(pointScale));
            lastReceivedPointScale = (PointScale)basicSteps.getLastApiResponse().getData();
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @And("^I receive a payload that is the same as the pointScale payload$")
    public void iReceiveAPayloadThatIsTheSameAsTheBadgePayload() {
        assertEquals(pointScale, lastReceivedPointScale);
    }

    @When("^I send a GET to the /pointScales endpoint$")
    public void i_send_a_GET_to_the_pointscales_endpoint() {
        try {
            basicSteps.processApiResponse(api.getPointScalesWithHttpInfo());
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

}
