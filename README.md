# Educational Institution Management

REST API и веб-интерфейс для управления учебными заведениями, преподавателями и студентами.

**Стек:** Java 17, Spring Boot, H2 (встроенная БД в памяти), HTML/JS

**PostgreSQL не используется.** База данных — встроенная H2, ничего устанавливать и настраивать не нужно.

---

## Запуск приложения

### Что нужно установить

- **Java 17** (или выше)
- **Maven**

Проверка:
```bash
java -version
mvn -version
```

### Запуск

В корне проекта выполните:

```bash
mvn spring-boot:run
```

Дождитесь сообщения вроде: `Started InstitutionManagementApplication`.

- **Веб-интерфейс:** http://localhost:8080  
- **API:** http://localhost:8080/api/...  
- **H2 Console (просмотр БД в браузере):** http://localhost:8080/h2-console  
  - JDBC URL: `jdbc:h2:mem:education_db`  
  - User: `sa`  
  - Password: пусто  

---

## Как тестировать API через Postman

**Postman** — это программа для отправки HTTP-запросов к вашему API. Она не заменяет базу данных: приложение уже запущено (с H2 в памяти), а Postman просто отправляет запросы на `http://localhost:8080/api/...` и получает ответы в JSON.

### Подключение в Postman

1. **Установите Postman:** https://www.postman.com/downloads/
2. **Запустите приложение** (см. выше): `mvn spring-boot:run`
3. **Базовый адрес API:** `http://localhost:8080`
4. Создайте коллекцию (например, «EduManager API») и добавляйте запросы с URL вида: `http://localhost:8080/api/...`

### Примеры запросов

| Метод | URL | Описание |
|-------|-----|----------|
| GET | `http://localhost:8080/api/institutions` | Список заведений |
| POST | `http://localhost:8080/api/institutions` | Создать заведение (Body → raw → JSON) |
| GET | `http://localhost:8080/api/teachers` | Список преподавателей |
| POST | `http://localhost:8080/api/teachers` | Создать преподавателя |
| GET | `http://localhost:8080/api/students` | Список студентов |
| GET | `http://localhost:8080/api/students?search=John` | Поиск по имени |
| GET | `http://localhost:8080/api/students?sortBy=grade&order=desc` | Сортировка |
| POST | `http://localhost:8080/api/students` | Создать студента |
| GET | `http://localhost:8080/api/reports/summary` | Аналитика по заведениям |
| POST | `http://localhost:8080/api/auth/register` | Регистрация пользователя |

Для **POST** и **PUT** во вкладке **Body** выберите **raw** и **JSON**, затем введите JSON, например:

```json
{
  "name": "School #1",
  "address": "Street 1, City"
}
```

**Подробная инструкция по Postman** (установка, коллекция, переменные, все эндпоинты с примерами) — в файле **[POSTMAN.md](POSTMAN.md)**.

---

## Настройка почты (по желанию)

Чтобы при регистрации отправлялось письмо на email, в `src/main/resources/application.properties` укажите SMTP (например, Gmail):

```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
app.mail.from=your-email@gmail.com
```

Без настройки регистрация работает; письмо просто не отправляется.

---

## Структура проекта

Подробнее — в [ARCHITECTURE.md](ARCHITECTURE.md).

---

## Кратко: что к чему

- **База данных:** встроенная H2 в памяти. PostgreSQL не нужен.
- **Postman:** клиент для тестирования API. Вы отправляете запросы на `http://localhost:8080/api/...` и смотрите ответы. «Подключение Postman» — это просто указание этого URL в запросах.
- **Веб-интерфейс:** http://localhost:8080 — формы и таблицы для тех же операций.
