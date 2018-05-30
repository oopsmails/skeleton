package com.oopsmails.skeleton.springboot.controller;

import com.oopsmails.skeleton.springboot.model.Employee;
import com.oopsmails.skeleton.springboot.model.PropsObj;
import com.oopsmails.skeleton.springboot.model.PropsResourceObj;
import com.oopsmails.skeleton.springboot.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeRepository repository;

    private PropsObj propsObj;

    private PropsResourceObj propsResourceObj;

    @Autowired
    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setPropsObj(PropsObj propsObj) {
        this.propsObj = propsObj;
    }

    @Autowired
    public void setPropsResourceObj(PropsResourceObj propsResourceObj) {
        this.propsResourceObj = propsResourceObj;
    }

    @PostMapping("/")
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return repository.add(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        LOGGER.info("========Testing @ConfigurationProperties, propsResourceObj.getEmail() = {}",
                propsResourceObj.getEmail());

        LOGGER.info("========Testing @ConfigurationProperties, propsObj.getHost() = {}", propsObj.getHost());
        LOGGER.info("========Testing @ConfigurationProperties, propsObj.getHost() = {}",
                propsObj.getCredentials().getAuthMethod());
        return repository.findById(id);
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return repository.findAll();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }

}
