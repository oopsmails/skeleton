package com.oopsmails.skeleton.springboot.repository;

import com.oopsmails.skeleton.springboot.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@CacheConfig(cacheNames = "employeeRepositoryCache") // working as well, but hard to test without Service
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    // only for initializing
    public Employee initAdd(Employee employee) {
        employee.setId((long) (employees.size() + 1));
        employees.add(employee);
        return employee;
    }

    //	@CacheEvict(key = "#userId")
    public Employee add(String userId, Employee employee) {
        employee.setId((long) (employees.size() + 1));
        employees.add(employee);
        return employee;
    }

    //	@CacheEvict(key = "#userId", beforeInvocation = true)
    public void delete(String userId, Employee employee) {
        employees.remove(employee);
    }

    public Employee findById(Long id) {
        Optional<Employee> employee = employees.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }

    //	@Cacheable(key = "#userId")
    public List<Employee> findAll(String userId) {
        return employees;
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employees.stream().filter(a -> a.getDepartmentId().equals(departmentId)).collect(Collectors.toList());
    }

    public List<Employee> findByOrganization(Long organizationId) {
        return employees.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toList());
    }

}
