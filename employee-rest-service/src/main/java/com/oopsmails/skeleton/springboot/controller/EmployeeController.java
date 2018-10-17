package com.oopsmails.skeleton.springboot.controller;

import com.oopsmails.skeleton.springboot.config.HazelcastCustomProperties;
import com.oopsmails.skeleton.springboot.model.Employee;
import com.oopsmails.skeleton.springboot.model.PropsObj;
import com.oopsmails.skeleton.springboot.model.PropsResourceObj;
import com.oopsmails.skeleton.springboot.service.EmployeeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@Slf4j
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

//	private EmployeeRepository repository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PropsObj propsObj;

    @Autowired
    private PropsResourceObj propsResourceObj;

    @Autowired
    private HazelcastCustomProperties hazelcastCustomProperties;

//	@Autowired
//	public void setRepository(EmployeeRepository repository) {
//		this.repository = repository;
//	}
//
//	@Autowired
//	public void setPropsObj(PropsObj propsObj) {
//		this.propsObj = propsObj;
//	}
//
//	@Autowired
//	public void setPropsResourceObj(PropsResourceObj propsResourceObj) {
//		this.propsResourceObj = propsResourceObj;
//	}

    @PostMapping("")
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return employeeService.add("userId", employee);
    }

    @DeleteMapping("")
    public void delete(@RequestBody Employee employee) {
        LOGGER.info("Employee delete: {}", employee);
        employeeService.delete("userId", employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        LOGGER.info("========Testing @ConfigurationProperties, propsResourceObj.getEmail() = {}",
                propsResourceObj.getEmail());

        LOGGER.info("========Testing @ConfigurationProperties, propsObj.getHost() = {}",
                propsObj.getHost());
        LOGGER.info("========Testing @ConfigurationProperties, propsObj.getCredentials().getAuthMethod() = {}",
                propsObj.getCredentials().getAuthMethod());
        LOGGER.info("========Testing @ConfigurationProperties, propsObj.getAdditionalHeaders() = {}",
                propsObj.getAdditionalHeaders().get("redelivery"));

        log.info("========Testing @ConfigurationProperties, hazelcastCustomProperties.getPort() = {}",
                hazelcastCustomProperties.getPort());
        log.info("========Testing @ConfigurationProperties, hazelcastCustomProperties.getMulticastGroup() = {}",
                hazelcastCustomProperties.getMulticastGroup());

        return employeeService.findById(id);
    }

    @GetMapping("")
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        // for testing model, user id added
        return employeeService.findAll("userId");
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return employeeService.findByDepartment(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId);
        return employeeService.findByOrganization(organizationId);
    }

}
