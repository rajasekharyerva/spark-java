docker build -t spark-java-app .
docker run spark-java-app
-Dlog4j.configurationFile=src/main/resources/log4j2.xml