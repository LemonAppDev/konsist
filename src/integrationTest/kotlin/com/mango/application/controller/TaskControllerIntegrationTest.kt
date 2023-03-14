package com.mango.application.controller

import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType.CREATE
import com.mango.domain.activity.model.TaskActivityType.UPDATE_ASSIGNEE
import com.mango.domain.activity.model.TaskActivityType.UPDATE_COMPLETE_DATE
import com.mango.domain.activity.model.TaskActivityType.UPDATE_DESCRIPTION
import com.mango.domain.activity.model.TaskActivityType.UPDATE_DUE_DATE
import com.mango.domain.activity.model.TaskActivityType.UPDATE_NAME
import com.mango.domain.activity.model.TaskActivityType.UPDATE_PARENT_TASK
import com.mango.domain.activity.model.TaskActivityType.UPDATE_PRIORITY
import com.mango.domain.activity.model.TaskActivityType.UPDATE_PROJECT
import com.mango.domain.activity.model.TaskActivityType.UPDATE_TARGET_DATE
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId
import com.mango.domain.comment.model.request.AddCommentRequestModel
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.task.model.request.UpdateTaskRequestModel
import com.mango.domain.user.model.UserId
import com.mango.util.ControllerEndpointCaller
import com.mango.util.Json.serialize
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
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
        val project = projectEndpointHelper.callCreateEndpoint()
        val parentTask = taskEndpointHelper.callCreateEndpoint(name = "parent task", projectId = project.id)
        val assignee = userEndpointHelper.callCreateEndpoint("assignee")

        // when
        val actual = taskEndpointHelper.callCreateEndpoint(
            name = "task",
            parentTaskId = parentTask.id,
            projectId = project.id,
            assigneeId = assignee.id,
        )

        // then
        val expected = taskEndpointHelper.callGetEndpoint(taskId = actual.id)
        actual shouldBeEqualTo expected
    }

    @Test
    fun `create endpoint return error when parent task and task is not in the same project`() {
        // given
        val project1 = projectEndpointHelper.callCreateEndpoint()
        val project2 = projectEndpointHelper.callCreateEndpoint()
        val parentTaskInProject1 = taskEndpointHelper.callCreateEndpoint(name = "parent task", projectId = project1.id)

        // when
        val actual = {
            taskEndpointHelper.callCreateEndpoint(
                name = "task",
                parentTaskId = parentTaskInProject1.id,
                projectId = project2.id,
            )
        }

        // then
        actual shouldThrow RuntimeException::class
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
        val project = projectEndpointHelper.callCreateEndpoint()
        val parentTask = taskEndpointHelper.callCreateEndpoint(projectId = project.id)
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
                isCompleted = true,
            ),
        )

        // then
        val expected = taskEndpointHelper.callGetEndpoint(task.id)
        actual shouldBeEqualTo expected
    }

    @Test
    fun `update endpoint return error when parent task and task is not in the same project`() {
        // given
        val project1 = projectEndpointHelper.callCreateEndpoint()
        val project2 = projectEndpointHelper.callCreateEndpoint()
        val parentTaskInProject1 = taskEndpointHelper.callCreateEndpoint(projectId = project1.id)
        val assignee = userEndpointHelper.callCurrentEndpoint()
        val task = taskEndpointHelper.callCreateEndpoint()

        // when
        val actual = {
            taskEndpointHelper.callUpdateEndpoint(
                UpdateTaskRequestModel(
                    taskId = task.id,
                    name = "updated name",
                    description = "updated description",
                    dueDate = LocalDateTime.now().plusDays(10),
                    targetDate = LocalDateTime.now().plusDays(20),
                    priority = Priority.PRIORITY_5,
                    projectId = project2.id,
                    parentTaskId = parentTaskInProject1.id,
                    assigneeId = assignee.id,
                    isCompleted = true,
                ),
            )
        }

        // then
        actual shouldThrow RuntimeException::class
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

    @Test
    fun `add task comment endpoint adds comment`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()

        // when
        val actual = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.id,
                text = "comment",
            ),
        )

        // then
        val expected = taskEndpointHelper.callGetCommentsEndpoint(task.id)
        listOf(actual) shouldBeEqualTo expected
    }

    @Test
    fun `delete comment endpoint deletes comment`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()
        val comment1 = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.id,
                text = "comment",
            ),
        )
        val comment2 = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.id,
                text = "comment",
            ),
        )

        // when
        taskEndpointHelper.callDeleteCommentEndpoint(comment1.id)

        // then
        val expected = taskEndpointHelper.callGetCommentsEndpoint(task.id)
        listOf(comment2) shouldBeEqualTo expected
    }

    @Test
    fun `comments all endpoint returns all comments`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()
        val comment1 = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.id,
                text = "comment",
            ),
        )
        val comment2 = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.id,
                text = "comment",
            ),
        )

        // when
        val actual = taskEndpointHelper.callGetCommentsEndpoint(task.id)

        // then
        actual shouldBeEqualTo listOf(comment1, comment2)
    }

    @Test
    fun `update comment endpoint updates comment`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()
        val comment = taskEndpointHelper.callAddCommentEndpoint(
            AddCommentRequestModel(
                taskId = task.id,
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
                taskId = childTask.id,
                name = childTask.name,
                description = childTask.description,
                dueDate = childTask.dueDate,
                targetDate = childTask.targetDate,
                priority = childTask.priority,
                projectId = childTask.projectId,
                parentTaskId = parentTask.id,
                assigneeId = childTask.assigneeId,
                isCompleted = true,
            ),
        )

        // when
        val actual = taskEndpointHelper.callChildTasksEndpoint(parentTask.id)

        // then
        actual shouldBeEqualTo listOf(childTask)
    }

    @Test
    fun `task activities endpoint return task activities after creating task`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()

        // when
        val actual = taskEndpointHelper.callGetTaskActivitiesEndPoint(task.id)
            .map { it.type }

        // then
        actual shouldBeEqualTo listOf(CREATE)
    }

    @Test
    fun `task activities endpoint return task activities after updating task`() {
        // given
        val task = taskEndpointHelper.callCreateEndpoint()
        val project = projectEndpointHelper.callCreateEndpoint()
        val parentTask = taskEndpointHelper.callCreateEndpoint(projectId = project.id)
        val assignee = userEndpointHelper.callCurrentEndpoint()
        taskEndpointHelper.callUpdateEndpoint(
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
                isCompleted = true,
            ),
        )

        // when
        val actual = taskEndpointHelper.callGetTaskActivitiesEndPoint(task.id)
            .map { it.type }

        // then
        actual shouldBeEqualTo listOf(
            CREATE,
            UPDATE_NAME,
            UPDATE_DESCRIPTION,
            UPDATE_DUE_DATE,
            UPDATE_TARGET_DATE,
            UPDATE_PRIORITY,
            UPDATE_PROJECT,
            UPDATE_PARENT_TASK,
            UPDATE_ASSIGNEE,
            UPDATE_COMPLETE_DATE,
        )
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

        val jsonBody = serialize(requestModel)
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
        body = serialize(requestModel),
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
        body = serialize(requestModel),
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
        body = serialize(requestModel),
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

    fun callGetTaskActivitiesEndPoint(taskId: TaskId) = controllerEndpointCaller.call<List<TaskActivity>>(
        this,
        endpointName = "activities",
        method = HttpMethod.GET,
        queryParams = mapOf("taskId" to taskId.value.toString()),
    )
}
