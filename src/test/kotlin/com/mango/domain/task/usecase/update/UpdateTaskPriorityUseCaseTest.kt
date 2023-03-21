package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.model.TaskActivityType.UPDATE_PRIORITY
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.Priority.PRIORITY_1
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskPriorityUseCaseTest {
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = UpdateTaskPriorityUseCase(
        addTaskActivityUseCase,
        getTaskOrThrowUseCase,
        taskRepository,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldPriority: Priority = mockk()
        val newPriority: Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.getTask(taskId) } returns oldTask
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PRIORITY,
                date,
                newPriority.toString(),
                oldPriority.toString(),
            )
        }

        // when
        sut(taskId, newPriority, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to repository`() {
        // given
        val taskId = getTaskId1()
        val oldPriority: Priority = mockk()
        val newPriority: Priority = mockk()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PRIORITY,
                date,
                newPriority.toString(),
                oldPriority.toString(),
            )
        }

        // when
        sut(taskId, newPriority, date)

        // then
        verify {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PRIORITY,
                date,
                newPriority.toString(),
                oldPriority.toString(),
            )
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldPriority = PRIORITY_1
        val newPriority = PRIORITY_1
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, priority = oldPriority)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(priority = newPriority)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PRIORITY,
                date,
                newPriority.toString(),
                oldPriority.toString(),
            )
        }

        // when
        sut(taskId, newPriority, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            addTaskActivityUseCase(
                taskId,
                UPDATE_PRIORITY,
                date,
                newPriority.toString(),
                oldPriority.toString(),
            )
        }
    }
}
