# Application-manager
Данный сервис предназначен для регистрации и обработки пользовательских заявок. У заявок есть четыре статуса: SENT, DRAFT, ACCEPTED, REJECTED. В приложеие есть три роли: USER, ADMIN, OPERATOR. 

* **Функции роли USER**
* логиниться в систему (выдача jwt и access токенов) http://localhost:8080/login
* выходить из системы  http://localhost:8080/logout (в стадии разработки)
* создавать заявки http://localhost:8080/applications/users/:id (статус заявки SENT или DRAFT)
* создавать черновики http://localhost:8080/applications/users/:id (статус заявки DRAFT)
* просматривать созданные им заявки с возможностью сортировки по дате создания в оба направления (как от самой старой к самой новой, так и наоборот) и пагинацией по 5 элементов http://localhost:8080/applications/users/:id?sort=DESC
* редактировать созданные им заявки в статусе DRAFT http://localhost:8080/applications/users/:userId/update/:applicationId 
* отправлять заявки на рассмотрение оператору http://localhost:8080/applications/users/:id (если статус заявки SENT) или http://localhost:8080/applications/users/:userId/update/:applicationId (изменение статусза заявки с DRAFT на SENT)

* **Функции роли ADMIN**
* логиниться в систему (выдача jwt и access токенов) http://localhost:8080/login
* выходить из системы http://localhost:8080/logout (в стадии разработки)
* смотреть список пользователей http://localhost:8080/admin/person
* смотреть заявки в статусе SENT, ACCEPTED, REJECTED. Пагинация 5 элементов, сортировка по дате. Фильтрация по имени. http://localhost:8080/applications/admin?name=Thor&status=SENT&status=ACCEPTED&status=REJECTED&sort=DESC
* назначать пользователям права оператора http://localhost:8080/admin/person?ids=3&ids=4&ids=5

* **Функции роли OPERATOR** 
* логиниться в систему (выдача jwt и access токенов) http://localhost:8080/login
* выходить из системы http://localhost:8080/logout (в стадии разработки)
* смотреть все отправленные на рассмотрение заявки (статус SENT) с возможностью сортировки по дате создания в оба направления (как от самой старой к самой новой, так и наоборот) и пагинацией по 5 элементов. Должна быть фильтрация по имени. Просматривать отправленные заявки только конкретного пользователя по его имени/части имени (у пользователя, соответственно, должно быть поле name) http://localhost:8080/applications/operator?name=Tho&sort=DESC 
* смотреть заявку по id http://localhost:8080/applications/operator/:applicationId (статус SENT)
* принимать заявки http://localhost:8080/applications/operator/update (можно принимать от 1 до 5 заяваок за раз. Нужно указать в теле запроса статус заявки ACCEPTED);
* отклонять заявки http://localhost:8080/applications/operator/update (можно отклонить от 1 до 5 заявок за раз. Нужно указать в теле запроса статус заявки REJECTED);
