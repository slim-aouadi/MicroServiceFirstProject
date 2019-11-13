FROM java:8
EXPOSE 8282
ADD /target/Stock-0.0.1-SNAPSHOT.jar docker-stock.jar
ENTRYPOINT ["java","-jar","docker-stock.jar"]