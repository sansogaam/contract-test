package com.jd.spring.cloud.contract.create;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = CreateEmployeeApplication.class)
@AutoConfigureStubRunner(
        ids = {"com.jd.spring:get-employee-test-provider-contract:8180"}
        , stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public abstract class BaseClass {

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

}