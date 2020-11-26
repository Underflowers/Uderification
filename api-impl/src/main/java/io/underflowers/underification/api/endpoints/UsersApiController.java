package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.UsersApi;
import io.underflowers.underification.api.model.Badge;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.BadgeRewardEntity;
import io.underflowers.underification.entities.UserEntity;
import io.underflowers.underification.repositories.BadgeRewardRepository;
import io.underflowers.underification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    @Autowired
    ServletRequest request;

    @Override
    public ResponseEntity<List<Badge>> getUserBadges(String user) {
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");

        UserEntity userEntity = userRepository.findByAppUserIdAndApplication(user, applicationEntity);
        if (userEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

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
}
