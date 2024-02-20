docker run --name todo_mysql -d -p 3322:3306 \
-e MYSQL_DATABASE=todo_db \
-e MYSQL_ROOT_PASSWORD=1234 mysql:latest
