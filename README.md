# Java_Projects

This Project contains JDK14 and Spring Boot Based Integrations.
Key Features are
1. Redis Integration - [ Redis Database, Redis Cache ]
2. Concurrency Conpets -[ ExecutorSerice, Fork Join Pool, Completable Future etc ]
3. Rest API - [ Rest Template, Web Client , HATEOS ]
4. Error Handlers 
5. Microservices - [ Service Discovery, Circuit Breakers, Dynamic Configuration ]
6. Spring - [  AOP, Scheduling, Retryable, JPA, JTA , Security , Actuators]
7. Web Sockets - [ STOMP with sockJS ]
8. JDBC
etc.

I am actively working on it, feel free to collaborate by  forking it.

Microservices Configurations

# api-gateway
port:6010

# config-server
port:6050

# discovery-server
port: 6060
address: http://localhost:6060/discovery-server/


# microservice-1
port:6070

# microservice-2
port:6180

# microservice-3
port:6190


# Project Configurations

1. To Build Jars for all projects : Go to z-scripts folder and run the buil-java-projects-jar.sh
