<h1 align="center">YANAO Rental Service Backend</h1>

<div align="center">

[![Build Status](https://img.shields.io/badge/jdk-15-orange)](https://www.oracle.com/java/technologies/javase/15-relnote-issues.html)
[![Build Status](https://img.shields.io/badge/spring-2.4.5-brightgreen)](https://spring.io)
[![Build Status](https://img.shields.io/badge/postgres-13.2-blue)](https://www.postgresql.org****)

</div>

## Table of Contents

- [Introduction](#introduction)
- [Technology stack](#technology-stack)
- [Configuration](#configuration)

## Introduction

This is a Spring Boot Web application for renting equipment and inventory in
YANAO (Yamalo-Nenets Autonomous Okrug).

## Technology stack

- Java 15
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Liquibase migration
- JSON Schemas
- Docker
- Swagger
- Lombok
- Maven

## Configuration

To set up a configuration you have to add values to Environment variables.

### Configuration Values

- `SPRING_DATASOURCE_URL` - Postgres database url
- `SPRING_DATASOURCE_USERNAME` - Postgres database username
- `SPRING_DATASOURCE_PASSWORD` - Postgres database password

## Copyright

Copyright © 2021 by [LookFor Inc](https://github.com/LookFor-Inc)
