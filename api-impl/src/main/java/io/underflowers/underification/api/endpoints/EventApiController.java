package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.EventsApi;
import io.underflowers.underification.api.model.Event;
import io.underflowers.underification.entities.*;
import io.underflowers.underification.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EventApiController implements EventsApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    @Autowired
    PointRewardRepository pointRewardRepository;

    @Autowired
    ServletRequest request;

    @Override
    public ResponseEntity<Event> triggerEvent(@Valid Event event) {
        // Fetch the linked application from the token passed in request attribute
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");

        // Try to fetch all rules that concern the current eventType / application
        List<RuleEntity> ruleEntities = ruleRepository.findAllByEventTypeAndApplication(event.getEventType(), applicationEntity);
        if(ruleEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Try to fetch the current user from the Event pass in parameter and the linked application
        UserEntity userEntity = userRepository.findByAppUserIdAndApplication(event.getAppUserId(), applicationEntity);
        // The user doesn't exists => create now in the fly
        if(userEntity == null){
            userEntity = new UserEntity();
            userEntity.setAppUserId(event.getAppUserId());
            userEntity.setApplication(applicationEntity);
            userRepository.save(userEntity);
        }

        // Loop through all rules related to the app and the eventType
        for(RuleEntity ruleEntity : ruleEntities) {

            // The rule will reward the user with a badge
            if(ruleEntity.getBadge() != null){
                BadgeRewardEntity badgeRewardEntity = new BadgeRewardEntity();
                badgeRewardEntity.setBadge(ruleEntity.getBadge());
                badgeRewardEntity.setUser(userEntity);
                badgeRewardEntity.setDateTime(LocalDateTime.now());
                badgeRewardRepository.save(badgeRewardEntity);
            }

            // The rule will reward the user with points for a pointScale
            if(ruleEntity.getPointScale() != null){
                PointRewardEntity pointRewardEntity = new PointRewardEntity();
                pointRewardEntity.setPointScale(ruleEntity.getPointScale());
                pointRewardEntity.setPoints(ruleEntity.getAmount());
                pointRewardEntity.setUser(userEntity);
                pointRewardEntity.setTimestamp(LocalDateTime.now());
                pointRewardRepository.save(pointRewardEntity);
            }

        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
