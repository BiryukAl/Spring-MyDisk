FROM openjdk:17-alpine
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ./gradlew bootRun