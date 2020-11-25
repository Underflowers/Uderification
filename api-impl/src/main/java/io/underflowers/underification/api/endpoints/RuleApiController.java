package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.RulesApi;
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
public class RuleApiController implements RulesApi {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    ServletRequest request;

    @Override
    public ResponseEntity<Rule> createRule(@Valid Rule rule) {
        RuleEntity ruleEntity = toRuleEntity(rule);
        if(ruleEntity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ruleRepository.save(ruleEntity);
        return new ResponseEntity<>(rule, HttpStatus.CREATED);
    }

    private RuleEntity toRuleEntity(Rule rule) {
        // Must have one of them or both
        if(rule.getBadgeName().isEmpty() && rule.getScaleName().isEmpty()){
            return null;
        }

        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setEventType(rule.getEventType());

        // We have a badge name => check if exists in DB
        if(!rule.getBadgeName().isEmpty()) {
            BadgeEntity badgeEntity = badgeRepository.findByNameAndApplication(rule.getBadgeName(), applicationEntity);
            if(badgeEntity == null){
                return null;
            }

            ruleEntity.setBadge(badgeEntity);
            ruleEntity.setAmount(0);
        }

        // We have a scale name and points => check if exists in DB
        if(!rule.getScaleName().isEmpty() && rule.getScalePoints() != null) {
            PointScaleEntity pointScaleEntity = pointScaleRepository.findByNameAndApplication(rule.getScaleName(), applicationEntity);
            if(pointScaleEntity == null){
                return null;
            }

            ruleEntity.setPointScale(pointScaleEntity);
            ruleEntity.setAmount(rule.getScalePoints());
        }

        return ruleEntity;
    }

}
