package com.mango.business.usecase.task

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.factory.TaskFactory
import com.mango.business.factory.UUIDFactory
import com.mango.business.model.Priority
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
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CreateTaskUseCaseTest {
    private val uuidFactory: UUIDFactory = mockk()
    private val userRepository: UserRepository = mockk()
    private val taskRepository: TaskRepository = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val createTaskActivityFactory: CreateTaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val taskFactory: TaskFactory = mockk()

    private val sut = CreateTaskUseCase(
        uuidFactory,
        userRepository,
        taskRepository,
        localDateTimeFactory,
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
        every { taskRepository.containsTask(TaskId("parentId")) } returns false

        // when
        val actual = { sut(createTaskRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Parent task with id: $parentId doesn't exist"
    }

    @Test
    fun `factory creates create new task`() {
        // given
        val taskId = TaskId("taskId")
        val userId = UserId("ownerId")
        val creationDate: LocalDateTime = mockk()

        val requestModel = CreateTaskRequestModel(
            "name",
            "description",
            mockk(),
            mockk(),
            1,
            ProjectId("projectId"),
            TaskId("parentTaskId"),
            UserId("assigneeId"),
        )

        every { taskRepository.containsTask(TaskId("parentTaskId")) } returns true
        every { uuidFactory.createTaskId() } returns taskId
        every { userRepository.getCurrentUser().id } returns userId
        every { localDateTimeFactory() } returns creationDate

        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate

        every { taskFactory(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns task
        every { createTaskActivityFactory(taskId, creationDate) } returns mockk()
        justRun { taskRepository.addTask(any()) }
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(requestModel)

        // then
        verify {
            taskFactory(
                taskId,
                requestModel.name,
                userId,
                creationDate,
                requestModel.projectId,
                requestModel.description,
                requestModel.dueDate,
                requestModel.targetDate,
                Priority.getByValue(1),
                requestModel.parentTaskId,
                requestModel.assigneeId,
            )
        }
    }

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("taskId")
        val userId = UserId("ownerId")
        val creationDate: LocalDateTime = mockk()

        val requestModel = CreateTaskRequestModel(
            "name",
            "description",
            mockk(),
            mockk(),
            1,
            ProjectId("projectId"),
            TaskId("parentTaskId"),
            UserId("assigneeId"),
        )

        every { taskRepository.containsTask(TaskId("parentTaskId")) } returns true
        every { uuidFactory.createTaskId() } returns taskId
        every { userRepository.getCurrentUser().id } returns userId
        every { localDateTimeFactory() } returns creationDate

        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate

        every { taskFactory(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns task
        every { createTaskActivityFactory(taskId, creationDate) } returns mockk()
        justRun { taskRepository.addTask(task) }
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(requestModel)

        // then
        verify { taskRepository.addTask(task) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("taskId")
        val userId = UserId("ownerId")
        val creationDate: LocalDateTime = mockk()

        val requestModel = CreateTaskRequestModel(
            "name",
            "description",
            mockk(),
            mockk(),
            1,
            ProjectId("projectId"),
            TaskId("parentTaskId"),
            UserId("assigneeId"),
        )

        every { taskRepository.containsTask(TaskId("parentTaskId")) } returns true
        every { uuidFactory.createTaskId() } returns taskId
        every { userRepository.getCurrentUser().id } returns userId
        every { localDateTimeFactory() } returns creationDate

        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate

        every { taskFactory(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()) } returns task
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(taskId, creationDate) } returns activity
        justRun { taskRepository.addTask(any()) }
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(requestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
