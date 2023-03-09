package com.mango.application.controller

import com.mango.domain.user.model.User
import com.mango.util.ControllerEndpointCaller
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Disabled
class UserControllerTest {

    @Autowired
    private lateinit var userEndpointHelper: UserEndpointHelper

    @Test
    fun `create endpoint creates user`() {
        // given
        val userName = "userName"

        // when
        val actual = userEndpointHelper.callCreateEndpoint(userName)

        // then
        actual.name shouldBeEqualTo userName
    }

    @Test
    fun `current endpoint returns current user`() {
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
    fun callCreateEndpoint(userName: String) = controllerEndpointCaller.call<User>(
        this,
        endpointName = "create",
        method = HttpMethod.POST,
        queryParams = mapOf("userName" to userName),
    )

    fun callCurrentEndpoint() = controllerEndpointCaller.call<User>(
        this,
        endpointName = "current",
        method = HttpMethod.GET,
    )
}
