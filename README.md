# Ticketing System - Inventory Service

## Overview

This project is part of a distributed ticketing system composed of several microservices:

- **inventoryservice**: Manages ticket inventory and availability.
- **orderservice**: Handles order creation and processing.
- **apigateway**: Entry point for client requests, routing them to appropriate services.
- **bookingservice**: Manages booking requests and coordinates with other services.

## Kafka Integration

Kafka is used as the message broker to enable asynchronous communication between services. Specifically:

- When a booking is made via the `bookingservice`, it publishes a notification/event to a Kafka topic.
- The `orderservice` subscribes to this topic and processes the booking event, creating an order accordingly.

This decouples the services and allows for scalable, reliable event-driven communication.

## Inventory Service Role

The `inventoryservice` is responsible for:

- Tracking available tickets.
- Updating inventory based on bookings and orders.
- Responding to inventory queries from other services.

## Architecture Diagram

```
[Client] -> [apigateway] -> [bookingservice] --(Kafka event)--> [orderservice] -> [inventoryservice]
```

## Running the Services

Each service runs independently and communicates via REST APIs and Kafka topics. Ensure Kafka is running and accessible to all services.

## Dockerized Dependencies

This project uses Docker to manage essential infrastructure components:

- **MySQL**: Used as the primary database for storing ticket, order, and user data.
- **Zookeeper**: Required by Kafka for managing brokers and distributed coordination.
- **Kafka**: Acts as the message broker for inter-service communication.
- **Keycloak**: Provides authentication and authorization for securing the services.

You can use `docker-compose` to start these dependencies easily. Make sure Docker is installed and running on your system.
