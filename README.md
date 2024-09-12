# Kafka Consumer and Producer

This is the README file for a sample of Kafka project with Producer and Consumer and the configurations.

### Spring Application Name
- **Description**: Specifies the name of the Spring Boot application.

#### Kafka Configuration
    - `.\zookeeper-server-start.bat ..\..\config\zookeeper.properties`:  Run the Zookeeper in order to used by Brokers
    - to determine which Broker is the leader of which Partition
    - `.\kafka-server-start.bat ..\..\config\server.properties`: to run Kafka environment running and ready to use
    - `.\kafka-topics.bat --create --topic TOPIC_NAME --bootstrap-server localhost:9092`: to create a Topic through cmdl

## Starting the Application
- **Request**: PUT
- **API**: `http://localhost:8082/cab-locations/update`

### 1. Setting Environment Variables and Running Main Function in CoffeePlaceApplication.java
- Set the required environment
  variables (`KAFKA`, `ZOOKEEPER`, `SPRINGBOOTAPPLICATION`)
  according to your environment and requirements.
- Run the `producer` function in the `DriverApplication.java`.
- Run the `listener` function in the `UserApplication.java`.

```bash
recent-consumer-msgs .\bin/kafka-console-consumer.bat --topic TOPIC_NAME --bootstrap-server localhost:9092
all-consumer-msgs .\bin/kafka-console-consumer.bat --topic TOPIC_NAME --from beginning --bootstrap-server localhost:9092
```