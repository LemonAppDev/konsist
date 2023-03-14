package com.mango.domain.task.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getProjectId2
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.common.model.BusinessTestModel.getUserId2
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.project.usecase.CheckProjectIdUseCase
import com.mango.domain.task.TaskFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.user.usecase.CheckUserIdUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

class CreateTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val taskFactory: TaskFactory = mockk()
    private val checkUserIdUseCase: CheckUserIdUseCase = mockk()
    private val checkProjectIdUseCase: CheckProjectIdUseCase = mockk()
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val taskActivityFactory: TaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = CreateTaskUseCase(
        taskRepository,
        taskFactory,
        checkUserIdUseCase,
        checkProjectIdUseCase,
        requireDateIsNowOrLaterUseCase,
        localDateTimeFactory,
        taskActivityFactory,
        activityRepository,
        getTaskOrThrowUseCase,
    )

    @Test
    fun `throws exception when task and parent task are not in the same project`() {
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val taskProjectId = getProjectId1()
        val parentTaskProjectId = getProjectId2()
        val parentTask: Task = mockk()
        val parentTaskId = getTaskId2()
        every { parentTask.parentTaskId } returns parentTaskId
        every { parentTask.projectId } returns parentTaskProjectId
        val assigneeId = getUserId1()
        val dueDate = LocalDateTime.of(2023, Month.MARCH, 1, 20, 0, 0)
        val targetDate = LocalDateTime.of(2023, Month.MARCH, 10, 20, 0, 0)
        val createTaskRequestModel = CreateTaskRequestModel(
            "name",
            "description",
            dueDate,
            targetDate,
            1,
            taskProjectId,
            parentTaskId,
            assigneeId,
        )
        justRun { checkProjectIdUseCase(taskProjectId) }
        every { getTaskOrThrowUseCase(parentTaskId) } returns parentTask
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        // when
        val actual = { sut.invoke(createTaskRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task and parent task are not in the same project"
    }

    @Test
    fun `creates and adds new task to tasks list`() {
        // given
        val taskId = getTaskId1()
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val projectId = getProjectId1()
        val parentTask: Task = mockk()
        val parentTaskId = getTaskId2()
        every { parentTask.parentTaskId } returns parentTaskId
        every { parentTask.projectId } returns projectId
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
        every { getTaskOrThrowUseCase(parentTaskId) } returns parentTask
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, creationDate) } returns task

        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(task) } returns repositoryTask
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        val creatorId = getUserId2()
        every { task.ownerId } returns creatorId
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(taskId, creationDate, TaskActivityType.CREATE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut.invoke(createTaskRequestModel)

        // then
        verify { taskRepository.saveTask(task) }
    }

    @Test
    fun `adds activity to repository`() {
        // given
        val taskId = getTaskId1()
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val projectId = getProjectId1()
        val parentTask: Task = mockk()
        val parentTaskId = getTaskId2()
        every { parentTask.parentTaskId } returns parentTaskId
        every { parentTask.projectId } returns projectId
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
        every { getTaskOrThrowUseCase(parentTaskId) } returns parentTask
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, creationDate) } returns task
        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(task) } returns repositoryTask
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(taskId, creationDate, TaskActivityType.CREATE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut.invoke(createTaskRequestModel)

        // then
        verify { activityRepository.addTaskActivity(activity) }
    }

    @Test
    fun `returns task`() {
        // given
        val taskId = getTaskId1()
        val creationDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns creationDate
        val projectId = getProjectId1()
        val parentTask: Task = mockk()
        val parentTaskId = getTaskId2()
        every { parentTask.parentTaskId } returns parentTaskId
        every { parentTask.projectId } returns projectId
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
        every { getTaskOrThrowUseCase(parentTaskId) } returns parentTask
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        val task: Task = mockk()
        every { taskFactory(createTaskRequestModel, creationDate) } returns task

        val expected: Task = mockk()
        every { taskRepository.saveTask(task) } returns expected
        every { task.id } returns taskId
        every { task.creationDate } returns creationDate
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(taskId, creationDate, TaskActivityType.CREATE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        val actual = sut.invoke(createTaskRequestModel)

        // then
        actual shouldBeEqualTo expected
    }
}
