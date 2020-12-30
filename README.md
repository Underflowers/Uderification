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

The API offers two access points. We could say that they correspond to a `backend` and a `frontend`. The first (i.e. the backend) is to let you (or your sysadmin) configure your application within the engine, and the second (i.e. the frontend) is what your application will be using to access the data stored by the engine or trigger events (little bit on that later).

#### Backend

##### Applications

**`POST`** `http://underification.lo/applications`

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
	"token":"ELodX6tPk3Lu8vHj2aHdMrkadxWouHbVkorldPPo0IuqGiNyUX"
}
```

##### Badge

**`GET`** `http://underification.lo/badges`

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
--header 'X-API-KEY: ELodX6tPk3Lu8vHj2aHdMrkadxWouHbVkorldPPo0IuqGiNyUX'

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



**`POST`** `http://underification.lo/badges`

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
--header 'X-API-KEY: ELodX6tPk3Lu8vHj2aHdMrkadxWouHbVkorldPPo0IuqGiNyUX' \
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

**`GET`** `http://underification.lo/pointScales`

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
--header 'X-API-KEY: ELodX6tPk3Lu8vHj2aHdMrkadxWouHbVkorldPPo0IuqGiNyUX'

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



`POST` `http://underification.lo/badges`

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
--header 'X-API-KEY: ELodX6tPk3Lu8vHj2aHdMrkadxWouHbVkorldPPo0IuqGiNyUX' \
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

**`POST`** `http://underification.lo/rules`

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

**`POST`** `http://underification.lo/rules`

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

