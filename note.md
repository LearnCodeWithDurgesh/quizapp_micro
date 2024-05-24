### Some important architecture flow:

![flow](./quiz%20microservices%20architecture.svg)

https://excalidraw.com/#json=U0va-_IQ9juz_71XfX-s_,pnusQ3ix-MQxcYYm0vMnCA

## Download docker:

### docker -v

### TO RUN ZIPKIN SERVER USING DOCKER:

docker run -d -p 9411:9411 openzipkin/zipkin

### keycloak:

docker run -p 8085:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.4 start-dev

    -   Create realm
    -   create client
    -   Creat User

application . properties file ko check karligiega

client id
client secret
urls

### Kafka:

docker run -p 9092:9092 apache/kafka:3.7.0

### To create token:

http://localhost:8079/realms/apps/protocol/openid-connect/token

Updated
Spring boot -
map based dao(repository) data access object --, ✅
validation, ✅
data jpa, ✅
custom query @query(quiz ko search ), caching(mysql connection ke use hoti hai), ✅
Database--> radis(temp. caching)
profiles,✅--> development--> testing ---> production(multiple environments)
feign client, ✅
ribbon❌ ,load balancing using eureka service registry ✅
hystrix❌ , resilience4j✅
actuator, ✅
zipkin, ✅
security using oauth 2,✅ with authentication keycloak
custom centralized exception. ✅

Apache Kafka,✅
graph ql,✅
cloud config server,
