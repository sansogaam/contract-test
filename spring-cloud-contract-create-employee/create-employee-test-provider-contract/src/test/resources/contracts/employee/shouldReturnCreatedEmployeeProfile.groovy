package contracts.employee

import org.springframework.cloud.contract.spec.Contract


Contract.make {
    description "should create an employee profile for given details"

    request {
        method(POST())

        urlPath("/employee/") {
        }

        headers {
            contentType(applicationJson())
            accept(applicationJson())
        }

        body(
                "firstName": anyAlphaUnicode(),
                "lastName": anyAlphaUnicode(),
                "identityCardNo": $(
                        consumer(regex("[1-9][0-9]{0,}"))
                        , producer("1234567890"))
        )
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                "id": "${(regex('[1-9][0-9]{0,}'))}",
                "firstName": anyAlphaUnicode(),
                "lastName": anyAlphaUnicode(),
                "identityCardNo": $(fromRequest().body('$.identityCardNo')),
                "status": "EMPLOYEE_FOUND"
        )

    }
}