package com.mango.business.usecase.task

import com.mango.business.factory.TaskFactory
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
        every { taskRepository.containsTask(TaskId("parentId")) } returns false

        // when
        val actual = { sut(createTaskRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Parent task with id: $parentId doesn't exist"
    }

    @Test
    fun `creates and adds new task to tasks list`() {
        // given
        val date: LocalDateTime = mockk()
        val createTaskRequestModel =
            CreateTaskRequestModel(
                "name",
                "description",
                date,
                date,
                1,
                ProjectId("projectId"),
                null,
                UserId("assigneeId"),
            )
        every { userRepository.getCurrentUser().id } returns UserId("ownerId")

        val task: Task = mockk()
        every {
            taskFactory(
                "name",
                UserId("ownerId"),
                ProjectId("projectId"),
                "description",
                date,
                date,
                Priority.getByValue(1),
                null,
                UserId("assigneeId"),
            )
        } returns task

        justRun { taskRepository.addTask(task) }
        every { task.id } returns TaskId("id")
        every { task.creationDate } returns date
        every { createTaskActivityFactory(TaskId("id"), date) } returns mockk()
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(createTaskRequestModel)

        // then
        verify { taskRepository.addTask(task) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val date: LocalDateTime = mockk()
        val createTaskRequestModel =
            CreateTaskRequestModel(
                "name",
                "description",
                date,
                date,
                1,
                ProjectId("projectId"),
                null,
                UserId("assigneeId"),
            )
        every { userRepository.getCurrentUser().id } returns UserId("ownerId")

        val task: Task = mockk()
        every {
            taskFactory(
                "name",
                UserId("ownerId"),
                ProjectId("projectId"),
                "description",
                date,
                date,
                Priority.getByValue(1),
                null,
                UserId("assigneeId"),
            )
        } returns task
        justRun { taskRepository.addTask(task) }
        every { task.id } returns TaskId("id")
        every { task.creationDate } returns date
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(TaskId("id"), date) } returns activity
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(createTaskRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `calls all instructions when parentTaskId is not null and this parent exist`() {
        // given
        val date: LocalDateTime = mockk()
        val createTaskRequestModel =
            CreateTaskRequestModel(
                "name",
                "description",
                date,
                date,
                1,
                ProjectId("projectId"),
                TaskId("parentId"),
                UserId("assigneeId"),
            )
        every { userRepository.getCurrentUser().id } returns UserId("ownerId")
        every { taskRepository.containsTask(TaskId("parentId")) } returns true

        val task: Task = mockk()
        every {
            taskFactory(
                "name",
                UserId("ownerId"),
                ProjectId("projectId"),
                "description",
                date,
                date,
                Priority.getByValue(1),
                TaskId("parentId"),
                UserId("assigneeId"),
            )
        } returns task

        justRun { taskRepository.addTask(task) }
        every { task.id } returns TaskId("id")
        every { task.creationDate } returns date
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(TaskId("id"), date) } returns activity
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
