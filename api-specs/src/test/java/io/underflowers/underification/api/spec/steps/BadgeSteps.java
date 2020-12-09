package io.underflowers.underification.api.spec.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.underflowers.underification.ApiException;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.dto.Badge;
import io.underflowers.underification.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;

public class BadgeSteps {

    private Environment environment;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private DefaultApi api;

    private Badge badge;
    private Badge lastReceivedBadge;

    public BadgeSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps) {
        this.environment = environment;
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;

        this.api = environment.getApi();
    }

    @Given("I have a badge payload")
    public void i_have_an_badge_payload() throws Throwable {
        badge = new Badge()
                .description("This is a test badge")
                .image("image")
                .name("test_badge_1");
    }

    @When("^I POST the badge payload to the /badges endpoint$")
    public void i_POST_the_badge_payload_to_the_badges_endpoint() throws Throwable {
        try {
            api.getApiClient().addDefaultHeader("X-API-KEY", applicationSteps.getReceivedToken());
            basicSteps.processApiResponse(api.createBadgeWithHttpInfo(badge));
            lastReceivedBadge = (Badge)basicSteps.getLastApiResponse().getData();
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I send a GET to the /badges endpoint$")
    public void iSendAGETToTheBadgesEndpoint() {
        try {
            basicSteps.processApiResponse(api.getBadgesWithHttpInfo());
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @And("I receive a payload that is the same as the badge payload")
    public void iReceiveAPayloadThatIsTheSameAsTheBadgePayload() {
        assertEquals(badge, lastReceivedBadge);
    }

    public Badge getLastReceivedBadge() {
        return lastReceivedBadge;
    }
}
