FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY ./ ./
RUN mvn clean package -fae

FROM openjdk:17-alpine as app
WORKDIR /app
COPY --from=build /app/target/summarizer-0.0.1-SNAPSHOT.jar /app/summarizer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/summarizer-0.0.1-SNAPSHOT.jar"]
