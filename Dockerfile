FROM maven:3.6-jdk-8-alpine

ADD . /usr/share/21buttons_test
WORKDIR /usr/share/21buttons_test

EXPOSE 27074

RUN mvn -e clean package

ENTRYPOINT java -jar target/mastermind-1.0.0.jar --spring.config.name=${APP_ENV}
