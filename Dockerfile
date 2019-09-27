FROM openjdk:8-jdk-alpine

VOLUME /tmp
ADD ./target/invillia-0.0.1-SNAPSHOT app.jar
ENV JAVA_OPTS="-Xms256m -Xmx512m -agentlib:jdwp=transport=dt_socket,address=9000,server=y,suspend=n"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${SPRING_PROFILES} -jar /app.jar" ]