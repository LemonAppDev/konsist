package com.mango.presentation.controller

import com.mango.business.model.Project
import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.business.model.value.Color
import com.mango.business.model.value.ProjectId
import com.mango.util.ControllerEndpointCaller
import com.mango.util.Json
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProjectControllerTest {

    @Autowired
    private lateinit var projectEndpointHelper: ProjectEndpointHelper

    @Test
    fun `create endpoint creates project`() {
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
            collaborators = null,
            isFavourite = true,
            color = Color("0xFF0000"),
        )

        val jsonBody = Json.encodeToString(requestModel)
        return controllerEndpointCaller.call(
            this,
            endpointName = "create",
            method = HttpMethod.POST,
            jsonBody = jsonBody,
        )
    }

    fun callGetEndpoint(projectId: ProjectId) = controllerEndpointCaller.call<Project>(
        this,
        endpointName = "get",
        method = HttpMethod.GET,
        queryParams = mapOf("projectId" to projectId.id),
    )
}
