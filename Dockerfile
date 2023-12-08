FROM maven:3.8-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline
COPY src ./src
RUN mvn -B -e -o package
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=builder /app/target/OnlineSponsoredAds-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "OnlineSponsoredAds-0.0.1-SNAPSHOT.jar"]
