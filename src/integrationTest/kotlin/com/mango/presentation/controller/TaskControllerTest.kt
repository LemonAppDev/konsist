package com.mango.presentation.controller

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.business.model.request.task.UpdateTaskRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.util.ControllerEndpointCaller
import com.mango.util.Json
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.test.annotation.DirtiesContext
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TaskControllerTest {

    @Autowired
    private lateinit var taskEndpointHelper: TaskEndpointHelper

    @Autowired
    private lateinit var projectEndpointHelper: ProjectEndpointHelper

    @Autowired
    private lateinit var userEndpointHelper: UserEndpointHelper

    @Test
    fun `create endpoint creates task`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint(name = "parent task")
        val project = projectEndpointHelper.callCreateEndpoint()
        val assignee = userEndpointHelper.callCurrentEndpoint()

        // when
        val actual = taskEndpointHelper.callCreateEndpoint(
            name = "child task",
            parentTaskId = parentTask.id,
            projectId = project.id,
            assigneeId = assignee.id,
        )

        // then
        actual.parentTaskId shouldBeEqualTo parentTask.id
    }

    @Test
    fun `delete endpoint deletes task`() {
        // given
        val task1 = taskEndpointHelper.callCreateEndpoint()
        val task2 = taskEndpointHelper.callCreateEndpoint()
        taskEndpointHelper.callDeleteEndpoint(task1.id)

        // when
        val actual = taskEndpointHelper.callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(task2)
    }

    @Test
    fun `all endpoint returns empty list`() {
        // when
        val actual = taskEndpointHelper.callAllEndpoint()

        // then
        actual shouldBeEqualTo emptyList()
    }

    @Test
    fun `all endpoint returns 2 tasks`() {
        // given
        val task1 = taskEndpointHelper.callCreateEndpoint()
        val task2 = taskEndpointHelper.callCreateEndpoint()

        // when
        val actual = taskEndpointHelper.callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(task1, task2)
    }

    @Test
    fun `update endpoint updates task`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()
        val project = projectEndpointHelper.callCreateEndpoint()
        val assignee = userEndpointHelper.callCurrentEndpoint()
        val task = taskEndpointHelper.callCreateEndpoint()

        // when
        val actual = taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = task.id,
                name = "updated name",
                description = "updated description",
                dueDate = LocalDateTime.now().plusDays(10),
                targetDate = LocalDateTime.now().plusDays(20),
                priority = Priority.PRIORITY_5,
                projectId = project.id,
                parentTaskId = parentTask.id,
                assigneeId = assignee.id,
            ),
        )

        // then
        val expected = taskEndpointHelper.callGetEndpoint(task.id)
        actual shouldBeEqualTo expected
    }

    @Test
    fun `duplicate endpoint duplicates task`() {
        // given
        val task1 = taskEndpointHelper.callCreateEndpoint()
        val task2 = taskEndpointHelper.callDuplicateEndpoint(task1.id)

        // when
        val actual = taskEndpointHelper.callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(task1, task2)
    }
}

@Service
class TaskEndpointHelper(
    private var controllerEndpointCaller: ControllerEndpointCaller,
) {
    fun callCreateEndpoint(
        name: String? = "task",
        parentTaskId: TaskId? = null,
        projectId: ProjectId? = null,
        assigneeId: UserId? = null,
    ): Task {
        val requestModel = CreateTaskRequestModel(
            name = name ?: "name",
            description = "description",
            dueDate = LocalDateTime.now().plusDays(1),
            targetDate = LocalDateTime.now().plusDays(2),
            priority = 1,
            projectId = projectId,
            parentTaskId = parentTaskId,
            assigneeId = assigneeId,
        )

        val jsonBody = Json.encodeToString(requestModel)
        return controllerEndpointCaller.call(
            this,
            endpointName = "create",
            method = HttpMethod.POST,
            jsonBody = jsonBody,
        )
    }

    fun callGetEndpoint(taskId: TaskId) = controllerEndpointCaller.call<Task>(
        this,
        endpointName = "get",
        method = HttpMethod.GET,
        queryParams = mapOf("taskId" to taskId.id),
    )

    fun callDeleteEndpoint(taskId: TaskId) = controllerEndpointCaller.call<Any?>(
        this,
        endpointName = "delete",
        method = HttpMethod.DELETE,
        queryParams = mapOf("taskId" to taskId.id),
    )

    fun callAllEndpoint() = controllerEndpointCaller.call<List<Task>>(
        this,
        endpointName = "all",
        method = HttpMethod.GET,
    )

    fun callUpdateEndpoint(requestModel: UpdateTaskRequestModel) = controllerEndpointCaller.call<Task>(
        this,
        endpointName = "update",
        method = HttpMethod.POST,
        jsonBody = Json.encodeToString(requestModel),
    )

    fun callDuplicateEndpoint(taskId: TaskId) = controllerEndpointCaller.call<Task>(
        this,
        endpointName = "duplicate",
        method = HttpMethod.POST,
        null,
        queryParams = mapOf("taskId" to taskId.id),
    )
}
