package com.mango.application.controller

import com.mango.domain.common.model.Color
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.ProjectId
import com.mango.domain.project.model.request.CreateProjectRequestModel
import com.mango.util.ControllerEndpointCaller
import com.mango.util.Json.encodeToString
import org.amshove.kluent.shouldBeEqualTo
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
class ProjectControllerTest {

    @Autowired
    private lateinit var projectEndpointHelper: ProjectEndpointHelper

    @Test
    fun `create endpoint project`() {
        // given
        val project = projectEndpointHelper.callCreateEndpoint()

        // when
        val actual = projectEndpointHelper.callGetEndpoint(project.id)

        // then
        actual shouldBeEqualTo project
    }
}

@Service
class ProjectEndpointHelper(
    private var controllerEndpointCaller: ControllerEndpointCaller,
) {

    fun callCreateEndpoint(name: String? = "project"): Project {
        val requestModel = CreateProjectRequestModel(
            name = name ?: "name",
            isFavourite = true,
            color = Color("0xFF0000"),
        )

        val jsonBody = encodeToString(requestModel)
        return controllerEndpointCaller.call(
            this,
            endpointName = "create",
            method = HttpMethod.POST,
            body = jsonBody,
        )
    }

    fun callGetEndpoint(projectId: ProjectId) = controllerEndpointCaller.call<Project>(
        this,
        endpointName = "get",
        method = HttpMethod.GET,
        queryParams = mapOf("projectId" to projectId.value.toString()),
    )
}
