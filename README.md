# spring-security-demo

This project runs on port `9000`.

## Users
| Username | Password | Roles |
| -------- | -------- | ----- |
| admin    | password | ADMIN, USER |
| user     | password | USER |

## H2 In-Memory Database
#### URL
http://localhost:9000/h2-console

#### Required Role
`ADMIN`

#### Connection Details
__JDBC URL:__   `jdbc:h2:mem:testdb`\
__User Name:__  `sa`\
__Password:__   `<blank>`


## Endpoints
| Method | Resource | Required Role | Required Permission |
| ------ | --- | ------------- | ------------------- |
| `GET` | `/admin` | ADMIN |
| `GET` | `/user` | USER |
| `GET` | `/items` | | ITEM_READ |
| `POST` | `/items` | | ITEM_CREATE |
| `PUT` | `/items` | | ITEM_UPDATE |
| `DELETE` | `/items` | | ITEM_DELETE |
