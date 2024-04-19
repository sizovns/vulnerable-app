#
# Build stage
#
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .
COPY api/ ./api
COPY admin/ ./admin

RUN mvn clean package -DskipTests

#
# Install to TomCat
#
FROM tomcat:jre17-temurin-jammy

RUN chown www-data:www-data -R /usr/local/tomcat

USER www-data

COPY --from=build /app/api/target/api.war /usr/local/tomcat/webapps/.
COPY --from=build /app/admin/target/admin.war /usr/local/tomcat/webapps/.
EXPOSE 8080

# CMD ["catalina.sh", "run"]
ENTRYPOINT ["/bin/bash", "/usr/local/tomcat/bin/catalina.sh", "run"]