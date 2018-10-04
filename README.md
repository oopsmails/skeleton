# skeleton

Spring boot applications

## employee-rest-service

Spring boot backend and Angular frontend

###### run SpringEmployeeServiceApplication

-- GET: localhost:18080/employee

-- GET: localhost:18080/employee/1

-- POST: localhost:18080/employee
```
{
    "organizationId": 1,
    "departmentId": 1,
    "name": "John Smith",
    "age": 34,
    "position": "Analyst"
}
```
-- In log, will see `@PropertySource` test.

>2018-10-04 09:10:34.708  INFO 13060 --- [io-18080-exec-1] c.o.s.s.controller.EmployeeController    : Employee find: id=1
 2018-10-04 09:10:34.708  INFO 13060 --- [io-18080-exec-1] c.o.s.s.controller.EmployeeController    : ========Testing @ConfigurationProperties, propsResourceObj.getEmail() = aaa@abc.com
 2018-10-04 09:10:34.708  INFO 13060 --- [io-18080-exec-1] c.o.s.s.controller.EmployeeController    : ========Testing @ConfigurationProperties, propsObj.getHost() = mailer@mail.com
 2018-10-04 09:10:34.708  INFO 13060 --- [io-18080-exec-1] c.o.s.s.controller.EmployeeController    : ========Testing @ConfigurationProperties, propsObj.getHost() = SHA1



## javatest

Spring boot with useful tests/unittests




