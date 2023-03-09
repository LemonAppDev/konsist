package com.mango.domain.task.usecase

import com.mango.data.activity.ActivityRepositoryImpl
import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.project.usecase.CheckProjectIdUseCase
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.user.usecase.CheckUserIdUseCase
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
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val createTaskActivityFactory: com.mango.domain.task.activity.CreateTaskActivityFactory = spyk()
    private val activityRepository: ActivityRepositoryImpl = mockk()
    private val taskFactory: com.mango.domain.task.TaskFactory = mockk()
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
        justRun { activityRepository.addActivity(any<com.mango.domain.task.activity.TaskActivity>()) }

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
        val activity: com.mango.domain.task.activity.CreateTaskActivity = mockk()
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
        val activity: com.mango.domain.task.activity.CreateTaskActivity = mockk()
        every { createTaskActivityFactory(taskId, creationDate) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        val actual = sut.invoke(createTaskRequestModel)

        // then
        actual shouldBe repositoryTask
    }
}
