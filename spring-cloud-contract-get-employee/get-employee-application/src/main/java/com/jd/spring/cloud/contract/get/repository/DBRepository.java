package com.jd.spring.cloud.contract.get.repository;

import com.jd.spring.cloud.contract.get.model.Employee;

import java.util.Random;

public class DBRepository {

    public Employee getEmployee(String identityCardNo)
    {
        Employee employee = new Employee();

        String id= String.valueOf(Math.abs(new Random().nextInt()));
        employee.setId(id);
        employee.setIdentityCardNo(identityCardNo);
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");
        employee.setStatus("EMPLOYEE_FOUND");

        return employee;
    }
}