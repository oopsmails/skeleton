package com.oopsmails.skeleton.springboot;

import com.oopsmails.skeleton.springboot.cache.EmployeeServiceMockBeanTest;
import com.oopsmails.skeleton.springboot.controller.EmployeeControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        EmployeeControllerTest.class,
        EmployeeServiceMockBeanTest.class
})
public class EmployeeServiceTestSuite {
}
