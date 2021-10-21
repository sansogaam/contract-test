package com.jd.spring.cloud.contract.get.configuration;

import com.jd.spring.cloud.contract.get.controller.GetEmployeeController;
import com.jd.spring.cloud.contract.get.repository.DBRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {
    @Bean
    public DBRepository dbRepository()
    {
        return new DBRepository();
    }

    @Bean
    public GetEmployeeController getEmployeeController()
    {
        return new GetEmployeeController();
    }
}