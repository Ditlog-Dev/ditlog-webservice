FROM openjdk:jdk-alpine

# Create app directory and set as working directory
RUN mkdir -p /opt/ditlog-webservice
WORKDIR /opt/ditlog-webservice
# RUN chown -R www-data:www-data /opt/ditlog-webservice

# Copy app source to container
COPY . /opt/ditlog-webservice

# Set Spring Jar permissions
RUN mkdir -p /opt/ditlog-webservice/build
RUN chmod -R 0755 /opt/ditlog-webservice/build
# RUN chown -R www-data:www-data /opt/ditlog-webservice/build
RUN mkdir -p /opt/ditlog-webservice/out
RUN chmod -R 0755 /opt/ditlog-webservice/out
# RUN chown -R www-data:www-data /opt/ditlog-webservice/out

EXPOSE 8080 35729

RUN ./gradlew build

CMD [ "./gradlew", "build", "-t" ]