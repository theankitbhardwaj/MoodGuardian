# [MoodGuardian](https://moodguardian-api.onrender.com/)
Spring boot application with PostgreSQL DB to track mood. 
This application exposes APIs that can be used to create a full stack Mood Tracker Application.
## API Reference

#### Signup for account:

```http
  POST /api/v1/auth/signup
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Your unique username |
| `email` | `string` | **Required**. Your unique email |
| `password` | `string` | **Required**. Password  for account |
| `role` | `List of roles` |  Role to be provided to user (ROLE_MOD,ROLE_USER,ROLE_ADMIN) |

#### Sign In:

```http
  POST /api/v1/auth/signin
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `username`      | `string` | **Required**. Your username |
| `password`      | `string` | **Required**. Your password |

This will return some parameters, of which one is `accessToken`. This will be authorization bearer token for further use of API by each user. This is JWT. This token is valid of 1 day only for now.

### Mood api usage

#### Create Mood:
Create mood for today. If mood for today's date already added it will throw error.

```http
  POST /api/v1/mood
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `feeling`      | `string` | **Required**. Mood value |
| `notes`      | `string` |  Notes for today. |

Mood value can be VERY_SAD, SAD, NEUTRAL, HAPPY, VERY_HAPPY.

You also need to authorize this request.

| Authorization | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| **Required**      | `Bearer Token` | `accessToken ` from SignIn Request |


#### Get all Moods:
This will return all saved moods of current user.

```http
  GET /api/v1/mood
```
You also need to authorize this request.

| Authorization | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| **Required**      | `Bearer Token` | `accessToken ` from SignIn Request |

#### Get Mood by ID :
This will return mood by mood id. This will only succeed if mood id belongs to authorized user.

```http
  GET /api/v1/mood/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Mood ID |

You also need to authorize this request.

| Authorization | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| **Required**      | `Bearer Token` | `accessToken ` from SignIn Request |

## Run Locally

#### Step 1: 
Clone the project

```bash
  git clone https://github.com/theankitbhardwaj/MoodGuardian
```

#### Step 2: 
Create .env files in ./src/main/resources and ./docker folder using examples provided.


### Before doing further steps please ensure you have docker installed.

Go to the docker directory under project root

```bash
  cd MoodGuardian/docker
```

Docker Build

```bash
  docker-compose build
```

Start the containers

```bash
  docker-compose up
```

Also, please insert roles in Roles table by using below SQL Statements for the first time setting up database.
```bash
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
<a href="https://spring.io/" target="blank">
<img align="center" src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="Spring" height="40" width="40" />
</a>
<a href="https://www.postgresql.org" target="blank">
<img align="center" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="PostgreSQL" height="40" width="40" />
</a>
<a href="https://www.docker.com/" target="blank">
<img align="center" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/docker/docker-original-wordmark.svg" alt="Docker" height="40" width="40" />
</a>
