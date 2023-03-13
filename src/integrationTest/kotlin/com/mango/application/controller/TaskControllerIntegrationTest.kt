package com.mango.application.controller

import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId
import com.mango.domain.comment.model.request.AddCommentRequestModel
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.task.model.request.UpdateTaskRequestModel
import com.mango.domain.user.model.UserId
import com.mango.util.ControllerEndpointCaller
import com.mango.util.Json.encodeToString
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
        val assignee = userEndpointHelper.callCreateEndpoint("assignee")

        // when
        val actual = taskEndpointHelper.callCreateEndpoint(
            name = "task",
            parentTaskId = parentTask.value,
            projectId = project.id,
            assigneeId = assignee.id,
        )

        // then
        val expected = taskEndpointHelper.callGetEndpoint(taskId = actual.value)
        actual shouldBeEqualTo expected
    }

    @Test
    fun `delete endpoint deletes task`() {
        // given
        val task1 = taskEndpointHelper.callCreateEndpoint()
        val task2 = taskEndpointHelper.callCreateEndpoint()
        taskEndpointHelper.callDeleteEndpoint(task1.value)

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
                taskId = task.value,
                name = "updated name",
                description = "updated description",
                dueDate = LocalDateTime.now().plusDays(10),
                targetDate = LocalDateTime.now().plusDays(20),
                priority = com.mango.domain.task.model.Priority.PRIORITY_5,
                projectId = project.id,
                parentTaskId = parentTask.value,
                assigneeId = assignee.id,
                isCompleted = true,
            ),
        )

        // then
        val expected = taskEndpointHelper.callGetEndpoint(task.value)
        actual shouldBeEqualTo expected
    }

    @Test
    fun `duplicate endpoint duplicates task`() {
        // given
        val task1 = taskEndpointHelper.callCreateEndpoint()
        val task2 = taskEndpointHelper.callDuplicateEndpoint(task1.value)

        // when
        val actual = taskEndpointHelper.callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(task1, task2)
    }

    @Test
    fun `add task comment endpoint adds comment`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()

        // when
        val actual = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.value,
                text = "comment",
            ),
        )

        // then
        val expected = taskEndpointHelper.callGetCommentsEndpoint(task.value)
        listOf(actual) shouldBeEqualTo expected
    }

    @Test
    fun `delete comment endpoint deletes comment`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()
        val comment1 = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.value,
                text = "comment",
            ),
        )
        val comment2 = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.value,
                text = "comment",
            ),
        )

        // when
        taskEndpointHelper.callDeleteCommentEndpoint(comment1.id)

        // then
        val expected = taskEndpointHelper.callGetCommentsEndpoint(task.value)
        listOf(comment2) shouldBeEqualTo expected
    }

    @Test
    fun `comments all endpoint returns all comments`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()
        val comment1 = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.value,
                text = "comment",
            ),
        )
        val comment2 = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.value,
                text = "comment",
            ),
        )

        // when
        val actual = taskEndpointHelper.callGetCommentsEndpoint(task.value)

        // then
        actual shouldBeEqualTo listOf(comment1, comment2)
    }

    @Test
    fun `update comment endpoint updates comment`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()
        val comment = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.value,
                text = "comment",
            ),
        )

        // when
        val actual = taskEndpointHelper.callUpdateCommentEndpoint(
            UpdateCommentRequestModel(
                commentId = comment.id,
                text = "updated comment",
            ),
        )

        // then
        val expected = taskEndpointHelper.callGetCommentEndpoint(comment.id)
        actual shouldBeEqualTo expected
    }

    @Test
    fun `child tasks endpoint return child tasks`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()

        var childTask = taskEndpointHelper.callCreateEndpoint()
        childTask = taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = childTask.value,
                name = childTask.name,
                description = childTask.description,
                dueDate = childTask.dueDate,
                targetDate = childTask.targetDate,
                priority = childTask.priority,
                projectId = childTask.projectId,
                parentTaskId = parentTask.value,
                assigneeId = childTask.assigneeId,
                isCompleted = true,
            ),
        )

        // when
        val actual = taskEndpointHelper.callChildTasksEndpoint(parentTask.value)

        // then
        actual shouldBeEqualTo listOf(childTask)
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

        val jsonBody = encodeToString(requestModel)
        return controllerEndpointCaller.call(
            this,
            endpointName = "create",
            method = HttpMethod.POST,
            body = jsonBody,
        )
    }

    fun callGetEndpoint(taskId: TaskId) = controllerEndpointCaller.call<Task>(
        this,
        endpointName = "get",
        method = HttpMethod.GET,
        queryParams = mapOf("taskId" to taskId.value.toString()),
    )

    fun callDeleteEndpoint(taskId: TaskId) = controllerEndpointCaller.call<Any?>(
        this,
        endpointName = "delete",
        method = HttpMethod.DELETE,
        queryParams = mapOf("taskId" to taskId.value.toString()),
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
        body = encodeToString(requestModel),
    )

    fun callDuplicateEndpoint(taskId: TaskId) = controllerEndpointCaller.call<Task>(
        this,
        endpointName = "duplicate",
        method = HttpMethod.POST,
        queryParams = mapOf("taskId" to taskId.value.toString()),
    )

    fun callAddCommentEndpoint(requestModel: AddCommentRequestModel) = controllerEndpointCaller.call<Comment>(
        this,
        endpointName = "add-comment",
        method = HttpMethod.POST,
        body = encodeToString(requestModel),
    )

    fun callDeleteCommentEndpoint(commentId: CommentId) = controllerEndpointCaller.call<Any?>(
        this,
        endpointName = "delete-comment",
        method = HttpMethod.DELETE,
        queryParams = mapOf("commentId" to commentId.value.toString()),
    )

    fun callUpdateCommentEndpoint(requestModel: UpdateCommentRequestModel) = controllerEndpointCaller.call<Comment>(
        this,
        endpointName = "update-comment",
        method = HttpMethod.POST,
        body = encodeToString(requestModel),
    )

    fun callGetCommentEndpoint(commentId: CommentId) = controllerEndpointCaller.call<Comment>(
        this,
        endpointName = "comment",
        method = HttpMethod.GET,
        queryParams = mapOf("commentId" to commentId.value.toString()),
    )

    fun callGetCommentsEndpoint(taskId: TaskId) = controllerEndpointCaller.call<List<Comment>>(
        this,
        endpointName = "comments-all",
        method = HttpMethod.GET,
        queryParams = mapOf("taskId" to taskId.value.toString()),
    )

    fun callChildTasksEndpoint(taskId: TaskId) = controllerEndpointCaller.call<List<Task>>(
        this,
        endpointName = "child-tasks",
        method = HttpMethod.GET,
        queryParams = mapOf("taskId" to taskId.value.toString()),
    )
}
