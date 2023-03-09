package com.mango.business.usecase.task

import com.mango.business.common.model.BusinessTestModel.getProjectId1
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.common.model.BusinessTestModel.getTaskId2
import com.mango.business.common.model.BusinessTestModel.getUserId1
import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.factory.TaskFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivity
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.activity.task.TaskActivity
import com.mango.business.model.request.task.CreateTaskRequestModel
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
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

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
        val taskId = getTaskId1()
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val projectId = getProjectId1()
        val parentTaskId = getTaskId2()
        val assigneeId = getUserId1()
        val dueDate = LocalDateTime.of(2023, Month.MARCH, 1, 20, 0, 0)
        val targetDate = LocalDateTime.of(2023, Month.MARCH, 10, 20, 0, 0)
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
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, creationDate) } returns task

        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(task) } returns repositoryTask
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        every { createTaskActivityFactory(taskId, creationDate) } returns mockk()
        justRun { activityRepository.addActivity(any<TaskActivity>()) }

        // when
        sut.invoke(createTaskRequestModel)

        // then
        verify { taskRepository.saveTask(task) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val taskId = getTaskId1()
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val projectId = getProjectId1()
        val parentTaskId = getTaskId2()
        val assigneeId = getUserId1()
        val dueDate = LocalDateTime.of(2023, Month.MARCH, 1, 20, 0, 0)
        val targetDate = LocalDateTime.of(2023, Month.MARCH, 10, 20, 0, 0)
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
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, creationDate) } returns task
        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(task) } returns repositoryTask
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(taskId, creationDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut.invoke(createTaskRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `returns task`() {
        // given
        val taskId = getTaskId1()
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val projectId = getProjectId1()
        val parentTaskId = getTaskId2()
        val assigneeId = getUserId1()
        val dueDate = LocalDateTime.of(2023, Month.MARCH, 1, 20, 0, 0)
        val targetDate = LocalDateTime.of(2023, Month.MARCH, 10, 20, 0, 0)
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
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, creationDate) } returns task

        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(task) } returns repositoryTask
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(taskId, creationDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        val actual = sut.invoke(createTaskRequestModel)

        // then
        actual shouldBe repositoryTask
    }
}
