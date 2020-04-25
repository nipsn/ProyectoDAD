FROM openjdk:8-jre
WORKDIR /
ADD sender-emails-source.jar sender-emails-source.jar
ADD javax.mail-1.6.2.jar /usr/java/jdk-8/lib
CMD ["java", "-jar", "sender-emails-source.jar"]