package com.mango.presentation.controller

import com.mango.business.model.User
import com.mango.util.ControllerEndpointCaller
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private lateinit var userEndpointHelper: UserEndpointHelper

    @Test
    fun `create endpoint creates task`() {
        // when
        val actual = userEndpointHelper.callCurrentEndpoint()

        // then
        actual shouldNotBeEqualTo null
    }
}

@Service
class UserEndpointHelper(
    private var controllerEndpointCaller: ControllerEndpointCaller,
) {
    fun callCurrentEndpoint() = controllerEndpointCaller.call<User>(
        this,
        endpointName = "current",
        method = HttpMethod.GET,
    )
}
