package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.lemon.mango.domain.common.model.BusinessTestModel.getFutureDate1
import com.lemon.mango.domain.common.model.BusinessTestModel.getFutureDate2
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import com.lemon.mango.domain.task.model.Priority.PRIORITY_5
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class UpdateTaskUseCaseTest {
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val updateTaskAssigneeUseCase: UpdateTaskAssigneeUseCase = mockk()
    private val updateTaskCompleteDateUseCase: UpdateTaskCompleteDateUseCase = mockk()
    private val updateTaskDescriptionUseCase: UpdateTaskDescriptionUseCase = mockk()
    private val updateTaskDueDateUseCase: UpdateTaskDueDateUseCase = mockk()
    private val updateTaskNameUseCase: UpdateTaskNameUseCase = mockk()
    private val updateTaskParentTaskUseCase: UpdateTaskParentTaskUseCase = mockk()
    private val updateTaskPriorityUseCase: UpdateTaskPriorityUseCase = mockk()
    private val updateTaskProjectUseCase: UpdateTaskProjectUseCase = mockk()
    private val updateTaskTargetDateUseCase: UpdateTaskTargetDateUseCase = mockk()

    private val sut = UpdateTaskUseCase(
        getTaskOrThrowUseCase,
        localDateTimeFactory,
        updateTaskAssigneeUseCase,
        updateTaskCompleteDateUseCase,
        updateTaskDescriptionUseCase,
        updateTaskDueDateUseCase,
        updateTaskNameUseCase,
        updateTaskParentTaskUseCase,
        updateTaskPriorityUseCase,
        updateTaskProjectUseCase,
        updateTaskTargetDateUseCase,
    )

    @Test
    fun `calls all update tasks use cases`() {
        // given
        val task: Task = mockk()
        val taskId = getTaskId1()
        every { task.id } returns taskId
        val updDate = getCurrentDate()
        val newName = "new name"
        val newDescription = "new description"
        val newDueDate = getFutureDate1()
        val newTargetDate = getFutureDate2()
        val newPriority = PRIORITY_5
        val newProjectId = getProjectId1()
        val newParentTaskId = getTaskId2()
        val newAssigneeId = getUserId1()

        every { getTaskOrThrowUseCase(taskId) } returns task
        every { localDateTimeFactory() } returns updDate
        justRun { updateTaskNameUseCase(taskId, newName, updDate) }
        justRun { updateTaskDescriptionUseCase(taskId, newDescription, updDate) }
        justRun { updateTaskDueDateUseCase(taskId, newDueDate, updDate) }
        justRun { updateTaskTargetDateUseCase(taskId, newTargetDate, updDate) }
        justRun { updateTaskPriorityUseCase(taskId, newPriority, updDate) }
        justRun { updateTaskProjectUseCase(taskId, newProjectId, updDate) }
        justRun { updateTaskParentTaskUseCase(taskId, newParentTaskId, updDate) }
        justRun { updateTaskAssigneeUseCase(taskId, newAssigneeId, updDate) }
        justRun { updateTaskCompleteDateUseCase(taskId, true, updDate) }

        // when
        sut(taskId, newName, newDescription, newDueDate, newTargetDate, newPriority, newProjectId, newParentTaskId, newAssigneeId, true)

        // then
        verifyAll {
            updateTaskNameUseCase(taskId, newName, updDate)
            updateTaskDescriptionUseCase(taskId, newDescription, updDate)
            updateTaskDueDateUseCase(taskId, newDueDate, updDate)
            updateTaskTargetDateUseCase(taskId, newTargetDate, updDate)
            updateTaskPriorityUseCase(taskId, newPriority, updDate)
            updateTaskProjectUseCase(taskId, newProjectId, updDate)
            updateTaskParentTaskUseCase(taskId, newParentTaskId, updDate)
            updateTaskAssigneeUseCase(taskId, newAssigneeId, updDate)
            updateTaskCompleteDateUseCase(taskId, true, updDate)
        }
    }

    @ParameterizedTest(name = "calls updateTaskCompleteDateUseCase() when isComplete is {0}")
    @ValueSource(booleans = [true, false])
    fun `calls updateTaskCompleteDateUseCase()`(isCompleted: Boolean) {
        // given
        val task: Task = mockk()
        val taskId = getTaskId1()
        every { task.id } returns taskId
        every { task.name } returns "name"

        val updDate = getCurrentDate()
        every { localDateTimeFactory() } returns updDate

        every { getTaskOrThrowUseCase(taskId) } returns task
        justRun { updateTaskNameUseCase(taskId, "name", updDate) }
        justRun { updateTaskDescriptionUseCase(taskId, null, updDate) }
        justRun { updateTaskDueDateUseCase(taskId, null, updDate) }
        justRun { updateTaskTargetDateUseCase(taskId, null, updDate) }
        justRun { updateTaskPriorityUseCase(taskId, null, updDate) }
        justRun { updateTaskProjectUseCase(taskId, null, updDate) }
        justRun { updateTaskParentTaskUseCase(taskId, null, updDate) }
        justRun { updateTaskAssigneeUseCase(taskId, null, updDate) }
        justRun { updateTaskCompleteDateUseCase(taskId, isCompleted, updDate) }

        // when
        sut(taskId, "name", null, null, null, null, null, null, null, isCompleted)

        // then
        verify { updateTaskCompleteDateUseCase(taskId, isCompleted, updDate) }
    }
}
