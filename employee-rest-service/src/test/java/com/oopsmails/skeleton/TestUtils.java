package com.oopsmails.skeleton;

import com.oopsmails.skeleton.springboot.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestUtils {
    public static List<Employee> getMockedEmployeeList() {
        List<Employee> result = new ArrayList<>();
        IntStream.range(1, 11).forEach((int i) -> {
            Employee employee = new Employee();
            employee.setId(Long.valueOf(i));
            employee.setName("name " + i);
            result.add(employee);
        });

        return result;
    }
}
