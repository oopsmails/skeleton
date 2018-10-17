package com.oopsmails.skeleton.springboot.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.oopsmails.skeleton.springboot.SpringEmployeeServiceApplication;
import com.oopsmails.skeleton.springboot.model.Employee;
import com.oopsmails.skeleton.springboot.repository.EmployeeRepository;
import com.oopsmails.skeleton.springboot.service.EmployeeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
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

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                SpringEmployeeServiceApplication.class,
                EmployeeServiceMockBeanTest.EmployeeServiceMockBeanTestConfig.class
        })
public class EmployeeServiceMockBeanTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Before
    public void setup() {
        List<Employee> mockedRes = new ArrayList<>();
        IntStream.range(1, 6).forEach((int i) -> {
            Employee employee = new Employee();
            employee.setId(Long.valueOf(i));
            employee.setName("name " + i);
            mockedRes.add(employee);
        });
        when(employeeRepository.findAll(anyString()))
                .thenReturn(mockedRes);
    }

    @Test
    public void testGetDefunctSecuritiesForAccountCaching() throws Exception {
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
        assertEquals(5, inCacheList.size());
        assertEquals(inCacheList.size(), employeeList.size());

        // call again, make sure the heavy employeeRepository.findAll(userId) is NOT called again, take from model instead.
        employeeService.findAll(userId);
        verify(employeeRepository, times(1)).findAll(userId); // still be called 1 time
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
