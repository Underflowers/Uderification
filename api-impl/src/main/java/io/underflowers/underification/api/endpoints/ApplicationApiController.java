package io.underflowers.underification.api.endpoints;

import io.swagger.annotations.ApiParam;
import io.underflowers.underification.api.ApplicationsApi;
import io.underflowers.underification.api.model.Application;
import io.underflowers.underification.api.model.Token;
import io.underflowers.underification.api.model.RegisteringApplication;
import io.underflowers.underification.entities.ApplicationEntity;
import io.underflowers.underification.repositories.ApplicationRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationApiController implements ApplicationsApi {

    @Autowired
    ApplicationRepository applicationRepository;

    public ResponseEntity<Token> registerApplication(@ApiParam(value = "", required = true) @Valid @RequestBody RegisteringApplication application) {
        Token token = new Token();
        // TODO move hardcoded value
        token.setToken(RandomStringUtils.randomAlphanumeric(50));

        ApplicationEntity entity = toApplicationEntity(application);
        entity.setToken(token.getToken());
        applicationRepository.save(entity);

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Application>> getApplications() {
        List<Application> applications = new ArrayList<>();
        for (ApplicationEntity applicationEntity : applicationRepository.findAll())
            applications.add(toApplication(applicationEntity));

        return ResponseEntity.ok(applications);
    }

    private ApplicationEntity toApplicationEntity(RegisteringApplication application) {
        ApplicationEntity entity = new ApplicationEntity();
        entity.setName(application.getName());
        entity.setDescription(application.getDescription());
        entity.setUrl(application.getUrl());
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
