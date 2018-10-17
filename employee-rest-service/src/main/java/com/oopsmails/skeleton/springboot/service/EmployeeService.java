package com.oopsmails.skeleton.springboot.service;

import com.oopsmails.skeleton.springboot.model.Employee;
import java.util.List;

public interface EmployeeService {

    Employee initAdd(Employee employee);

    Employee add(String userId, Employee employee);


    void delete(String userId, Employee employee);

    Employee findById(Long id);


    List<Employee> findAll(String userId);

    List<Employee> findByDepartment(Long departmentId);

    List<Employee> findByOrganization(Long organizationId);
}
