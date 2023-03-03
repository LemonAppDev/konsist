package com.mango.business.usecase.task

import com.mango.business.factory.TaskFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivity
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.activity.task.TaskActivity
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
import com.mango.persistence.repository.TaskRepository
import com.mango.persistence.repository.UserRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CreateTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val createTaskActivityFactory: CreateTaskActivityFactory = spyk()
    private val activityRepository: ActivityRepository = mockk()
    private val taskFactory: TaskFactory = mockk()
    private val projectRepository: ProjectRepository = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = CreateTaskUseCase(
        taskRepository,
        createTaskActivityFactory,
        activityRepository,
        taskFactory,
        projectRepository,
        userRepository,
    )

    @Test
    fun `throws exception when projectId is not null and this project doesn't exist`() {
        // given
        val projectId = ProjectId("projectId")
        val createTaskRequestModel: CreateTaskRequestModel = mockk()
        every { createTaskRequestModel.projectId } returns projectId
        every { projectRepository.containsProject(projectId) } returns false

        // when
        val actual = { sut(createTaskRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Project with id: $projectId doesn't exist"
    }

    @Test
    fun `throws exception when parentTaskId is not null and parent doesn't exist`() {
        // given
        val parentId = TaskId("parentId")
        val createTaskRequestModel: CreateTaskRequestModel = mockk()
        every { createTaskRequestModel.parentTaskId } returns parentId
        every { createTaskRequestModel.projectId } returns null
        every { taskRepository.containsTask(parentId) } returns false

        // when
        val actual = { sut(createTaskRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Parent task with id: $parentId doesn't exist"
    }

    @Test
    fun `throws exception when assigneeId is not null and this user doesn't exist`() {
        // given
        val assigneeId = UserId("userId")
        val createTaskRequestModel: CreateTaskRequestModel = mockk()
        every { createTaskRequestModel.assigneeId } returns assigneeId
        every { createTaskRequestModel.projectId } returns null
        every { createTaskRequestModel.parentTaskId } returns null
        every { userRepository.containsUser(assigneeId) } returns false

        // when
        val actual = { sut(createTaskRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Assignee with id: $assigneeId doesn't exist"
    }

    @Test
    fun `creates and adds new task to tasks list`() {
        // given
        val taskId = TaskId("id")
        val projectId = ProjectId("projectId")
        val parentTaskId = TaskId("parentTaskId")
        val assigneeId = UserId("assigneeId")
        val creationDate: LocalDateTime = mockk()
        val dueDate: LocalDateTime = mockk()
        val targetDate: LocalDateTime = mockk()
        val createTaskRequestModel = CreateTaskRequestModel(
            "name",
            "description",
            dueDate,
            targetDate,
            1,
            projectId,
            parentTaskId,
            assigneeId,
        )
        every { projectRepository.containsProject(projectId) } returns true
        every { taskRepository.containsTask(parentTaskId) } returns true
        every { userRepository.containsUser(assigneeId) } returns true

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel) } returns task

        justRun { taskRepository.addTask(task) }
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        every { createTaskActivityFactory(taskId, creationDate) } returns mockk()
        justRun { activityRepository.addActivity(any<TaskActivity>()) }

        // when
        sut(createTaskRequestModel)

        // then
        verify { taskRepository.addTask(task) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val projectId = ProjectId("projectId")
        val parentTaskId = TaskId("parentTaskId")
        val assigneeId = UserId("assigneeId")
        val creationDate: LocalDateTime = mockk()
        val dueDate: LocalDateTime = mockk()
        val targetDate: LocalDateTime = mockk()
        val createTaskRequestModel = CreateTaskRequestModel(
            "name",
            "description",
            dueDate,
            targetDate,
            1,
            projectId,
            parentTaskId,
            assigneeId,
        )
        every { projectRepository.containsProject(projectId) } returns true
        every { taskRepository.containsTask(parentTaskId) } returns true
        every { userRepository.containsUser(assigneeId) } returns true

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel) } returns task
        justRun { taskRepository.addTask(task) }
        every { task.id } returns TaskId("id")
        every { task.creationDate } returns creationDate
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(TaskId("id"), creationDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(createTaskRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
