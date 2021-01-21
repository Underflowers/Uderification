# Underification

<p align="center">
  <img src="https://forthebadge.com/images/badges/powered-by-black-magic.svg" alt="platform">
    <img src="https://forthebadge.com/images/badges/it-works-why.svg" alt="platform">
    <img src="https://forthebadge.com/images/badges/0-percent-optimized.svg" alt="platform">
</p>

## About

Underification is a microservice to help you integrate gamification features to your projects. This means that it **doesn't offer** any form of graphical interface and it should only be used to help you create your own projects.

## Deployment

As for the main project, we've generated (this time willingly) a Docker image that contains the latest version of Underification.

Additionaly, we've created a [docker-compose.yml](docker-compose.yml) which starts up a MySQL database and the REST API.

```bash
$ docker-compose up -d
```

>Note: The paths used in the docker-compose.yml are relative to the project structure, but if you don't wont to have all of the project locally, don't forget to update them.
>
> It is recommended to change the database credentials in the compose file.

The server's now listening on `localhost:8080`.

## API endpoints

If you wish to have a documentation of the API, you can run the REST API (see the [deployment section](#deployment)), then the Sprint Boot server will be up and running, listening for connections on port 8080.

If you're too lazy to start it up, you can find a tldr of the API documentation in our [wiki](https://github.com/Underflowers/Underification/wiki/API-Endpoints-TLDR). But know that you should be a shamed of yourself!

<p align="center">
    <img src="https://media.giphy.com/media/vX9WcCiWwUF7G/giphy.gif">
</p>


## Contributing

### Prerequisites

* Java (v11.0.8)
* Git (v2.28.0)
* Docker (v19.03.12-ce)
* Docker-compose (v1.27.4)
* Maven (v3.6.3)

### Project setup

Clone the repository, then startup the database:

```bash
$ docker-composer up -d underification_db
```

Now you'll need to setup the database environment variables so the API can connect to it. Simply update it accordingly the application.properties.

> Note: You need to put the same values found in the `docker-compose.yml`.

Now you can start up Spring Boot.

```bash
$ cd api-impl/
$ mvn sprint-boot:run 
```

And voilà :relieved:! You can now access the application at `http://localhost:8080`.

### Tests

For the tests, we're using [Cucumber.io](https://cucumber.io/). They can be found in `api-specs/` folder.

Before running the tests, you need to make sure you have a copy of `api-spec.yaml` in `api-specs/src/main/resources/`.

> Note: The file can be found in `api-impl/src/main/resources` but we've created a symlink so it should work.

Now you can run the tests

```bash
$ cd api-secs/
$ mvn test
```

> Note: The Sprint Boot server **NEEDS** to be started!

## Main contributors

| Name               | Username      |
| ------------------ | ------------- |
| Robin Demarta      | rdemarta      |
| Loic Dessaules     | gollgot       |
| Thibaud Franchetti | ChatDeBlofeld |
| Doran Kayoumi      | kayoumido     |
