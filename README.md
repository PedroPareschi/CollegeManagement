# College Management Project

### Rest API made in Java with Spring Boot simulating a College Management system





### Documentation: https://school-management-pareschi.herokuapp.com/swagger-ui.html



## Roles



Any person can find colleges, programs and courses without need for verification.



That are four roles, with the following use cases:



1. Student: can subscribe to classes, check and edit some basic information and print academic record.



2. Teacher: can create classes, change students scores and absences count and change basic information about themselves



3. CollegeAdmin: it's the college administrator.  All the above, but also can accept or reject students subscriptions, change sensitive information, create new programs and courses, 
and register new students and teachers.



4. Administrator: all the above, and register new colleges and college administrators.



## Entities

Every college has its programs, and each program has its required courses. Each course has requisites when they are necessary.

Each course has its classes. Each class has its teacher and students. Also, they must have at least one classroom and time.

Students have their basic information, GPA and overall situation (active, paused...). They also have terms which last one semester.
The terms are how students subscribe to classes. By them, they can get the score, absences and situation (failed, passed or subscribed).

