package io.underflowers.underification.api.spec.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.underflowers.underification.ApiException;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.dto.Badge;
import io.underflowers.underification.api.dto.Rule;
import io.underflowers.underification.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;

public class RuleSteps {

    private Environment environment;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private BadgeSteps badgeSteps;
    private DefaultApi api;

    private Rule rule;
    private Rule lastReceivedRule;

    public RuleSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps, BadgeSteps badgeSteps) {
        this.environment = environment;
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
        this.badgeSteps = badgeSteps;

        this.api = environment.getApi();
    }

    @Given("I have a badge rule payload")
    public void i_have_a_badge_rule_payload() throws Throwable {
        rule = new Rule()
                .badgeName(badgeSteps.getLastReceivedBadge().getName())
                .eventType("EventForBadge")
                .scaleName("")
                .scalePoints(0);
    }

//    @Given("I have a scale rule payload")
//    public void i_have_a_scale_rule_payload() throws Throwable {
//        rule = new Rule().scaleName("").eventType("EventForScale");
//    }
//
//    @Given("I have a badge-and-scale rule payload")
//    public void i_have_a_badge_and_scale_rule_payload() throws Throwable {
//        rule = new Rule().badgeName("Badge for rule").eventType("EventForBadgeAndScale");
//    }

    @When("^I POST the rule payload to the /rules endpoint$")
    public void i_POST_the_rule_payload_to_the_rules_endpoint() throws Throwable {
        try {
            api.getApiClient().addDefaultHeader("X-API-KEY", applicationSteps.getReceivedToken());
            basicSteps.processApiResponse(api.createRuleWithHttpInfo(rule));
            lastReceivedRule = (Rule)basicSteps.getLastApiResponse().getData();
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @And("I receive a payload that is the same as the rule payload")
    public void iReceiveAPayloadThatIsTheSameAsTheBadgePayload() {
        assertEquals(rule, lastReceivedRule);
    }
}
