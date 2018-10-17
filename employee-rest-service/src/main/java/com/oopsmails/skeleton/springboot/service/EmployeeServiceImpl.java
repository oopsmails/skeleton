package com.oopsmails.skeleton.springboot.service;

import com.oopsmails.skeleton.springboot.model.Employee;
import com.oopsmails.skeleton.springboot.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "employeeCache")
public class EmployeeServiceImpl implements EmployeeService {
    public static String globalUserId = "userId";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee initAdd(Employee employee) {
        return employeeRepository.initAdd(employee);
    }
    @Override
    @CacheEvict(key = "#userId")
    public Employee add(String userId, Employee employee) {
        return employeeRepository.add(userId, employee);
    }
    @Override
    @CacheEvict(key = "#userId", beforeInvocation = true)
    public void delete(String userId, Employee employee) {
        employeeRepository.delete(userId, employee);
    }
    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id);
    }
    @Override
    @Cacheable(key = "#userId")
    public List<Employee> findAll(String userId) {
        return employeeRepository.findAll(userId);
    }
    @Override
    public List<Employee> findByDepartment(Long departmentId) {
        return employeeRepository.findByDepartment(departmentId);
    }
    @Override
    public List<Employee> findByOrganization(Long organizationId) {
        return employeeRepository.findByOrganization(organizationId);
    }
}
