package io.underflowers.underification.api.spec.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.underflowers.underification.ApiException;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.dto.Event;
import io.underflowers.underification.api.dto.Rule;
import io.underflowers.underification.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;

public class EventSteps {

    private Environment environment;
    private BasicSteps basicSteps;
    private ApplicationSteps applicationSteps;
    private RuleSteps ruleSteps;
    private DefaultApi api;

    private Event eventPayload;

    public EventSteps(Environment environment, BasicSteps basicSteps, ApplicationSteps applicationSteps, RuleSteps ruleSteps) {
        this.environment = environment;
        this.basicSteps = basicSteps;
        this.applicationSteps = applicationSteps;
        this.ruleSteps = ruleSteps;

        this.api = environment.getApi();
    }

    @Given("^I have an event payload$")
    public void i_have_a_event_payload() throws Throwable {
        this.eventPayload = new Event()
                .appUserId("test_user")
                .eventType(this.ruleSteps.getLastReceivedRule().getEventType());
    }

    @When("^I POST the event payload$")
    public void i_post_the_event_payload() throws Throwable {
        this.api.triggerEvent(this.eventPayload);
    }

}
