# Exchange Rate Challenge

API that fetches exchange-rates from an external API (https://exchangerate.host) and use them for conversion calculations.

### Versions and tools

- Java 20
- Spring boot 3.1.0
- Maven
- Redis
- Swagger documentation (Open API 3)

### Run on windows

1. Run redis
    - Open WLS (Linux terminal on windows)
    - Run the command
    ```
    sudo service redis-server start
    ```
2. Run spring boot application
    - Open command prompt
    - Go the ExchangeRateChallenge package directory
    - Run the commands
    ```
    mvn clean install
    mvn spring-boot:run
    ```
3. Use Swagger UI on: http://localhost:8080/swagger-ui/index.html
