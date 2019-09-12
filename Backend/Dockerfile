# our base build image
FROM maven as maven

# copy the project files
COPY ./Backend/pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./Backend/src ./src

# build for release
RUN mvn package

# our final base image
FROM openjdk:8-jre-alpine

# set deployment directory
WORKDIR /theWeatherMan

# copy over the built artifact from the maven image
COPY --from=maven target/theWeatherMan-0.0.1-SNAPSHOT.jar ./

# set the startup command to run your binary
CMD ["java", "-jar", "./theWeatherMan-0.0.1-SNAPSHOT.jar"]