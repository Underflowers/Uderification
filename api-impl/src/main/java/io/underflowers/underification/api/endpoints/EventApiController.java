package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.EventsApi;
import io.underflowers.underification.api.RulesApi;
import io.underflowers.underification.api.model.Event;
import io.underflowers.underification.api.model.Rule;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.BadgeEntity;
import io.underflowers.underification.entities.PointScaleEntity;
import io.underflowers.underification.entities.RuleEntity;
import io.underflowers.underification.repositories.BadgeRepository;
import io.underflowers.underification.repositories.PointScaleRepository;
import io.underflowers.underification.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@Controller
public class EventApiController implements EventsApi {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    ServletRequest request;

    @Override
    public ResponseEntity<Event> triggerEvent(@Valid Event event) {
        
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }


}
