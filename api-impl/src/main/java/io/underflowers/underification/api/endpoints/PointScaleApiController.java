package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.PointScalesApi;
import io.underflowers.underification.api.model.LeaderBoard;
import io.underflowers.underification.api.model.LeaderBoardEntry;
import io.underflowers.underification.api.model.PointScale;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.PointScaleEntity;
import io.underflowers.underification.repositories.PointRewardRepository;
import io.underflowers.underification.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PointScaleApiController implements PointScalesApi {

    static private final Integer DEFAULT_LIMIT = 10;

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    PointRewardRepository pointRewardRepository;

    @Autowired
    ServletRequest request;

    @Override
    public ResponseEntity<PointScale> createPointScale(@Valid PointScale pointScale) {
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");
        PointScaleEntity pointScaleEntity = this.toPointScaleEntity(pointScale);
        // link the application and save the pointScale
        pointScaleEntity.setApplication(applicationEntity);
        pointScaleRepository.save(pointScaleEntity);
        return new ResponseEntity<>(pointScale, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PointScale>> getPointScales() {
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");
        // Fetch all point scales by application
        List<PointScale> pointScales = new ArrayList<>();
        for (PointScaleEntity pointScaleEntity : pointScaleRepository.findAllByApplication(applicationEntity))
            pointScales.add(toPointScale(pointScaleEntity));

        return ResponseEntity.ok(pointScales);
    }

    @Override
    public ResponseEntity<List<LeaderBoardEntry>> getLeaderBoard(String pointScale, @Valid Integer limit) {
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");

        // point scale doesn't exist for the given application
        if (pointScaleRepository.findByNameAndApplication(pointScale, applicationEntity) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        limit = limit == null ? DEFAULT_LIMIT : limit;
        // Retrieve leaders for the given point scale
        List<LeaderBoardEntry> leaders = pointRewardRepository.findLeaders(applicationEntity.getId(), pointScale, limit).stream()
                .map(u -> new LeaderBoardEntry().appUserId(u.getFirst()).score(u.getSecond().intValue())).collect(Collectors.toList());
        return ResponseEntity.ok(leaders);
    }

    private PointScaleEntity toPointScaleEntity(PointScale pointScale) {
        PointScaleEntity entity = new PointScaleEntity();
        entity.setName(pointScale.getName());
        return entity;
    }

    private PointScale toPointScale(PointScaleEntity entity) {
        PointScale pointScale = new PointScale();
        pointScale.setName(entity.getName());
        return pointScale;
    }
}
