package io.underflowers.underification.api.endpoints;

import io.underflowers.underification.api.PointScalesApi;
import io.underflowers.underification.api.model.Badge;
import io.underflowers.underification.api.model.PointScale;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.BadgeEntity;
import io.underflowers.underification.entities.PointScaleEntity;
import io.underflowers.underification.repositories.PointScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PointScaleApiController implements PointScalesApi {

    @Autowired
    PointScaleRepository pointScaleRepository;

    @Autowired
    ServletRequest request;

    @Override
    public ResponseEntity<PointScale> createPointScale(@Valid PointScale pointScale) {
        // Fetch the linked application from the token passed
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("applicationEntity");

        // check that the point scale doesn't already exist
        // if so, return 409
        if (pointScaleRepository.findByNameAndApplication(pointScale.getName(), applicationEntity) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

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
