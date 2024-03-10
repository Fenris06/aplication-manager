# Application-manager
Данный сервис предназначен для регистрации и обработки пользовательских заявок. У заявок есть четыре статуса: SENT, DRAFT, ACCEPTED, REJECTED. В приложение есть три роли: USER, ADMIN, OPERATOR. 

 **Функции роли USER**
* логиниться в систему (выдача jwt и access токенов) http://localhost:8080/login
* выходить из системы  http://localhost:8080/logout 
* создавать заявки http://localhost:8080/applications/users/:id (статус заявки SENT или DRAFT)
* создавать черновики http://localhost:8080/applications/users/:id (статус заявки DRAFT)
* просматривать созданные им заявки с возможностью сортировки по дате создания в оба направления (как от самой старой к самой новой, так и наоборот) и пагинацией по 5 элементов http://localhost:8080/applications/users/:id?sort=DESC
* редактировать созданные им заявки в статусе DRAFT http://localhost:8080/applications/users/:userId/update/:applicationId 
* отправлять заявки на рассмотрение оператору http://localhost:8080/applications/users/:id (если статус заявки SENT) или http://localhost:8080/applications/users/:userId/update/:applicationId (изменение статуса заявки с DRAFT на SENT)

 **Функции роли ADMIN**
* логиниться в систему (выдача jwt и access токенов) http://localhost:8080/login
* выходить из системы http://localhost:8080/logout 
* смотреть список пользователей http://localhost:8080/admin/person
* смотреть заявки в статусе SENT, ACCEPTED, REJECTED. Пагинация 5 элементов, сортировка по дате. Фильтрация по имени. http://localhost:8080/applications/admin?name=Thor&status=SENT&status=ACCEPTED&status=REJECTED&sort=DESC
* назначать пользователям права оператора http://localhost:8080/admin/person?ids=3&ids=4&ids=5

 **Функции роли OPERATOR** 
* логиниться в систему (выдача jwt и access токенов) http://localhost:8080/login
* выходить из системы http://localhost:8080/logout 
* смотреть все отправленные на рассмотрение заявки (статус SENT) с возможностью сортировки по дате создания в оба направления (как от самой старой к самой новой, так и наоборот) и пагинацией по 5 элементов. Должна быть фильтрация по имени. Просматривать отправленные заявки только конкретного пользователя по его имени/части имени (у пользователя, соответственно, должно быть поле name) http://localhost:8080/applications/operator?name=Tho&sort=DESC 
* смотреть заявку по id http://localhost:8080/applications/operator/:applicationId (статус SENT)
* принимать заявки http://localhost:8080/applications/operator/update (можно принимать от 1 до 5 заявок за раз. Нужно указать в теле запроса статус заявки ACCEPTED);
* отклонять заявки http://localhost:8080/applications/operator/update (можно отклонить от 1 до 5 заявок за раз. Нужно указать в теле запроса статус заявки REJECTED);

 **Работа с приложением**:
 Для удобства можно загрузить тесты для postman из папки aplication-manager\postman\Application-manager.postman_collection.json. В системе загружено шесть пользователей. У всех пользователей пароль 123. Посмотреть список загруженных пользователей и их роли можно папки с миграциями базы данных aplication-manager\src\main\resources\db\migration.
* Для работы в роли User можно залогинится под именем Odin пароль 123. Для этого в postman тестах есть папка Autorization. В теле запроса нужно заполнить поля и отправить запрос. В ответе в hedars Authorization придет ваш токен. Далее для роли User доступна пака с тестами User Applications. Перед запуском теста необходимо выбрать авторизацию через Barer Token и указать ваш токен.
* Для работы в роли Admin залогинится под именем Thor пароль 123 . Далее действовать аналогично роли User. Для роли Admin доступны папки Admin Users и  Admin Application.
* Для работы в роли Operator залогинится под именем Loki пароль 123 . Далее действовать аналогично роли User. Для роли Operator доступны папки Operator Application.

**Стэк технологий**  
* Java 17
* Springboot 3.2.3
* Spring security
* io.jsonwebtoken
* Hibernate
* Flyway
* Lombok
* PostgreSql
* H2 database for test
* Maven
* Mockito
* JUnit 5

**схема базы данных**

  ![database diagram](https://github.com/Fenris06/application-manager/blob/master/application-db%20-%20public.png)
