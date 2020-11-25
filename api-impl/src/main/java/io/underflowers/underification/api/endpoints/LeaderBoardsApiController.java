package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.LeaderBoardsApi;
import io.underflowers.underification.api.model.LeaderBoard;
import io.underflowers.underification.api.model.UserScore;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.repositories.FruitRepository;
import io.underflowers.underification.repositories.PointRewardRepository;
import io.underflowers.underification.repositories.PointScaleRepository;
import io.underflowers.underification.repositories.projections.UserScoreProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LeaderBoardsApiController implements LeaderBoardsApi {

    static private final Integer DEFAULT_LIMIT = 10;

    @Autowired
    ServletRequest request;

    @Autowired
    PointRewardRepository pointRewardRepository;

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Override
    public ResponseEntity<LeaderBoard> getLeaderBoard(String pointScale, @Valid Integer limit) {
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");

        if (pointScaleRepository.findByNameAndApplication(pointScale, applicationEntity) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        limit = limit == null ? DEFAULT_LIMIT : limit;
        // Retrieve leaders for the given point scale
        List<UserScore> leaders = pointRewardRepository.findLeaders(applicationEntity.getId(), pointScale, limit).stream()
                .map(u -> new UserScore().userId(u.getUserId()).score(u.getScore())).collect(Collectors.toList());
        return ResponseEntity.ok(new LeaderBoard().pointScale(pointScale).leaders(leaders));
    }
}
