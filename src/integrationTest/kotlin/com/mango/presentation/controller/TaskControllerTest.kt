package com.mango.presentation.controller

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.business.model.request.task.UpdateTaskRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.util.Json
import com.mango.util.ext.exchange
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.test.annotation.DirtiesContext
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TaskControllerTest {

    @Value(value = "\${local.server.port}")
    private val port = 0
    private val controllerUrl get() = "http://localhost:$port/v1/task/"

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `create endpoint creates task`() {
        // given
        val task = callCreateEndpoint()

        // when
        val actual = callGetEndpoint(task.id)

        // then
        actual shouldBeEqualTo task
    }

    @Test
    fun `create endpoint creates task with parent task`() {
        // given
        val parentTask = callCreateEndpoint(name = "parent task")

        // when
        val actual = callCreateEndpoint(name = "child task", parentTaskId = parentTask.id)

        // then
        actual.parentTaskId shouldBeEqualTo parentTask.id
    }

    @Test
    fun `delete endpoint deletes task`() {
        // given
        val task1 = callCreateEndpoint()
        val task2 = callCreateEndpoint()
        callDeleteEndpoint(task1.id)

        // when
        val actual = callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(task2)
    }

    @Test
    fun `all endpoint returns empty list`() {
        // when
        val actual = callAllEndpoint()

        // then
        actual shouldBeEqualTo emptyList()
    }

    @Test
    fun `all endpoint returns 2 tasks`() {
        // given
        val task1 = callCreateEndpoint()
        val task2 = callCreateEndpoint()

        // when
        val actual = callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(task1, task2)
    }

    @Test
    fun `update endpoint updates task`() {
        // given
        val parentTask = callCreateEndpoint()
        val task = callCreateEndpoint()
        val updatedTask = callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = task.id,
                name = "updated name",
                description = "updated description",
                dueDate = LocalDateTime.now().plusDays(10),
                targetDate = LocalDateTime.now().plusDays(20),
                priority = Priority.PRIORITY_5,
                projectId = ProjectId("updatedProjectId"),
                parentTaskId = parentTask.id,
                assigneeId = UserId("updatedAssigneeId"),
            ),
        )

        // when
        val actual = callGetEndpoint(task.id)

        // then
        actual shouldBeEqualTo updatedTask
    }

    @Test
    fun `duplicate endpoint duplicates task`() {
        // given
        val task1 = callCreateEndpoint()
        val task2 = callDuplicateEndpoint(task1.id)

        // when
        val actual = callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(task1, task2)
    }

    private fun callCreateEndpoint(name: String? = "task", parentTaskId: TaskId? = null): Task {
        val requestModel = CreateTaskRequestModel(
            name = name ?: "name",
            description = "description",
            dueDate = LocalDateTime.now().plusDays(1),
            targetDate = LocalDateTime.now().plusDays(2),
            priority = 1,
            projectId = ProjectId("projectId"),
            parentTaskId = parentTaskId,
            assigneeId = UserId("assigneeId"),
        )

        val jsonBody = Json.encodeToString(requestModel)
        return callTaskController(
            endpointName = "create",
            method = HttpMethod.POST,
            jsonBody = jsonBody,
        )
    }

    private fun callGetEndpoint(taskId: TaskId) = callTaskController<Task>(
        endpointName = "get",
        method = HttpMethod.GET,
        queryParams = mapOf("taskId" to taskId.id),
    )

    private fun callDeleteEndpoint(taskId: TaskId) = callTaskController<Any?>(
        endpointName = "delete",
        method = HttpMethod.DELETE,
        queryParams = mapOf("taskId" to taskId.id),
    )

    private fun callAllEndpoint() = callTaskController<List<Task>>(
        endpointName = "all",
        method = HttpMethod.GET,
    )

    private fun callUpdateEndpoint(requestModel: UpdateTaskRequestModel) = callTaskController<Task>(
        endpointName = "update",
        method = HttpMethod.POST,
        jsonBody = Json.encodeToString(requestModel),
    )

    private fun callDuplicateEndpoint(taskId: TaskId) = callTaskController<Task>(
        endpointName = "duplicate",
        method = HttpMethod.POST,
        null,
        queryParams = mapOf("taskId" to taskId.id),
    )

    private inline fun <reified T : Any?> callTaskController(
        endpointName: String,
        method: HttpMethod,
        jsonBody: String? = null,
        queryParams: Map<String, String>? = null,
    ): T = restTemplate.exchange(
        url = "$controllerUrl/$endpointName",
        method = method,
        jsonBody = jsonBody,
        queryParams = queryParams,
    )
}
