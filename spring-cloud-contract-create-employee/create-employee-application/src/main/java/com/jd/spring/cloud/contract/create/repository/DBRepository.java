package com.jd.spring.cloud.contract.create.repository;

import com.jd.spring.cloud.contract.create.model.Employee;

import java.util.Random;

public class DBRepository {

    public Employee createEmployee(Employee emp)
    {
        String id= String.valueOf(Math.abs(new Random().nextInt()));
        emp.setId(id);
        emp.setStatus("NEW_EMPLOYEE_CREATED");

        return emp;
    }
}
