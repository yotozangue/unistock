FROM tomcat:9-jdk8

ENV JAVA_OPTS=${JAVA_OPTS}

COPY target/unistock.war /usr/local/tomcat/webapps/

EXPOSE ${TOMCAT_PORT}

CMD ["sh", "-c", "catalina.sh run $JAVA_OPTS"]
