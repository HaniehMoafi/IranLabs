# RECOMMENDATION SERVICE

The goal was to develop a very simple project in order to support functionalities of a **Recommendation service**.
<br/>
To achieve this goal I have considered three main Entity:
1. UserEntity
2. RecommendationEntity
3. ResponseEntity

<br/>
This project implemented with the following stack:
<ul>
<li>JAVA, as the base language of project</li>
<li>SPRING-BOOT, as the main framework of the project</li>
<li>H2-DATABASE, lightweight database which needs no configuration</li>
<li>HIBERNATE, an open source and lightweight ORM(Object Relational Mapping) tool that help us in interacting with database and persisting data</li>
<li>MAVEN, as the build tool and dependency manager for the project</li>
<li>JUNIT & SPRING-TEST are also used in order to write unit tests for repository layer</li>
<li>SWAGGER, is used to have an auto-generated API documentation</li>
<li>REST-API,  is an architectural style for an application program interface (API) that uses HTTP requests to access and use data.</li>
<li>JSF(primefaces), as a fron-end layer</li>
</ul>


<br/>
#instruction
-run application on port 8080 (root: localhost:8080)
- I have enabled `Swagger-ui` documentation for the project and after running it you can access it using the link below : [http://localhost:8080/swagger-ui/index.html]()

- Also, I have enabled **H2 Database Console**, you can use it in your browser with following URL: [http://localhost:8080/h2-console]() , when project is running, needed parameters are :
1. Username: sa
2. Password: sa
3. Url: jdbc:h2:mem:iranLabs
- you can login into admin panel with 'admin' as username and password both.
