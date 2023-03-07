package com.mango.business.usecase.task

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.factory.TaskFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivity
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.activity.task.TaskActivity
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.business.usecase.common.RequireDateIsNowOrLaterUseCase
import com.mango.business.usecase.project.CheckProjectIdUseCase
import com.mango.business.usecase.user.CheckUserIdUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CreateTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val createTaskActivityFactory: CreateTaskActivityFactory = spyk()
    private val activityRepository: ActivityRepository = mockk()
    private val taskFactory: TaskFactory = mockk()
    private val checkTaskIdUseCase: CheckTaskIdUseCase = mockk()
    private val checkUserIdUseCase: CheckUserIdUseCase = mockk()
    private val checkProjectIdUseCase: CheckProjectIdUseCase = mockk()
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()

    private val sut = CreateTaskUseCase(
        taskRepository,
        createTaskActivityFactory,
        activityRepository,
        taskFactory,
        checkTaskIdUseCase,
        checkUserIdUseCase,
        checkProjectIdUseCase,
        requireDateIsNowOrLaterUseCase,
        localDateTimeFactory,
    )

    @Test
    fun `creates and adds new task to tasks list`() {
        // given
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val taskId = TaskId("id")
        val projectId = ProjectId("projectId")
        val parentTaskId = TaskId("parentTaskId")
        val assigneeId = UserId("assigneeId")
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
        justRun { checkProjectIdUseCase(projectId) }
        justRun { checkTaskIdUseCase(parentTaskId) }
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(any(), creationDate) }

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, creationDate) } returns task

        justRun { taskRepository.addTask(task) }
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        every { createTaskActivityFactory(taskId, creationDate) } returns mockk()
        justRun { activityRepository.addActivity(any<TaskActivity>()) }

        // when
        sut.invoke(createTaskRequestModel)

        // then
        verify { taskRepository.addTask(task) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val projectId = ProjectId("projectId")
        val parentTaskId = TaskId("parentTaskId")
        val assigneeId = UserId("assigneeId")
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
        justRun { checkProjectIdUseCase(projectId) }
        justRun { checkTaskIdUseCase(parentTaskId) }
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(any(), creationDate) }

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, creationDate) } returns task
        justRun { taskRepository.addTask(task) }
        every { task.id } returns TaskId("id")
        every { task.creationDate } returns creationDate
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(TaskId("id"), creationDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut.invoke(createTaskRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
