# Kafka producer and consumer sample

This project contains a Spring Boot Kafka producer (`book-driver`) and consumer
(`book-user`).

## Run Kafka locally

The repository uses Kafka in **KRaft mode**, so ZooKeeper is not required.
Docker Desktop must be running.

```powershell
docker compose up -d
```

Kafka is available at `localhost:9092`. The `book-driver` application creates
the following topics, each with three partitions:

| Topic | Purpose |
| --- | --- |
| `cab-location` | Cab location updates |
| `cab-order` | Cab order events |
| `cab-status` | Cab status events |

Kafka preserves message order **within one partition**, not across all
partitions. When events for the same cab or order must remain ordered, send
them with the same Kafka key so they are routed to the same partition.

To stop Kafka:

```powershell
docker compose down
```

## Run the applications

Start the applications from separate terminals:

```powershell
cd book-user
.\mvnw.cmd spring-boot:run
```

```powershell
cd book-driver
.\mvnw.cmd spring-boot:run
```

The producer API is available at:

```text
PUT http://localhost:8082/cab-locations/update
```

## Inspect messages

With Kafka running, use the Kafka command-line tools:

```powershell
.\bin\kafka-console-consumer.bat --topic cab-location --bootstrap-server localhost:9092
.\bin\kafka-console-consumer.bat --topic cab-location --from-beginning --bootstrap-server localhost:9092
.\bin\kafka-topics.bat --list --bootstrap-server localhost:9092
.\bin\kafka-topics.bat --describe --topic cab-order --bootstrap-server localhost:9092
```

## Tests

`book-user` integration tests use Spring Kafka's embedded broker with a
dynamically assigned port. They do not require Docker, Kafka, or ZooKeeper.

```powershell
cd book-user
.\mvnw.cmd test
```
