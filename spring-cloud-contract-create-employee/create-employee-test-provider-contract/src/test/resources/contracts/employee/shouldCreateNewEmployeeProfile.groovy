package contracts.employee

import org.springframework.cloud.contract.spec.Contract


Contract.make {
    description "should return already created employee profile for given details"

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
                        consumer(regex("[0][0-9]{0,}"))
                        , producer("0123456789"))
        )
    }

    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
        body(
                "id": "${(regex('[1-9][0-9]{0,}'))}",
                "firstName": $(fromRequest().body('$.firstName')),
                "lastName": $(fromRequest().body('$.lastName')),
                "identityCardNo": $(fromRequest().body('$.identityCardNo')),
                "status": "NEW_EMPLOYEE_CREATED"
        )

    }
}