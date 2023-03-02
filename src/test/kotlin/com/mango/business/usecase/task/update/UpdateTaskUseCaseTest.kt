package com.mango.business.usecase.task.update

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.request.UpdateTaskRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class UpdateTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
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

    private val sut = UpdateTaskUseCase(
        taskRepository,
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
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val updateTaskRequestModel: UpdateTaskRequestModel = mockk()
        val taskId = TaskId("id")
        every { updateTaskRequestModel.taskId } returns taskId
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(updateTaskRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with taskId: $taskId doesn't exist"
    }

    @Test
    fun `calls all update tasks use cases()`() {
        // given
        val task: Task = mockk()
        val taskId = TaskId("id")
        every { task.id } returns taskId
        every { taskRepository.getTask(taskId) } returns task
        val updDate: LocalDateTime = mockk()
        val newName = "new name"
        val newDescription = "new description"
        val newDueDate: LocalDateTime = mockk()
        val newTargetDate: LocalDateTime = mockk()
        val newPriority = Priority.PRIORITY_5
        val newProjectId = ProjectId("new projectId")
        val newParentTaskId = TaskId("new parentTaskId")
        val newAssigneeId = UserId("new assigneeId")

        val updateTaskRequestModel = UpdateTaskRequestModel(
            taskId = TaskId("id"),
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
        val taskId = TaskId("id")
        every { task.id } returns taskId
        every { taskRepository.getTask(taskId) } returns task

        val updDate: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns updDate

        val updateTaskRequestModel = UpdateTaskRequestModel(
            taskId = TaskId("id"),
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

        justRun { updateTaskCompleteDateUseCase(taskId, isCompleted, updDate) }

        // when
        sut(updateTaskRequestModel)

        // then
        verify { updateTaskCompleteDateUseCase(taskId, isCompleted, updDate) }
    }
}
