package com.jd.spring.cloud.contract.get.controller;

import com.jd.spring.cloud.contract.get.model.Employee;
import com.jd.spring.cloud.contract.get.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.springframework.http.HttpMethod.GET;

@RestController
public class GetEmployeeController {

	@Autowired
	private DBRepository dbRepository;

	@RequestMapping(value="/employee/{identityCardNo}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable String identityCardNo)
	{
		if (!identityCardNo.startsWith("0")){
			return new ResponseEntity<Employee>(dbRepository.getEmployee(identityCardNo), HttpStatus.OK);
		}

		Employee employee = new Employee();
		employee.setStatus("EMPLOYEE_NOT_FOUND");

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);

	}
}
