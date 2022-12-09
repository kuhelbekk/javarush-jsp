FROM tomcat:9.0.69-jdk17-corretto
COPY ./target/ROOT.war /usr/local/tomcat/webapps/
