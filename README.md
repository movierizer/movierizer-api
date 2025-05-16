# movierizer-API Documentation

# Prerequisites

Before try to start the application you have to check two things : 

 - Java is in version 21 
 - Maven is at least version 3.8.7

# Without docker 

## Installation and launch 

Before anything you have to add some environnement variable : 

```
 - DATASOURCE_URL : your database URL (export=postgresql://your_port/name_DBB) 
```
For this app we recommanded to use postgresql database because we have configure our application with this database but if you want to change this, go to application.properties and change this two variable : `spring.jpa.properties.hibernate.dialect` and `spring.datasource.driver-class-name` 

```
 - POSTGRES_USER : the user of your database (export=myPassword)

 - POSTGRES_PASSWORD : the password of your database (export=myUser)

 - BACK_SERVER_PORT : the port where this app his launch (export=8080), but by default this port is 8080
```
Don't forget to launch your database before this folowing step !!!

To launch install and launch this application we must do :

```
./mvnw clean spring-boot:run
```

and you can find the movierizer-API on this adress : [http://localhost:8080/movies](http://localhost:8080/movies)

## Utilisation

First you have to create a user to have acess to the API : 

```sh
curl -v -X POST -H "Content-Type: application/json" -d "@user.json" http://localhost:8080/auth/signup

{"id":"4eef2986-b14b-4120-81b2-12954e86b0f7","username":"s0nren","email":"jean@gmail.com","password":"$2a$10$F06r3Oy5xpcsWaLW03Qj2O8efMym1KeGy7uLT7XipK8T7MrKl73Bq","user_language":null,"profile_picture":null,"create_at":"2025-05-16 09:55:14.990051","role":null,"updatedAt":"2025-05-16T07:55:14.986+00:00","authorities":[],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}
```
This file is in the root of the project. You can change the value of the user as you want. 

If you want, you can already login to the form but it's not recommanded. 
For more security you have to get a bearer token from the API : 

So you have to login to get your token 

```sh
curl -v -X POST -H "Content-Type: application/json" -d "@user.json" http://localhost:8080/auth/login

{"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMG5yZW4iLCJpYXQiOjE3NDczODI0NDQsImV4cCI6MTc0NzM4NjA0NH0.Z4k4SSt9SD7T06kzwTSs_LHseB5IThEgY4nz4bLWuDE","expiresIn":3600000}
```

For the following step we recommanded to do :

```sh
export TOKEN=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMG5yZW4iLCJpYXQiOjE3NDczODI0NDQsImV4cCI6MTc0NzM4NjA0NH0.Z4k4SSt9SD7T06kzwTSs_LHseB5IThEgY4nz4bLWuDE
```

Now you have finish and you can use the API securely  

without token : 

```sh
curl -v -X GET -H "Content-Type: application/json" http://localhost:8080/movies

* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /movies HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.5.0
> Accept: */*
> Content-Type: application/json
> Authorization: Bearer 
> 
< HTTP/1.1 401 
< Vary: Origin
< Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
< WWW-Authenticate: Basic realm="Realm"
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 0
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Length: 0
< Date: Fri, 16 May 2025 08:04:43 GMT
< 
* Connection #0 to host localhost left intact
```

with token : 

```sh
curl -v -X GET -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" http://localhost:8080/movies

* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /movies HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.5.0
> Accept: */*
> Content-Type: application/json
> Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMG5yZW4iLCJpYXQiOjE3NDczODI0NDQsImV4cCI6MTc0NzM4NjA0NH0.Z4k4SSt9SD7T06kzwTSs_LHseB5IThEgY4nz4bLWuDE
> 
< HTTP/1.1 200 
< Vary: Origin
< Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 0
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Fri, 16 May 2025 08:05:35 GMT
< 
* Connection #0 to host localhost left intact
[{"id":1,"title":"Léon","overview":null,"grade":100,"original_title":null,"release_date":null,"poster_path":null,"backdrop_path":null,"budget":0,"revenue":0,"runtime":0},{"id":2,"title":"The Matrix","overview":null,"grade":80,"original_title":null,"release_date":null,"poster_path":null,"backdrop_path":null,"budget":0,"revenue":0,"runtime":0}]
```



