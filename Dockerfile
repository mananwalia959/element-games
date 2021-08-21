FROM openjdk:11.0-jdk-slim
RUN addgroup --system spring 
RUN adduser --system spring --ingroup spring
USER spring:spring
COPY /build/libs/elementgames-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
