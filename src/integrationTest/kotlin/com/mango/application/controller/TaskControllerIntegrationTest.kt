package com.mango.application.controller

import com.mango.application.model.comment.AddCommentRequestModel
import com.mango.application.model.comment.UpdateCommentRequestModel
import com.mango.application.model.task.CreateTaskRequestModel
import com.mango.application.model.task.UpdateTaskRequestModel
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.ProjectActivityType.TASK_ADDED
import com.mango.domain.activity.model.ProjectActivityType.TASK_REMOVED
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
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.Priority.PRIORITY_5
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import com.mango.util.ControllerEndpointCaller
import com.mango.util.Json.serialize
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
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
    fun `create endpoint returns error when parent task and task is not in the same project`() {
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
    fun `create endpoint adds task_added project activity when project is provided`() {
        // given
        val project = projectEndpointHelper.callCreateEndpoint()

        // when
        taskEndpointHelper.callCreateEndpoint(name = "task", projectId = project.id)

        // then
        val actual = projectEndpointHelper
            .callGetProjectActivitiesEndPoint(project.id)
            .map { it.type }

        actual shouldBeEqualTo listOf(ProjectActivityType.CREATE, TASK_ADDED)
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
    fun `delete endpoint deletes task with its child tasks`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()
        taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)
        taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)

        val otherTask = taskEndpointHelper.callCreateEndpoint()
        taskEndpointHelper.callDeleteEndpoint(parentTask.id)

        // when
        val actual = taskEndpointHelper.callAllEndpoint()

        // then
        actual shouldBeEqualTo listOf(otherTask)
    }

    @Test
    fun `delete endpoint adds task_removed project activity when task has a project`() {
        // given
        val project = projectEndpointHelper.callCreateEndpoint()
        val task = taskEndpointHelper.callCreateEndpoint(name = "task", projectId = project.id)

        // when
        taskEndpointHelper.callDeleteEndpoint(task.id)

        // then
        val actual = projectEndpointHelper
            .callGetProjectActivitiesEndPoint(project.id)
            .map { it.type }

        actual shouldBeEqualTo listOf(ProjectActivityType.CREATE, TASK_ADDED, TASK_REMOVED)
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
                priority = PRIORITY_5,
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
    fun `update endpoint updates project in all subtasks`() {
        // given
        val oldProject = projectEndpointHelper.callCreateEndpoint()
        val newProject = projectEndpointHelper.callCreateEndpoint()
        val parentTask = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id)
        val childTask1 = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id, parentTaskId = parentTask.id)
        val childTask2 = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id, parentTaskId = parentTask.id)

        // when
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = parentTask.id,
                name = parentTask.name,
                description = parentTask.description,
                dueDate = parentTask.dueDate,
                targetDate = parentTask.targetDate,
                priority = parentTask.priority,
                projectId = newProject.id,
                parentTaskId = parentTask.parentTaskId,
                assigneeId = parentTask.assigneeId,
                isCompleted = true,
            ),
        )

        // then
        val updatedParentTask = taskEndpointHelper.callGetEndpoint(parentTask.id)
        val updatedChildTask1 = taskEndpointHelper.callGetEndpoint(childTask1.id)
        val updatedChildTask2 = taskEndpointHelper.callGetEndpoint(childTask2.id)
        val actual = taskEndpointHelper.callAllEndpoint()
            .filter { it.projectId == newProject.id }
        actual shouldBeEqualTo listOf(updatedParentTask, updatedChildTask1, updatedChildTask2)
    }

    @Test
    fun `update endpoint updates project in all subtasks recursively`() {
        // given
        val oldProject = projectEndpointHelper.callCreateEndpoint()
        val newProject = projectEndpointHelper.callCreateEndpoint()
        val parentTask = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id)
        val childTask = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id, parentTaskId = parentTask.id)
        val childChildTask = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id, parentTaskId = childTask.id)

        // when
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = parentTask.id,
                name = parentTask.name,
                description = parentTask.description,
                dueDate = parentTask.dueDate,
                targetDate = parentTask.targetDate,
                priority = parentTask.priority,
                projectId = newProject.id,
                parentTaskId = parentTask.parentTaskId,
                assigneeId = parentTask.assigneeId,
                isCompleted = true,
            ),
        )

        // then
        val updatedParentTask = taskEndpointHelper.callGetEndpoint(parentTask.id)
        val updatedChildTask1 = taskEndpointHelper.callGetEndpoint(childTask.id)
        val updatedChildTask2 = taskEndpointHelper.callGetEndpoint(childChildTask.id)
        val actual = taskEndpointHelper.callAllEndpoint()
            .filter { it.projectId == newProject.id }
        actual shouldBeEqualTo listOf(updatedParentTask, updatedChildTask1, updatedChildTask2)
    }

    @Test
    fun `update endpoint updates complete date in all subtasks`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()
        val childTask1 = taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)
        val childTask2 = taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)

        // when
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = parentTask.id,
                name = parentTask.name,
                description = parentTask.description,
                dueDate = parentTask.dueDate,
                targetDate = parentTask.targetDate,
                priority = parentTask.priority,
                projectId = parentTask.projectId,
                parentTaskId = parentTask.parentTaskId,
                assigneeId = parentTask.assigneeId,
                isCompleted = true,
            ),
        )

        // then
        val updatedParentTask = taskEndpointHelper.callGetEndpoint(parentTask.id)
        val updatedChildTask1 = taskEndpointHelper.callGetEndpoint(childTask1.id)
        val updatedChildTask2 = taskEndpointHelper.callGetEndpoint(childTask2.id)
        val completeDate = updatedParentTask.completeDate

        val actual = taskEndpointHelper.callAllEndpoint()
            .filter { it.completeDate == completeDate }
        actual shouldBeEqualTo listOf(updatedParentTask, updatedChildTask1, updatedChildTask2)
    }

    @Test
    fun `update endpoint updates complete date in subtasks recursively if they haven't been completed earlier`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()
        val childTask = taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)
        val childChildTask = taskEndpointHelper.callCreateEndpoint(parentTaskId = childTask.id)

        // when
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = parentTask.id,
                name = parentTask.name,
                description = parentTask.description,
                dueDate = parentTask.dueDate,
                targetDate = parentTask.targetDate,
                priority = parentTask.priority,
                projectId = parentTask.projectId,
                parentTaskId = parentTask.parentTaskId,
                assigneeId = parentTask.assigneeId,
                isCompleted = true,
            ),
        )

        // then
        val updatedParentTask = taskEndpointHelper.callGetEndpoint(parentTask.id)
        val updatedChildTask1 = taskEndpointHelper.callGetEndpoint(childTask.id)
        val updatedChildTask2 = taskEndpointHelper.callGetEndpoint(childChildTask.id)
        val completeDate = updatedParentTask.completeDate

        val actual = taskEndpointHelper.callAllEndpoint()
            .filter { it.completeDate == completeDate }
        actual shouldBeEqualTo listOf(updatedParentTask, updatedChildTask1, updatedChildTask2)
    }

    @Test
    fun `update endpoint not updates complete date in subtasks if they have been completed earlier`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()
        val childTask = taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)
        val childChildTask = taskEndpointHelper.callCreateEndpoint(parentTaskId = childTask.id)
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = childChildTask.id,
                name = childChildTask.name,
                description = childChildTask.description,
                dueDate = childChildTask.dueDate,
                targetDate = childChildTask.targetDate,
                priority = childChildTask.priority,
                projectId = childChildTask.projectId,
                parentTaskId = childChildTask.parentTaskId,
                assigneeId = childChildTask.assigneeId,
                isCompleted = true,
            ),
        )

        // when
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = parentTask.id,
                name = parentTask.name,
                description = parentTask.description,
                dueDate = parentTask.dueDate,
                targetDate = parentTask.targetDate,
                priority = parentTask.priority,
                projectId = parentTask.projectId,
                parentTaskId = parentTask.parentTaskId,
                assigneeId = parentTask.assigneeId,
                isCompleted = true,
            ),
        )

        // then
        val updatedParentTask = taskEndpointHelper.callGetEndpoint(parentTask.id)
        val updatedChildTask1 = taskEndpointHelper.callGetEndpoint(childTask.id)
        val completeDate = updatedParentTask.completeDate

        val actual = taskEndpointHelper.callAllEndpoint()
            .filter { it.completeDate == completeDate }
        actual shouldBeEqualTo listOf(updatedParentTask, updatedChildTask1)
    }

    @Test
    fun `update endpoint adds task_added project activity when project is changed`() {
        // given
        val oldProject = projectEndpointHelper.callCreateEndpoint()
        val newProject = projectEndpointHelper.callCreateEndpoint()
        val task = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id)

        // when
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = task.id,
                name = task.name,
                description = task.description,
                dueDate = task.dueDate,
                targetDate = task.targetDate,
                priority = task.priority,
                projectId = newProject.id,
                parentTaskId = task.parentTaskId,
                assigneeId = task.assigneeId,
                isCompleted = false,
            ),
        )

        // then
        val actual = projectEndpointHelper.callGetProjectActivitiesEndPoint(newProject.id).map { it.type }
        actual shouldBeEqualTo listOf(ProjectActivityType.CREATE, TASK_ADDED)
    }

    @Test
    fun `update endpoint adds task_removed project activity to old project after changing task project`() {
        // given
        val oldProject = projectEndpointHelper.callCreateEndpoint()
        val newProject = projectEndpointHelper.callCreateEndpoint()
        val task = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id)

        // when
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = task.id,
                name = task.name,
                description = task.description,
                dueDate = task.dueDate,
                targetDate = task.targetDate,
                priority = task.priority,
                projectId = newProject.id,
                parentTaskId = task.parentTaskId,
                assigneeId = task.assigneeId,
                isCompleted = false,
            ),
        )

        // then
        val actual = projectEndpointHelper.callGetProjectActivitiesEndPoint(oldProject.id).map { it.type }
        actual shouldBeEqualTo listOf(ProjectActivityType.CREATE, TASK_ADDED, TASK_REMOVED)
    }

    @Test
    fun `update endpoint returns error when parent task and task is not in the same project`() {
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
                    priority = PRIORITY_5,
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
    fun `duplicate endpoint adds create task activity`() {
        // given
        val task1 = taskEndpointHelper.callCreateEndpoint()

        // when
        val task2 = taskEndpointHelper.callDuplicateEndpoint(task1.id)

        // then
        val actual = taskEndpointHelper
            .callGetTaskActivitiesEndPoint(task2.id)
            .map { it.type }
        actual shouldBeEqualTo listOf(CREATE)
    }

    @Test
    fun `duplicate endpoint duplicates all subtasks`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()
        taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)
        taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)

        // when
        taskEndpointHelper.callDuplicateEndpoint(
            taskId = parentTask.id,
        )

        // then
        val actual = taskEndpointHelper.callAllEndpoint()
        actual.size shouldBeEqualTo 6
    }

    @Test
    fun `duplicate endpoint duplicates subtasks recursively`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()
        val childTask1 = taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)
        taskEndpointHelper.callCreateEndpoint(parentTaskId = childTask1.id)
        val childTask2 = taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)
        taskEndpointHelper.callCreateEndpoint(parentTaskId = childTask2.id)

        // when
        taskEndpointHelper.callDuplicateEndpoint(
            taskId = parentTask.id,
        )

        // then
        val actual = taskEndpointHelper.callAllEndpoint()
        actual.size shouldBeEqualTo 10
    }

    @Test
    fun `duplicate endpoint adds task_added project activity when project is provided`() {
        // given
        val project = projectEndpointHelper.callCreateEndpoint()
        val task = taskEndpointHelper.callCreateEndpoint(projectId = project.id)

        // when
        taskEndpointHelper.callDuplicateEndpoint(task.id)

        // then
        val actual = projectEndpointHelper
            .callGetProjectActivitiesEndPoint(project.id)
            .map { it.type }
        actual shouldBeEqualTo listOf(ProjectActivityType.CREATE, TASK_ADDED, TASK_ADDED)
    }

    @Test
    fun `duplicate endpoint sets new parent task to duplicated child tasks`() {
        // given
        val parentTask = taskEndpointHelper.callCreateEndpoint()
        val childTask = taskEndpointHelper.callCreateEndpoint(parentTaskId = parentTask.id)
        taskEndpointHelper.callCreateEndpoint(parentTaskId = childTask.id)

        // when
        val newParentTask = taskEndpointHelper.callDuplicateEndpoint(
            taskId = parentTask.id,
        )

        // then
        val actual = taskEndpointHelper
            .callAllEndpoint()
            .filter { it.parentTaskId == newParentTask.id }
        actual.size shouldBeEqualTo 1
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
        val actual = taskEndpointHelper
            .callGetTaskActivitiesEndPoint(task.id)
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
                priority = PRIORITY_5,
                projectId = project.id,
                parentTaskId = parentTask.id,
                assigneeId = assignee.id,
                isCompleted = true,
            ),
        )

        // when
        val actual = taskEndpointHelper
            .callGetTaskActivitiesEndPoint(task.id)
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

    @Test
    fun `task activities endpoint contains update project activity after project updating by its parent task`() {
        // given
        val oldProject = projectEndpointHelper.callCreateEndpoint()
        val newProject = projectEndpointHelper.callCreateEndpoint()
        val parentTask = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id)
        val task = taskEndpointHelper.callCreateEndpoint(projectId = oldProject.id, parentTaskId = parentTask.id)
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = parentTask.id,
                name = parentTask.name,
                description = parentTask.description,
                dueDate = parentTask.dueDate,
                targetDate = parentTask.targetDate,
                priority = parentTask.priority,
                projectId = newProject.id,
                parentTaskId = parentTask.parentTaskId,
                assigneeId = parentTask.assigneeId,
                isCompleted = true,
            ),
        )

        // when
        taskEndpointHelper.callUpdateEndpoint(
            UpdateTaskRequestModel(
                taskId = parentTask.id,
                name = parentTask.name,
                description = parentTask.description,
                dueDate = parentTask.dueDate,
                targetDate = parentTask.targetDate,
                priority = parentTask.priority,
                projectId = newProject.id,
                parentTaskId = parentTask.parentTaskId,
                assigneeId = parentTask.assigneeId,
                isCompleted = true,
            ),
        )

        // then
        val actual = taskEndpointHelper
            .callGetTaskActivitiesEndPoint(task.id)
            .map { it.type }

        actual shouldContain UPDATE_PROJECT
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
