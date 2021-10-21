package com.jd.spring.cloud.contract.create.configuration;

import com.jd.spring.cloud.contract.create.controller.CreateEmployeeController;
import com.jd.spring.cloud.contract.create.repository.DBRepository;
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
    public CreateEmployeeController createEmployeeController()
    {
        return new CreateEmployeeController();
    }
}
