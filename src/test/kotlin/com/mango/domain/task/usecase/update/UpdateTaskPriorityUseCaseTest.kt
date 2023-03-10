package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskPriorityUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase = mockk()

    private val sut = UpdateTaskPriorityUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        updateTaskActivityUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldPriority: com.mango.domain.task.model.Priority = mockk()
        val newPriority: com.mango.domain.task.model.Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.getTask(taskId) } returns oldTask
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newPriority.toString(),
                oldPriority.toString(),
                TaskActivityType.UPDATE_PRIORITY,
            )
        } returns mockk()

        // when
        sut(taskId, newPriority, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `calls updateTaskActivityUseCase`() {
        // given
        val taskId = getTaskId1()
        val oldPriority: com.mango.domain.task.model.Priority = mockk()
        val newPriority: com.mango.domain.task.model.Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newPriority.toString(),
                oldPriority.toString(),
                TaskActivityType.UPDATE_PRIORITY,
            )
        } returns mockk()

        // when
        sut(taskId, newPriority, date)

        // then
        verify {
            updateTaskActivityUseCase(
                taskId,
                date,
                newPriority.toString(),
                oldPriority.toString(),
                TaskActivityType.UPDATE_PRIORITY,
            )
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldPriority = com.mango.domain.task.model.Priority.PRIORITY_1
        val newPriority = com.mango.domain.task.model.Priority.PRIORITY_1
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newPriority.toString(),
                oldPriority.toString(),
                TaskActivityType.UPDATE_PRIORITY,
            )
        } returns mockk()

        // when
        sut(taskId, newPriority, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            updateTaskActivityUseCase(
                taskId,
                date,
                newPriority.toString(),
                oldPriority.toString(),
                TaskActivityType.UPDATE_PRIORITY,
            )
        }
    }
}
