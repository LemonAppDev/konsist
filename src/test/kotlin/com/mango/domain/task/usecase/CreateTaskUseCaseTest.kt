package com.mango.domain.task.usecase

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.mango.domain.common.model.BusinessTestModel.getFutureDate1
import com.mango.domain.common.model.BusinessTestModel.getFutureDate2
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getProjectId2
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.project.usecase.CheckProjectIdUseCase
import com.mango.domain.task.TaskFactory
import com.mango.domain.task.model.Task
import com.mango.domain.user.usecase.CheckUserIdUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CreateTaskUseCaseTest {
    private val taskFactory: TaskFactory = mockk()
    private val checkUserIdUseCase: CheckUserIdUseCase = mockk()
    private val checkProjectIdUseCase: CheckProjectIdUseCase = mockk()
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val saveTaskUseCase: SaveTaskUseCase = mockk()

    private val sut = CreateTaskUseCase(
        taskFactory,
        checkUserIdUseCase,
        checkProjectIdUseCase,
        requireDateIsNowOrLaterUseCase,
        localDateTimeFactory,
        getTaskOrThrowUseCase,
        saveTaskUseCase,
    )

    @Test
    fun `throws exception when task and parent task are not in the same project`() {
        val creationDate = getCurrentDate()
        val name = "name"
        val desc = "description"
        every { localDateTimeFactory() } returns creationDate
        val taskProjectId = getProjectId1()
        val parentTaskProjectId = getProjectId2()
        val parentTask: Task = mockk()
        val parentTaskId = getTaskId2()
        every { parentTask.parentTaskId } returns parentTaskId
        every { parentTask.projectId } returns parentTaskProjectId
        val assigneeId = getUserId1()
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
        justRun { checkProjectIdUseCase(taskProjectId) }
        every { getTaskOrThrowUseCase(parentTaskId) } returns parentTask
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        // when
        val actual = { sut(name, desc, dueDate, targetDate, 3, taskProjectId, parentTaskId, assigneeId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task and parent task are not in the same project"
    }

    @Test
    fun `calls 'saveTaskUseCase()`() {
        // given
        val name = "name"
        val desc = "description"
        val creationDate = getCurrentDate()
        every { localDateTimeFactory() } returns creationDate
        val projectId = getProjectId1()
        val parentTask: Task = mockk()
        val parentTaskId = getTaskId1()
        every { parentTask.parentTaskId } returns parentTaskId
        every { parentTask.projectId } returns projectId
        val assigneeId = getUserId1()
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
        justRun { checkProjectIdUseCase(projectId) }
        every { getTaskOrThrowUseCase(parentTaskId) } returns parentTask
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }
        val task: Task = mockk()
        every { taskFactory(name, desc, dueDate, targetDate, 3, projectId, parentTaskId, assigneeId, creationDate) } returns task
        every { saveTaskUseCase(task, creationDate) } returns mockk()

        // when
        sut(name, desc, dueDate, targetDate, 3, projectId, parentTaskId, assigneeId)

        // then
        verify { saveTaskUseCase(task, creationDate) }
    }

    @Test
    fun `returns task`() {
        // given
        val name = "name"
        val desc = "description"
        val creationDate = getCurrentDate()
        every { localDateTimeFactory() } returns creationDate
        val projectId = getProjectId1()
        val parentTask: Task = mockk()
        val parentTaskId = getTaskId1()
        every { parentTask.parentTaskId } returns parentTaskId
        every { parentTask.projectId } returns projectId
        val assigneeId = getUserId1()
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
        justRun { checkProjectIdUseCase(projectId) }
        every { getTaskOrThrowUseCase(parentTaskId) } returns parentTask
        justRun { checkUserIdUseCase(assigneeId) }
        justRun { requireDateIsNowOrLaterUseCase(dueDate) }
        justRun { requireDateIsNowOrLaterUseCase(targetDate) }

        val task: Task = mockk()
        every { taskFactory(name, desc, dueDate, targetDate, 3, projectId, parentTaskId, assigneeId, creationDate) } returns task
        val expected: Task = mockk()

        every { saveTaskUseCase(task, creationDate) } returns expected

        // when
        val actual = sut(name, desc, dueDate, targetDate, 3, projectId, parentTaskId, assigneeId)

        // then
        actual shouldBeEqualTo expected
    }
}
