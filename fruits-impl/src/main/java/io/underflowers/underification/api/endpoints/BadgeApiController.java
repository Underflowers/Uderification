package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.BadgesApi;
import io.underflowers.underification.api.model.Application;
import io.underflowers.underification.api.model.Badge;
import io.underflowers.underification.api.model.RegisteringApplication;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.BadgeEntity;
import io.underflowers.underification.repositories.ApplicationRepository;
import io.underflowers.underification.repositories.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BadgeApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    @Override
    public ResponseEntity<Badge> createBadge(@Valid Badge badge) {
        BadgeEntity badgeEntity = this.toBadgeEntity(badge);
        badgeRepository.save(badgeEntity);
        return new ResponseEntity<>(badge, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Badge>> getBadges() {
        return null;
    }

    private BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        entity.setImage(badge.getImage());
        entity.setDescription(badge.getDescription());
        return entity;
    }

    private Badge toBadge(BadgeEntity entity) {
        Badge badge = new Badge();
        badge.setName(entity.getName());
        badge.setImage(entity.getImage());
        badge.setDescription(entity.getDescription());
        return badge;
    }
}
