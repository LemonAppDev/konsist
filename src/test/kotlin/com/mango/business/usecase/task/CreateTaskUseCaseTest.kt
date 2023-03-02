package com.mango.business.usecase.task

import com.mango.business.factory.TaskFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivity
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.request.CreateTaskRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.ActivityRepository
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
    private val userRepository: UserRepository = mockk()
    private val taskRepository: TaskRepository = mockk()
    private val createTaskActivityFactory: CreateTaskActivityFactory = spyk()
    private val activityRepository: ActivityRepository = mockk()
    private val taskFactory: TaskFactory = mockk()

    private val sut = CreateTaskUseCase(
        userRepository,
        taskRepository,
        createTaskActivityFactory,
        activityRepository,
        taskFactory,
    )

    @Test
    fun `throws exception when parentTaskId is not null and parent doesn't exist`() {
        // given
        val parentId = TaskId("parentId")
        val createTaskRequestModel: CreateTaskRequestModel = mockk()
        every { createTaskRequestModel.parentTaskId } returns parentId
        every { taskRepository.containsTask(parentId) } returns false

        // when
        val actual = { sut(createTaskRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Parent task with id: $parentId doesn't exist"
    }

    @Test
    fun `creates and adds new task to tasks list`() {
        // given
        val taskId = TaskId("id")
        val userId = UserId("id")
        val creationDate: LocalDateTime = mockk()
        val dueDate: LocalDateTime = mockk()
        val targetDate: LocalDateTime = mockk()
        val createTaskRequestModel = CreateTaskRequestModel(
            "name",
            "description",
            dueDate,
            targetDate,
            1,
            ProjectId("projectId"),
            null,
            UserId("assigneeId"),
        )
        every { userRepository.getCurrentUser().id } returns userId

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, userId) } returns task

        justRun { taskRepository.addTask(task) }
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        every { createTaskActivityFactory(taskId, creationDate) } returns mockk()
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(createTaskRequestModel)

        // then
        verify { taskRepository.addTask(task) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val creationDate: LocalDateTime = mockk()
        val dueDate: LocalDateTime = mockk()
        val targetDate: LocalDateTime = mockk()
        val createTaskRequestModel = CreateTaskRequestModel(
            "name",
            "description",
            dueDate,
            targetDate,
            1,
            ProjectId("projectId"),
            null,
            UserId("assigneeId"),
        )
        every { userRepository.getCurrentUser().id } returns UserId("ownerId")

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, UserId("ownerId")) } returns task
        justRun { taskRepository.addTask(task) }
        every { task.id } returns TaskId("id")
        every { task.creationDate } returns creationDate
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(TaskId("id"), creationDate) } returns activity
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(createTaskRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `calls all instructions when parentTaskId is not null and this parent exist`() {
        // given
        val creationDate: LocalDateTime = mockk()
        val dueDate: LocalDateTime = mockk()
        val targetDate: LocalDateTime = mockk()
        val createTaskRequestModel = CreateTaskRequestModel(
            "name",
            "description",
            dueDate,
            targetDate,
            1,
            ProjectId("projectId"),
            TaskId("parentId"),
            UserId("assigneeId"),
        )
        every { userRepository.getCurrentUser().id } returns UserId("ownerId")
        every { taskRepository.containsTask(TaskId("parentId")) } returns true

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, UserId("ownerId")) } returns task

        justRun { taskRepository.addTask(task) }
        every { task.id } returns TaskId("id")
        every { task.creationDate } returns creationDate
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(TaskId("id"), creationDate) } returns activity
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(createTaskRequestModel)

        // then
        verify {
            taskRepository.addTask(task)
            activityRepository.addActivity(activity)
        }
    }
}
