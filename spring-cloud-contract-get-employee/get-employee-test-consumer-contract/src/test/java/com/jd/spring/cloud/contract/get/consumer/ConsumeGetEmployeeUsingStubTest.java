package com.jd.spring.cloud.contract.get.consumer;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ConsumeGetEmployeeUsingStubTest.AutoConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
        ids = {"com.jd.spring:get-employee-test-provider-contract:${version:+}"}
        , stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ConsumeGetEmployeeUsingStubTest {

    @Autowired
    StubFinder stubFinder;

    private String baseURL=null;

    @Before
    public void setup() {

        // expect: 'WireMocks are running'
        then(stubFinder.findStubUrl("com.jd.spring", "get-employee-test-provider-contract")).isNotNull();

        // and: 'Stub is running'
        then(stubFinder.findAllRunningStubs().isPresent("get-employee-test-provider-contract")).isTrue();

        // and: 'Stubs were registered and make an actual call to the wiremock stub'
        baseURL = stubFinder.findStubUrl("get-employee-test-provider-contract").toString() + "/employee";
    }

    @Test
    public void testGetExistingEmployeeUsingStub() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", APPLICATION_JSON_VALUE);
        headers.set("Content-Type", APPLICATION_JSON_VALUE);
        ResponseEntity<String> response =new RestTemplate()
                .exchange(baseURL+"/1234567890", GET, new HttpEntity<>(null,headers), String.class);

        System.out.println("testGetExistingEmployeeUsingStub: "+ response.getBody());
        // then:
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json.*"));

        System.out.println(response.getBody());

        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody());
        assertThatJson(parsedJson).field("['id']").matches("[1-9][0-9]{0,}");
        assertThatJson(parsedJson).field("['firstName']").matches("[\\p{L}]*");
        assertThatJson(parsedJson).field("['lastName']").matches("[\\p{L}]*");
        assertThatJson(parsedJson).field("['identityCardNo']").isEqualTo(1234567890);
        assertThatJson(parsedJson).field("['status']").matches("EMPLOYEE_FOUND");
    }

    @Test
    public void testGetNotExistingEmployeeUsingStub() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", APPLICATION_JSON_VALUE);
        headers.set("Content-Type", APPLICATION_JSON_VALUE);
        ResponseEntity<String> response =new RestTemplate()
                .exchange(baseURL+"/0123456789", GET, new HttpEntity<>(null,headers), String.class);

        System.out.println("testGetNotExistingEmployeeUsingStub: "+ response.getBody());

        // then:
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json.*"));

        System.out.println(response.getBody());

        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody());
        assertThatJson(parsedJson).field("['status']").matches("EMPLOYEE_NOT_FOUND");
    }

    @SpringBootConfiguration
    static class AutoConfig{}
}
