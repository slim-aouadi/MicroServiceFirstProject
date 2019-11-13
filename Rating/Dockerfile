FROM java:8
EXPOSE 8383
ADD /target/Rating-0.0.1-SNAPSHOT.jar docker-rating.jar
ENTRYPOINT ["java","-jar","docker-rating.jar"]