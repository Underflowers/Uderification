package io.underflowers.underification.api.endpoints;

import io.swagger.annotations.ApiParam;
import io.underflowers.underification.api.ApplicationsApi;
import io.underflowers.underification.api.FruitsApi;
import io.underflowers.underification.api.model.Application;
import io.underflowers.underification.api.model.Fruit;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.entities.FruitEntity;
import io.underflowers.underification.repositories.ApplicationRepository;
import io.underflowers.underification.repositories.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationApiController implements ApplicationsApi {

    @Autowired
    ApplicationRepository applicationRepository;

    public ResponseEntity<List<Application>> getApplications() {
        ApplicationEntity entity = new ApplicationEntity();
        entity.setName("Stackunderflow");
        entity.setDescription("Awesome stuff");
        entity.setUrl("flow.io");
        entity.setToken("1234");
        applicationRepository.save(entity);

        List<Application> applications = new ArrayList<>();
        for (ApplicationEntity applicationEntity : applicationRepository.findAll())
            applications.add(toApplication(applicationEntity));

        return ResponseEntity.ok(applications);
    }

    private ApplicationEntity toApplicationEntity(Application application) {
        ApplicationEntity entity = new ApplicationEntity();
        entity.setName(application.getName());
        return entity;
    }

    private  Application toApplication(ApplicationEntity entity) {
        Application application = new Application();
        application.setName(entity.getName());
        application.setDescription(entity.getDescription());
        application.setUrl(entity.getUrl());
        application.setToken(entity.getToken());
        return application;
    }
}
