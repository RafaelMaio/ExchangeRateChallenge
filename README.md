# Exchange Rate Challenge

API that fetches exchange-rates from an external API (https://exchangerate.host) and use them for conversion calculations.

### Versions and tools

- Java 20
- Spring boot 3.1.0
- Maven
- Redis
- Swagger documentation (Open API 3)

### Run on windows

1. In application.yml change the host address to:
     ```
    host: localhost
     ```
2. Run redis
    - Open WLS (Linux terminal on windows)
    - Run the command
    ```
    sudo service redis-server start
    ```
3. Run spring boot application
    - Open command prompt
    - Go the ExchangeRateChallenge package directory
    - Run the commands
    ```
    mvn clean install
    mvn spring-boot:run
    ```
4. Use Swagger UI on: http://localhost:8080/swagger-ui/index.html

### Run on docker

1. Do not change the application.yml:
    ```
   host: redis
    ```
2. Run the following commands:
   - Open command prompt
   - Go the ExchangeRateChallenge package directory
    ```
   mvn clean install
   docker-compose build
   docker-compose up
    ```
3. Use Swagger UI on: http://localhost:8080/swagger-ui/index.html