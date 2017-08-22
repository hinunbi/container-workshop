JBoss Fuse Microservice Application
===

![01-container-workshop-cats.png](../img/01-container-workshop-cats.png)

To run this project with Maven use

    mvn -Djavax.net.ssl.trustStore=ssl/client.ts \
        -DACTIVEMQ_BROKER_URL=ssl://amq-broker-ssl-amq-tcp-ssl-container-workshop.1d35.starter-us-east-1.openshiftapps.com:443 \
        spring-boot:run 

