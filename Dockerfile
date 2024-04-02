#
# Build stage
#
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

#
# Install to TomCat
#
FROM tomcat:jre17-temurin-jammy

COPY --from=build /app/target/demo.war /usr/local/tomcat/webapps/.
EXPOSE 8080

CMD ["catalina.sh", "run"]