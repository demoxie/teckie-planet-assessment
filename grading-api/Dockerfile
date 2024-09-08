FROM amazoncorretto:17

WORKDIR /app

# Install tar and gzip
RUN yum install -y tar gzip

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "target/grading-api-0.0.1-SNAPSHOT.jar"]
