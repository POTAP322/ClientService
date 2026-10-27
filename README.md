# Client Service

REST API для учёта клиентов компании «Лига Цифровой Экономики».

## Стек

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Swagger (SpringDoc OpenAPI)

## Запуск

### 1. Поднять PostgreSQL

```bash
docker run --name client-db \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=client_db \
  -p 5433:5432 -d postgres:16
```

### 2. Запустить приложение

```bash
mvn spring-boot:run
```

Приложение стартует на `http://localhost:8080`.

### 3. Swagger UI

```
http://localhost:8080/swagger-ui.html
```

## Эндпоинты

| Метод  | Путь                | Описание              | Успех | Ошибки |
|--------|---------------------|-----------------------|-------|--------|
| POST   | /api/clients        | Создать клиента       | 201   | —      |
| GET    | /api/clients        | Список всех клиентов  | 200   | —      |
| GET    | /api/clients/{id}   | Клиент по id          | 200   | 404    |
| PUT    | /api/clients/{id}   | Обновить клиента      | 200   | 404    |
| DELETE | /api/clients/{id}   | Удалить клиента       | 204   | 404    |

## Пример запроса

```json
POST /api/clients
{
  "fullName": "Ivan Ivanov",
  "gender": "M"
}
```

Ответ:

```json
201 Created
{
  "id": 1,
  "fullName": "Ivan Ivanov",
  "gender": "M",
  "status": "ACTIVE",
  "createDttm": "2026-10-28T10:00:00+03:00",
  "modifyDttm": "2026-10-28T10:00:00+03:00"
}
```

## Модель данных

Таблица `client_account`:

| Колонка       | Тип                        | Ограничения           |
|---------------|----------------------------|-----------------------|
| id            | BIGINT                     | PK, not null          |
| full_name     | TEXT                       | not null              |
| gender        | VARCHAR(1)                 | nullable (M / W)      |
| status        | VARCHAR(20)                | ACTIVE / INACTIVE     |
| create_dttm   | TIMESTAMP WITH TIME ZONE   | not null              |
| modify_dttm   | TIMESTAMP WITH TIME ZONE   | not null              |

Схема создаётся автоматически из `src/main/resources/schema.sql` при старте приложения (`spring.sql.init.mode=always`). Hibernate работает в режиме `validate` — проверяет, что сущность соответствует таблице, но не меняет её.

## Архитектура

```
Controller  →  Service  →  Repository  →  PostgreSQL
```

- **Controller** — принимает HTTP, валидирует DTO, делегирует в сервис.
- **Service** — бизнес-логика: статус `ACTIVE` по умолчанию, проверка существования клиента, выброс `ClientNotFoundException`.
- **Repository** — Spring Data JPA, доступ к БД.
- **GlobalExceptionHandler** — централизованная обработка `ClientNotFoundException` → HTTP 404.