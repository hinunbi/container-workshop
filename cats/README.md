JBoss Fuse Microservice Application
===

![01-container-workshop-cats.png](../img/01-container-workshop-cats.png)

To run this project with Maven use

    mvn -Djavax.net.ssl.trustStore=ssl/client.ts \
        -DACTIVEMQ_BROKER_URL=ssl://broker-amq-tcp-ssl-container-workshop.apps.13.124.168.242.nip.io:443 \
        spring-boot:run 

