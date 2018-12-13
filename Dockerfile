FROM openjdk:8-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./slike-api/target/slike-api-1.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD java -jar slike-api-1.0-SNAPSHOT.jar