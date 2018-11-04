package com.oopsmails.skeleton.springboot.cache;

import com.oopsmails.skeleton.TestUtils;
import com.oopsmails.skeleton.springboot.SpringEmployeeServiceApplication;
import com.oopsmails.skeleton.springboot.model.Employee;
import com.oopsmails.skeleton.springboot.model.PropsObj;
import com.oopsmails.skeleton.springboot.repository.EmployeeRepository;
import com.oopsmails.skeleton.springboot.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                SpringEmployeeServiceApplication.class,
                EmployeeServiceMockBeanTest.EmployeeServiceMockBeanTestConfig.class
        })
public class EmployeeServiceMockBeanTest {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() {
        List<Employee> mockedEmployeeList = TestUtils.getMockedEmployeeList();
        when(employeeRepository.findAll(anyString()))
                .thenReturn(mockedEmployeeList);
    }

    @Test
    public void testFindAllCaching() throws Exception {
        String userId = "userId";

        Cache employeeCache = cacheManager.getCache("employeeCache");
        List<Employee> inCacheList = (List<Employee>) employeeCache.get(userId); // employeeCache not initialized
        assertNull("1. should NOT be in model ... ", inCacheList);

        // the heavy employeeRepository.findAll(userId) is called once, because not cached yet.
        List<Employee> employeeList = employeeService.findAll(userId);
        verify(employeeRepository, times(1)).findAll(userId);

        // check already cached
        inCacheList = (List<Employee>) employeeCache.get(userId).get(); // get from SimpleValueWrapper
        assertNotNull("2. should be in model now", inCacheList);
        assertEquals(10, inCacheList.size());
        assertEquals(inCacheList.size(), employeeList.size());

        // call again, make sure the heavy employeeRepository.findAll(userId) is NOT called again, take from model instead.
        employeeService.findAll(userId);
        verify(employeeRepository, times(1)).findAll(userId); // still be called 1 time
    }

    @Test
    public void testFindAllCachingObjId() throws Exception {
        PropsObj propsObj = new PropsObj();
        propsObj.setFrom("123");
        PropsObj.Credentials credentials = new PropsObj.Credentials();
        credentials.setUsername("cacheuser");
        propsObj.setCredentials(credentials);

//        Cache employeeCache = cacheManager.getCache("employeeCache");
////        List<Employee> inCacheList = (List<Employee>) employeeCache.get(propsObj.getFrom()); // employeeCache not initialized
//        List<Employee> inCacheList = (List<Employee>) employeeCache.get(credentials.getUsername()); // employeeCache not initialized
//        assertNull("1. should NOT be in model ... ", inCacheList);

        // the heavy employeeRepository.findAll(userId) is called once, because not cached yet.
        List<Employee> employeeList = employeeService.findAllByPropsObj(propsObj);
//        verify(employeeRepository, times(1)).findAll(propsObj.getFrom());
        verify(employeeRepository, times(1)).findAll(credentials.getUsername());

        // check already cached
////        inCacheList = (List<Employee>) employeeCache.get(propsObj.getFrom()).get(); // get from SimpleValueWrapper
//        inCacheList = (List<Employee>) employeeCache.get(credentials.getUsername()).get();
//        assertNotNull("2. should be in model now", inCacheList);
//        assertEquals(10, inCacheList.size());
//        assertEquals(inCacheList.size(), employeeList.size());

        // call again, make sure the heavy employeeRepository.findAll(userId) is NOT called again, take from model instead.
//        employeeService.findAllByPropsObj(propsObj);
////        verify(employeeRepository, times(1)).findAll(propsObj.getFrom()); // still be called 1 time
//        verify(employeeRepository, times(1)).findAll(credentials.getUsername()); // still be called 1 time
    }

    @Configuration
    public static class EmployeeServiceMockBeanTestConfig {
        @Bean
        public CacheManager cacheManager() {
            SimpleCacheManager cacheManager = new SimpleCacheManager();
            cacheManager.setCaches(Arrays.asList(
                    new ConcurrentMapCache("otherCache"),
                    new ConcurrentMapCache("employeeCache")
            ));
            return cacheManager;
        }

        @Bean
        public EmployeeRepository employeeRepository() {
            return mock(EmployeeRepository.class);
        }
    }
}
