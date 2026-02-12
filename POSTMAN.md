
### Institutions (заведения)

| Метод | URL | Описание |
|-------|-----|----------|
| GET | `http://localhost:8080/api/institutions` | Список всех заведений |
| GET | `http://localhost:8080/api/institutions/1` | Заведение по ID |
| POST | `http://localhost:8080/api/institutions` | Создать заведение |
| PUT | `http://localhost:8080/api/institutions/1` | Обновить заведение |
| DELETE | `http://localhost:8080/api/institutions/1` | Удалить заведение |



### Teachers (преподаватели)

| Метод | URL |
|-------|-----|
| GET | `http://localhost:8080/api/teachers` |
| GET | `http://localhost:8080/api/teachers?institutionId=1` |
| POST | `http://localhost:8080/api/teachers` |
| PUT | `http://localhost:8080/api/teachers/1` |
| DELETE | `http://localhost:8080/api/teachers/1` |



### Students (студенты)

| Метод | URL |
|-------|-----|
| GET | `http://localhost:8080/api/students` |
| GET | `http://localhost:8080/api/students?search=John` |
| GET | `http://localhost:8080/api/students?sortBy=name&order=asc` |
| GET | `http://localhost:8080/api/students?sortBy=grade&order=desc` |
| POST | `http://localhost:8080/api/students` |
| PUT | `http://localhost:8080/api/students/1` |
| DELETE | `http://localhost:8080/api/students/1` |


### Reports (аналитика)

| Метод | URL |
|-------|-----|
| GET | `http://localhost:8080/api/reports/summary` |



### Регистрация (Auth)

| Метод | URL |
|-------|-----|
| POST | `http://localhost:8080/api/auth/register` |




