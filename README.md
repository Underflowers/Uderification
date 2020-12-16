# Underification

## About

Underification is a microservice to help you integrate gamification features to your projects. This means that it **doesn't offer** any form of graphical interface and it should only be used to help you create your own projects.

## API endpoints

If you wish to have a documentation of the API, you can build and run the REST API. After doing the followin, the Sprint Boot server will be up and running, listening for connections on port 8080.

```bash
docker-compose up -d underification_db
cd api-impl/
mvn sprint-boot:run
```

> Note: If you aren't using the mysql container we offer, you'll have to change the configuration in [application.properties](.//api-impl/src/main/resources/application.properties).

### TLDR

Here's a tldr of the API documentation if you're too lazy to start it up. You should be a shamed of yourself!

<p align="center">
<img src="https://media.giphy.com/media/vX9WcCiWwUF7G/giphy.gif">
</p>

The API offers two access points. We could say that they correspond to a `backend` and a `frontend` The first (i.e. the backend) is to let you (or your sysadmin) configure your application within the engine, and the second (i.e. the frontedn) is what your application will be using to access the data stored by the engine or trigger events (little bit on that later).

#### Backend

##### Application

##### Badge

##### Scale points

##### Rules

#### Frontend

##### Envents

##### Users

##### Scale points

## Deployment

## Contributing

### Prerequisites

### Project setup

### Tests

## Possible issues

## Main contributors

| Name               | Username      |
| ------------------ | ------------- |
| Robin Demarta      | rdemarta      |
| Loic Dessaules     | gollgot       |
| Thibaud Franchetti | ChatDeBlofeld |
| Doran Kayoumi      | kayoumido     |



# Build and run the Fruit microservice

```
docker-compose up -d underification_db
cd fruits-impl/
mvn spring-boot:run
```

You can then access:

* the [API documentation](http://localhost:8080/swagger-ui.html), generated from annotations in the code
* the [API endpoint](http://localhost:8080/), accepting GET and POST requests

You can use curl to invoke the endpoints:

* To retrieve the list of fruits previously created:

```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/fruits'
```

* To create a new fruit (beware that in the live documentation, there are extra \ line separators in the JSON payload that cause issues in some shells)

```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{
  "colour": "red",
  "expirationDate": "2020-11-06",
  "expirationDateTime": "2020-11-06T05:43:27.909Z",
  "kind": "apple",
  "size": "small",
  "weight": "light"
}' 'http://localhost:8080/fruits'
```

# Test the Fruit microservice by running the executable specification

You can use the Cucumber project to validate the API implementation. Do this when the server is running.

```
cd cd fruits-specs/
mvn clean test
```
You will see the test results in the console, but you can also open the file located in `./target/cucumber`

# Deployment 

As always and for the rest of our lives, we provide a beautiful handcrafted docker image.

Just run:

`docker-compose up -d`

> Note: You may want to change the database credentials in the compose file for obvious security purposes.

