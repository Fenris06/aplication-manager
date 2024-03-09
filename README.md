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
