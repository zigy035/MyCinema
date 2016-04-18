Instructions:

1) Create new MySQL DB with name 'mycinema_db' (check jdbc.properties)
2) Create database structure - run SQL commands from 'init-structure.sql'
3) Create initial data - run SQL commands from 'init-data.sql'
4) Build project by using maven tool (mvn clean install)
5) Run project (you can use maven-tomcat plugin: mvn tomcat:run)

Application recognizes two types of user: USER and ADMIN.

Login as ADMIN (user: admin, pass: admin) and you are able to create new 
movie broadcasts and generate tickets. Also you can book tickets and check your bookings.

Register new user and log it in. 
This is an ordinary USER, so you can only book tickets and check your bookings.