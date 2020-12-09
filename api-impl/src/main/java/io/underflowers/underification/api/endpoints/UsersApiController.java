package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.UsersApi;
import io.underflowers.underification.api.model.Badge;
import io.underflowers.underification.api.model.UserScore;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.BadgeRewardEntity;
import io.underflowers.underification.entities.UserEntity;
import io.underflowers.underification.repositories.BadgeRewardRepository;
import io.underflowers.underification.repositories.PointRewardRepository;
import io.underflowers.underification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    @Autowired
    PointRewardRepository pointRewardRepository;

    @Autowired
    ServletRequest request;

    @Override
    public ResponseEntity<List<Badge>> getUserBadges(String user) {
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");

        UserEntity userEntity = userRepository.findByAppUserIdAndApplication(user, applicationEntity);
        if (userEntity == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Badge> badges = new ArrayList<>();
        // get all badges related to the given user
        for (BadgeRewardEntity badgeRewardEntity : badgeRewardRepository.findAllByUser(userEntity))
            badges.add(new Badge()
                .name(badgeRewardEntity.getBadge().getName())
                .image(badgeRewardEntity.getBadge().getImage())
                .description(badgeRewardEntity.getBadge().getDescription())
            );
        return ResponseEntity.ok(badges);
    }

    @Override
    public ResponseEntity<List<UserScore>> getUserScores(String user) {
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");

        // if user doesn't exist for the given application
        UserEntity userEntity = userRepository.findByAppUserIdAndApplication(user, applicationEntity);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Retrieve score on all point scales for the given user
        List<UserScore> scores = pointRewardRepository.findScores(userEntity.getId()).stream()
                .map(u -> new UserScore().pointScaleName(u.getFirst()).score(u.getSecond().intValue())).collect(Collectors.toList());
        return ResponseEntity.ok(scores);
    }
}
