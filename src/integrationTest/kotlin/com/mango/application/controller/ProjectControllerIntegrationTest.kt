package com.mango.application.controller

import com.mango.application.model.project.CreateProjectRequestModel
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.ProjectActivityType.CREATE
import com.mango.domain.common.model.Color
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.ProjectId
import com.mango.util.ControllerEndpointCaller
import com.mango.util.Json.serialize
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

    @Autowired
    private lateinit var taskEndpointHelper: TaskEndpointHelper

    @Test
    fun `create endpoint project`() {
        // given
        val project = projectEndpointHelper.callCreateEndpoint()

        // when
        val actual = projectEndpointHelper.callGetEndpoint(project.id)

        // then
        actual shouldBeEqualTo project
    }

    @Test
    fun `delete endpoint deletes project`() {
        // given
        val project1 = projectEndpointHelper.callCreateEndpoint()
        val project2 = projectEndpointHelper.callCreateEndpoint()
        projectEndpointHelper.callDeleteEndpoint(project1.id)

        // when
        val actual = projectEndpointHelper.calAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(project2)
    }

    @Test
    fun `delete endpoint deletes all tasks from project`() {
        // given
        val project = projectEndpointHelper.callCreateEndpoint()
        taskEndpointHelper.callCreateEndpoint(projectId = project.id)
        val otherTask = taskEndpointHelper.callCreateEndpoint()
        projectEndpointHelper.callDeleteEndpoint(project.id)

        // when
        val actual = taskEndpointHelper.callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(otherTask)
    }

    @Test
    fun `all endpoint returns empty list`() {
        // when
        val actual = projectEndpointHelper.calAllEndpoint()

        // then
        actual shouldBeEqualTo emptyList()
    }

    @Test
    fun `all endpoint returns 2 projects`() {
        // given
        val project1 = projectEndpointHelper.callCreateEndpoint()
        val project2 = projectEndpointHelper.callCreateEndpoint()

        // when
        val actual = projectEndpointHelper.calAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(project1, project2)
    }

    @Test
    fun `project activities endpoint return project create activity after creating project`() {
        // given
        val project = projectEndpointHelper.callCreateEndpoint()

        // when
        val actual = projectEndpointHelper
            .callGetProjectActivitiesEndPoint(project.id)
            .map { it.type }

        // then
        actual shouldBeEqualTo listOf(CREATE)
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

        val jsonBody = serialize(requestModel)
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

    fun callDeleteEndpoint(projectId: ProjectId) = controllerEndpointCaller.call<Unit?>(
        this,
        endpointName = "delete",
        method = HttpMethod.DELETE,
        queryParams = mapOf("projectId" to projectId.value.toString()),
    )

    fun calAllEndpoint() = controllerEndpointCaller.call<List<Project>>(
        this,
        endpointName = "all",
        method = HttpMethod.GET,
    )

    fun callGetProjectActivitiesEndPoint(projectId: ProjectId) = controllerEndpointCaller.call<List<ProjectActivity>>(
        this,
        endpointName = "activities",
        method = HttpMethod.GET,
        queryParams = mapOf("projectId" to projectId.value.toString()),
    )
}
