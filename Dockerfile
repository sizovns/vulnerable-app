#
# Build stage
#
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

RUN mkdir -p api/src
RUN mkdir -p admin/src
RUN mkdir -p bff/src

COPY pom.xml .
COPY api/src ./api/src
COPY api/pom.xml ./api/pom.xml
COPY admin/src ./admin/src
COPY admin/pom.xml ./admin/pom.xml
COPY bff/src ./bff/src
COPY bff/pom.xml ./bff/pom.xml

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