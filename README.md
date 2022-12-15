```

# How to use this code?

1. Make sure you have [Java 8 or +]
(https://www.java.com/download/) and [Maven](https://maven.apache.org) installed

2. Fork this repository and clone it
  
```
$ git clone https://github.com/<your-user>/admin-management-users.git
```

3. Navigate into the folder  

```
$ cd spring-boot-jwt
```

4. Install dependencies

```
$ mvn install
```

5. Run the project

```
$ mvn spring-boot:run
```

6. Navigate to `http://localhost:8087/swagger-ui.html` in your browser to check everything is working correctly. You can change the default port in the `application.yml` file

```yml
server:
  port: 8087
```

7. Make a GET request to `/api/me` to check you're not authenticated. You should receive a response with a `403` with an `Access Denied` message since you haven't set your valid JWT token yet

```
$ curl -X GET http://localhost:8087/api/me
```

8. Make a POST request to `/api/register` with the default admin user we programatically created to get a valid JWT token

```
$ curl -X POST 'http://localhost:8087/api/login'
```
```javascript
{
    "email": "admin@email.com",
    "password": "admin"
}
```

9. Add the JWT token as a Header parameter and make the initial GET request to `/api/me` again

```
$ curl -X GET http://localhost:8087/api/me -H 'Authorization: Bearer <JWT_TOKEN>'
```

10. And that's it, congrats! You should get a similar response to this one, meaning that you're now authenticated

```javascript
{
    "id": 1,
    "email": "admin@email.com",
    "roles": [
        "ROLE_ADMIN"
    ],
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBlbWFpbC5jb20iLCJhdXRoIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2NzExMzc2NzYsImV4cCI6MTY3MTEzNzk3Nn0.EQriPgsH9vSQhCP286gM7UVsqSMQGs9AcR2fW4S7ghw",
    "msg": "Login successfully"
}
```
