package com.oopsmails.skeleton.springboot;

import com.oopsmails.skeleton.springboot.model.Employee;
import com.oopsmails.skeleton.springboot.repository.EmployeeRepository;
import com.oopsmails.skeleton.springboot.service.EmployeeService;
import com.oopsmails.skeleton.springboot.service.EmployeeServiceImpl;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class SpringEmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEmployeeServiceApplication.class, args);
    }

    @Bean
    EmployeeRepository repository() {
        EmployeeRepository repository = new EmployeeRepository();
        repository.initAdd(new Employee(1L, 1L, "John Smith", 34, "Analyst"));
        repository.initAdd(new Employee(1L, 1L, "Darren Hamilton", 37, "Manager"));
        repository.initAdd(new Employee(1L, 1L, "Tom Scott", 26, "Developer"));
        repository.initAdd(new Employee(1L, 2L, "Anna London", 39, "Analyst"));
        repository.initAdd(new Employee(1L, 2L, "Patrick Dempsey", 27, "Developer"));
        repository.initAdd(new Employee(2L, 3L, "Kevin Price", 38, "Developer"));
        repository.initAdd(new Employee(2L, 3L, "Ian Scott", 34, "Developer"));
        repository.initAdd(new Employee(2L, 3L, "Andrew Campton", 30, "Manager"));
        repository.initAdd(new Employee(2L, 4L, "Steve Franklin", 25, "Developer"));
        repository.initAdd(new Employee(2L, 4L, "Elisabeth Smith", 30, "Developer"));
        return repository;
    }

    @Bean
    EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

}
