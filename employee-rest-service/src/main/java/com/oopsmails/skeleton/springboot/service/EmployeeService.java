package com.oopsmails.skeleton.springboot.service;

import com.oopsmails.skeleton.springboot.model.Employee;
import com.oopsmails.skeleton.springboot.model.PropsObj;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface EmployeeService {

    Employee initAdd(Employee employee);

    Employee add(String userId, Employee employee);


    void delete(String userId, Employee employee);

    Employee findById(Long id);


    List<Employee> findAll(String userId);

    @Cacheable(key = "#propsObj.from")
    List<Employee> findAllByPropsObj(PropsObj propsObj);

    List<Employee> findByDepartment(Long departmentId);

    List<Employee> findByOrganization(Long organizationId);
}
