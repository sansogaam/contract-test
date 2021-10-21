package com.jd.spring.cloud.contract.create.controller;

import com.jd.spring.cloud.contract.create.model.Employee;
import com.jd.spring.cloud.contract.create.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@RestController
public class CreateEmployeeController {

	@Value("${location.getEmployee.url:http://localhost:8180}")
	private String location;

	@Autowired
	private DBRepository dbRepository;

	@RequestMapping(value= "/employee", method = RequestMethod.POST)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type","application/json");
		headers.add("accept","application/json");

		ResponseEntity<Employee> response = restTemplate
				.exchange(location+"/employee/"+employee.getIdentityCardNo(), GET, new HttpEntity<>(headers), Employee.class);

		if("EMPLOYEE_FOUND".equals(response.getBody().getStatus())){

			return response;
		}

		return new ResponseEntity<Employee>(dbRepository.createEmployee(employee), HttpStatus.CREATED);

	}
}
