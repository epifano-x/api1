FROM eclipse-temurin:17


WORKDIR /app

COPY . .
RUN ls
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db-postgres:5433/db_api


RUN chmod +x gradlew
RUN ./gradlew build -x test
RUN ls build/libs
EXPOSE 8080


ENTRYPOINT ["java","-jar","build/libs/turismo-api-0.0.1-SNAPSHOT.jar"]
