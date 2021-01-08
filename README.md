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

Here's a tldr of the API documentation if you're too lazy to start it up. But know that you should be a shamed of yourself!

<p align="center">
    <img src="https://media.giphy.com/media/vX9WcCiWwUF7G/giphy.gif">
</p>

The API offers two access points, we could say that they correspond to a `backend` and a `frontend`. The first (i.e. the backend) is to let you (or your sysadmin) configure your application within the engine, and the second (i.e. the frontend) is what your application will be using to access the data stored by the engine or trigger events (little bit on that later).

#### Backend

##### Applications

**`POST`** `/applications`

Registers the application within the gamification engine.

**Header**:

| Key          | Value            |
| ------------ | ---------------- |
| Content-Type | application/json |

**Request body**:

| Parameter   | Type   | Description                             |
| ----------- | ------ | --------------------------------------- |
| Name        | String | The name of the application registering |
| URL         | String | URL of the application                  |
| Description | String | A little description of the application |

**Responses**:

**`201`** Created

| Key   | Description                                      |
| ----- | ------------------------------------------------ |
| token | The authentication token to play with the engine |

**Example:**

```bash
curl --location --request POST 'http://underification.lo/applications' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Stack Underflow",
    "description": "Stack underflow is a Chinese clone of stack overflow.",
    "url": "flow.io"
}'

# Response
{
	"token":"vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2"
}
```

##### Badge

**`GET`** `/badges`

Get all the badges of an application

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Request body:**

N/A

**Responses:**

**`200`** Success

| Parameter   | Type   | Description                       |
| ----------- | ------ | --------------------------------- |
| Name        | String | The name of the badge             |
| Image       | String | A path the the image of the badge |
| Description | String | A little description of the badge |

> Note: The responses is a list of badges, the above are the fields for a single badge

**`401`** Unauthorized

The given authentication token isn't valid.

**Example:**

```bash
curl --location --request GET "http://underification.lo/badges" \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2'

# Response
[
    {
        "name": "Newbie",
        "image": "n/a",
        "description": "Newly created account"
    },
    {
        "name": "Awesome question",
        "image": "n/a",
        "description": "User asked a question that got a lot of upvotes"
    }
]
```



**`POST`** `/badges`

Create a new badge for a given application

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Request body:**

| Parameter   | Type   | Description                       |
| ----------- | ------ | --------------------------------- |
| Name        | String | The name of the badge             |
| Image       | String | A path the the image of the badge |
| Description | String | A little description of the badge |

**Responses:**

**`201`** Created

| Parameter   | Type   | Description                       |
| ----------- | ------ | --------------------------------- |
| Name        | String | The name of the badge             |
| Image       | String | A path the the image of the badge |
| Description | String | A little description of the badge |

**`401`** Unauthorized

The given authentication token isn't valid.

**Example:**

```bash
curl --location --request POST 'http://underification.lo/badges' \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2' \
--data-raw '{
    "name": "Newbie",
    "description": "Newly created account",
    "image": "https://cdn4.iconfinder.com/data/icons/badges-9/66/47-512.png"
}'

# Response
{
	"name": "Newbie",
	"image": "path/to/image.png",
	"description": "Newly created account"
}
```

##### Scale points

**`GET`** `/pointScales`

Get all the point scales of an application

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Request body:**

N/A

**Responses:**

**`200`** Success

| Key  | Description                 |
| ---- | --------------------------- |
| name | The name of the point scale |

> Note: The responses is a list of point scales, the above are the fields for a single point scale

**`401`** Unauthorized

The given authentication token isn't valid.

**Example:**

```bash
curl --location --request GET "http://underification.lo/pointScales" \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2'

# Response
[
    {
        "name": "Reputation"
    },
    {
        "name": "Activity"
    }
]
```



**`POST`** `/badges`

Create a new badge for a given application

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Request body:**

| Parameter   | Type   | Description                       |
| ----------- | ------ | --------------------------------- |
| Name        | String | The name of the badge             |
| Image       | String | A path the the image of the badge |
| Description | String | A little description of the badge |

**Responses:**

`201`

| Parameter   | Type   | Description                       |
| ----------- | ------ | --------------------------------- |
| Name        | String | The name of the badge             |
| Image       | String | A path the the image of the badge |
| Description | String | A little description of the badge |

**`401`** Unauthorized

The given authentication token isn't valid.

**Example:**

```bash
curl --location --request POST 'http://underification.lo/badges' \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2' \
--data-raw '{
    "name": "Newbie",
    "description": "Newly created account",
    "image": "https://cdn4.iconfinder.com/data/icons/badges-9/66/47-512.png"
}'

# Response
{
    "name": "Newbie",
    "image": "path/to/image.png",
    "description": "Newly created account"
}
```

##### Rules

**`POST`** `/rules`

Create a new rule for a given application

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Request body:**
| Parameter              | Type    | Description                                                  |
| ---------------------- | ------- | ------------------------------------------------------------ |
| eventType              | String  | The name of the event that triggers the rule                 |
| badgeName (optional)   | String  | The name of the badge to attribute                           |
| scaleName (optional)   | String  | The name of the scale point under which the points are attributed |
| scalePoints (optional) | Integer | The amount of points to give                                 |
> Note: Event tho badgeName, scaleName & scalePoints are optional, you NEED to specify at least one of them!
>
> Note 2: scaleName & scalePoints come as a pair, which means if you specify one you need to specify the other.

**Responses:**
**`201`** Created

| Parameter              | Type    | Description                                                  |
| ---------------------- | ------- | ------------------------------------------------------------ |
| eventType              | String  | The name of the event that triggers the rule                 |
| badgeName (optional)   | String  | The name of the badge to attribute                           |
| scaleName (optional)   | String  | The name of the scale point under which the points are attributed |
| scalePoints (optional) | Integer | The amount of points to give                                 |

**`401`** Unauthorized

The given authentication token isn't valid.

**`400`** Bad request

You've forgotten the badge name or the scale point and the points to give.

**Example:**
```bash
curl --location --request POST 'http://underification.lo/rules' \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2' \
--data-raw '{
   "eventType": "newUser",
   "badgeName": "Newbie",
   "scaleName": "",
   "scalePoints": ""
}'

# Response
{
    "eventType": "newUser",
    "badgeName": "Newbie",
    "scaleName": "",
    "scalePoints": null
}
```

#### Frontend

##### Events

**`POST`** `/rules`

Something happened on the application which triggers one of the rules found in the gamification engine.

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Request body**:

| Parameter | Type   | Description                                                  |
| --------- | ------ | ------------------------------------------------------------ |
| appUserId | String | The ID of the user **managed by the application**            |
| eventType | String | The name of the event that was specified when creating a new rule |

**Responses**:

**`201`** Created

The rule was triggered

**`401`** Unauthorized

The given authentication token isn't valid.

**`400`** Bad request

The given eventType doesn't exist.

**Example:**

```bash
curl --location --request POST '`http://underification.lo/events' \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2' \
--data-raw '{
    "appUserId": "123",
    "eventType": "newUser"
}'

# Response
```



##### Users

**`GET`** `/users/{user}/badges`

Get all the badges earned by a user

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Parameters**

| Name | Type   | Description                                       |
| ---- | ------ | ------------------------------------------------- |
| user | String | The ID of the user **managed by the application** |

**Responses**

**`200`** Success

| Parameter   | Type   | Description                       |
| ----------- | ------ | --------------------------------- |
| Name        | String | The name of the badge             |
| Image       | String | A path the the image of the badge |
| Description | String | A little description of the badge |

> Note: The responses is a list of badges, the above are the fields for a single badge

**`401`** Unauthorized

The given authentication token isn't valid.

**`400`** Bad request

The user doesn't exist.

**Example:**

```bash
curl --location --request GET 'http://underification.lo/users/123/badges' \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2'

# Response
[
    {
        "name": "Newbie",
        "image": "n/a",
        "description": "Newly created account"
    }
]
```



**`GET`** `/users/{user}/scores`

Get all the scores earned by a user for each scale point in which he "participated"

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Parameters**

| Name | Type   | Description                                       |
| ---- | ------ | ------------------------------------------------- |
| user | String | The ID of the user **managed by the application** |

**Responses**

**`200`** Success

| Parameter      | Type    | Description                                                 |
| -------------- | ------- | ----------------------------------------------------------- |
| pointScaleName | String  | The name of the point scale in which the points were earned |
| Score          | Integer | The amount of points earned                                 |

> Note: The responses is a list of scores, the above are the fields for a single score

**`401`** Unauthorized

The given authentication token isn't valid.

**`400`** Bad request

The user doesn't exist.

**Example:**

```bash
curl --location --request GET 'http://underification.lo/users/123/scores' \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2'

# Response
[
    {
        "pointScaleName": "Reputation",
        "score": 1
    }
]
```

##### Scale points

**`GET`** `/pointScales/{pointScale}/leaderBoard`

Get the leader board for the given point scale

**Header:**

| Key          | Value                                       |
| ------------ | ------------------------------------------- |
| Content-Type | application/json                            |
| X-API-KEY    | The authentication token of the application |

**Parameters**

| Name       | Type    | Description                                                  |
| ---------- | ------- | ------------------------------------------------------------ |
| pointScale | String  | The name of the point scale. It corresponds to the name given when creating the scale point |
| limit      | Integer | The number of users to return in the leader board            |

**Responses:**

**`200`** Success

| Parameter | Type    | Description                    |
| --------- | ------- | ------------------------------ |
| appUserId | String  | The ID of the user             |
| Score     | Integer | The points the user has earned |

> Note: The response is a list of entries in the leader board, the above are the fields for a single entry.

**`401`** Unauthorized

The given authentication token isn't valid.

**`400`** Bad request

The scale point doesn't exist.

**Example:**

```bash
curl --location --request GET 'http://underification.lo/pointScales/Reputation/leaderBoard?limit=1' \
--header 'Content-Type: application/json' \
--header 'X-API-KEY: vKlk1qRecW2w6JCPQE2UPyjSdFUCXbSkimGl3nPFHFgmVvLXp2' \
--data-raw '{
    "appUserId": "123",
    "eventType": "upVoted"
}'

# Response
[
    {
        "appUserId": "123",
        "score": 1
    }
]
```

## Deployment

As for the main project, we've generated (this time willingly) a Docker image that contains the latest version of Underification.

```bash
$ docker pull ghcr.io/underflowers/underification:latest
```

Now, all you need to do is to start up a database. Once you have a database running, you need to update [/api-impl/src/main/resources/application.properties](/api-impl/src/main/resources/application.properties) with the database information needed so that Spring Boot can establish a connection with. It should look something like this:

```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3307}/underification?createDatabaseIfNotExist=true
spring.datasource.username=${MYSQL_USER:underflower}
spring.datasource.password=${MYSQL_PASSWORD:securepassword}
```

To help with this tedious task, we've created a [docker-compose.yml](docker-compose.yml) which starts up a MySQL database and the REST API.

```bash
$ docker-compose up -d
```

> Note:  If you decide to use our docker-compose.yml, you'll need to updated  it with the database environment variables. Make sure that in the `application.properties` file you've set the `spring.datasource.url=jdbc:mysql` to **underification_db** (it's the name of the service in the docker-compose.yml), otherwise it  won't work.
>
> The paths used in the docker-compose.yml are relative to the project  structure, but if you don't wont to have all of the project locally,  don't forget to update them.

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

