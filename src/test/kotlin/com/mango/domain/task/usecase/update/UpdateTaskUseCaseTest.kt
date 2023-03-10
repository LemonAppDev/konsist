package com.mango.domain.task.usecase.update

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.request.UpdateTaskRequestModel
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class UpdateTaskUseCaseTest {
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val updateTaskNameUseCase: UpdateTaskNameUseCase = mockk()
    private val updateTaskDescriptionUseCase: UpdateTaskDescriptionUseCase = mockk()
    private val updateTaskDueDateUseCase: UpdateTaskDueDateUseCase = mockk()
    private val updateTaskTargetDateUseCase: UpdateTaskTargetDateUseCase = mockk()
    private val updateTaskPriorityUseCase: UpdateTaskPriorityUseCase = mockk()
    private val updateTaskProjectUseCase: UpdateTaskProjectUseCase = mockk()
    private val updateTaskParentTaskUseCase: UpdateTaskParentTaskUseCase = mockk()
    private val updateTaskAssigneeUseCase: UpdateTaskAssigneeUseCase = mockk()
    private val updateTaskCompleteDateUseCase: UpdateTaskCompleteDateUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = UpdateTaskUseCase(
        localDateTimeFactory,
        updateTaskNameUseCase,
        updateTaskDescriptionUseCase,
        updateTaskDueDateUseCase,
        updateTaskTargetDateUseCase,
        updateTaskPriorityUseCase,
        updateTaskProjectUseCase,
        updateTaskParentTaskUseCase,
        updateTaskAssigneeUseCase,
        updateTaskCompleteDateUseCase,
        getTaskOrThrowUseCase,
    )

    @Test
    fun `calls all update tasks use cases()`() {
        // given
        val task: Task = mockk()
        val taskId = getTaskId1()
        every { task.value } returns taskId
        val updDate: LocalDateTime = mockk()
        val newName = "new name"
        val newDescription = "new description"
        val newDueDate: LocalDateTime = mockk()
        val newTargetDate: LocalDateTime = mockk()
        val newPriority = com.mango.domain.task.model.Priority.PRIORITY_5
        val newProjectId = getProjectId1()
        val newParentTaskId = getTaskId2()
        val newAssigneeId = getUserId1()

        val updateTaskRequestModel = UpdateTaskRequestModel(
            taskId = taskId,
            name = newName,
            description = newDescription,
            dueDate = newDueDate,
            targetDate = newTargetDate,
            priority = newPriority,
            projectId = newProjectId,
            parentTaskId = newParentTaskId,
            assigneeId = newAssigneeId,
            isCompleted = true,
        )

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
        sut(updateTaskRequestModel)

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
        every { task.value } returns taskId

        val updDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns updDate

        val updateTaskRequestModel = UpdateTaskRequestModel(
            taskId = taskId,
            name = null,
            description = null,
            dueDate = null,
            targetDate = null,
            priority = null,
            projectId = null,
            parentTaskId = null,
            assigneeId = null,
            isCompleted = isCompleted,
        )
        every { getTaskOrThrowUseCase(taskId) } returns task
        justRun { updateTaskCompleteDateUseCase(taskId, isCompleted, updDate) }

        // when
        sut(updateTaskRequestModel)

        // then
        verify { updateTaskCompleteDateUseCase(taskId, isCompleted, updDate) }
    }
}
