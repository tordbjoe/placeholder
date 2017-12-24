FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 8080
VOLUME /tmp
RUN mkdir -p /app/
ADD build/libs/placeholder-0.1.jar /app/placeholder.jar
ENTRYPOINT ["java","-jar","/app/placeholder.jar"]