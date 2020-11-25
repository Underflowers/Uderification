package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.LeaderBoardsApi;
import io.underflowers.underification.api.model.LeaderBoard;
import io.underflowers.underification.entities.ApplicationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LeaderBoardsApiController implements LeaderBoardsApi {

    static private final Integer DEFAULT_LIMIT = 10;

    @Autowired
    ServletRequest request;

    public ResponseEntity<List<LeaderBoard>> getLeaderBoards(@Valid Integer limit){
        // TODO
        return null;
    }

    @Override
    public ResponseEntity<LeaderBoard> getLeaderBoard(String pointScale, @Valid Integer limit) {
        limit = limit == null ? DEFAULT_LIMIT : limit;
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");


        // scaleId = SELECT id FROM pointScales WHERE name=pointScale AND applications_id=applicationEntity.id
        // leaderBoard = SELECT users_id, COUNT(points) as score FROM points_rewards
        // WHERE pointScales_id=scaleId
        // GROUP BY users_id
        // ORDER BY score DESC
        // TODO: translate from SQL
        return null;
    }
}
