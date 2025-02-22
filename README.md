### Film library watchlist (on microservice architecture)

<div id="badges">
  <a href="https://openjdk.org/projects/jdk/17/">
    <img src="https://img.shields.io/badge/Java%3A%20-17%20-%20darkcyan?style=flat&logo=openjdk" alt="JDK Badge"/>
  </a>
  <a href="https://spring.io/blog/2023/09/21/spring-boot-3-1-4-available-now">
    <img src="https://img.shields.io/badge/Spring%20boot%3A%20-3.1.4%20-%20darkcyan?style=flat&logo=Spring" alt="Spring Badge"/>
  </a>
  <a href="https://maven.apache.org/">
    <img src="https://img.shields.io/badge/Apache%20Maven%20-%20darkcyan?style=flat&logo=Apache-Maven" alt="Maven Badge"/>
  </a>
</div>

Приложение состоит из трёх бизнес-микросервисов, по-разному взаимодействующих друг с другом.

1. Микросервис [Film-Server](film-library/film-server) реализует функционал для использования и изменения информации о фильмах в БД.


2. С помощью микросервиса [Watchlist-Server](film-library/watchlist-server) через выделенный UI пользователь может создавать "Списки просмотра", куда он может 
добавлять интересующие его фильмы.


3. Микросервис [Watch-Film-Stat-Server](film-library/watch-film-stat-server) представляет собой подсчет и вывод на UI общей статиcтики добавления того или иного фильма в "Списки просмотра".
В качестве дальнейшего развития может служить для составления и анализа дополнительных метрик.

## Содержание:
- [Технологии](#Технологии)
- [Архитектура](#Архитектура)
- [База данных](#База-данных)
- [Тестирование](#Тестирование)
- [Возможные доработки](#Возможные-доработки)

## Технологии:
- Основной фреймворк: Spring Boot 3.1.4
- Сборщик проектов: Maven
- Spring: Core, MVC, Data JPA, Security, Bean Validation
- СУБД:
    - H2 v.2.2.220 (только для тестирования)
    - PostgreSQL v.13.12
- Тестирование:
    - JUnit 5
    - Mockito-core
    - Spring Boot test
- Frontend:
    - Thymeleaf
    - AJAX
- Взаимодействие микросервисов:
    - Discovery server (Netflix Eureka)
    - Feign client
    - Apache Kafka
- Среда контейнеризации и развертывания
    - Docker
- Другие зависимости:
    - Lombok
    - Map struct
    - Hibernate-validator
    - Docker

## Архитектура:

Данный микросервис связан с 1 через feign-клиент, а с 3 через Kafka

Взаимодействие между [Film-Server](film-library/film-server) и [Watchlist-Server](film-library/watchlist-server) происходит через Feign Client с отправкой
дефолтных значений на случай отказа (паттерн "Circuit Breaker").

Между [Watchlist-Server](film-library/watchlist-server) и [Watch-Film-Stat-Server](film-library/watch-film-stat-server) взаимодействие организовано 
через Kafka в односторонней связи (только Watchlist-Server отправляет данные статистики и никак иначе).

![image](./readme-resources/App%20Arch%20v1_0.PNG)

## База данных:
Основная СУБД в проекте: **PostgreSQL**  
СУБД для тестов: **H2**

ER-диаграммы баз данных (Film-, Watchlist- и Watch-Film-Stat servers):

![image](./readme-resources/DB%20Data%20Structure%20v1_0.PNG)

## Тестирование:
Тесты написаны для всех микросервисов для слоя controller.
В микросервисе [Film-Server](film-library/film-server) также тестами покрыт слой repository.

Для слоя repository написаны интеграционные тесты с использованием H2.  
Для слоя controller используется технология mockMvc.

## Возможные доработки:
- Добавить аутентификацию через JWT;
- Написать тесты для всех модулей;
- Поменять БД для модуля [Watch-Film-Stat-Server](film-library/watch-film-stat-server) с SQL на NoSQL;
- Внедрить больше технологий из Spring Cloud (Gateway, Actuator, Config Server, etc);
- Вынести UI в отдельный микросервис
