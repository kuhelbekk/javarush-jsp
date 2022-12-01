FROM tomcat:9.0.69-jdk17-corretto
COPY ./target/javarush-jsp-1.0.war /usr/local/tomcat/webapps/