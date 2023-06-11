FROM openjdk:20
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ExchangeRateChallenge.jar
ENTRYPOINT ["java", "-jar", "ExchangeRateChallenge.jar"]