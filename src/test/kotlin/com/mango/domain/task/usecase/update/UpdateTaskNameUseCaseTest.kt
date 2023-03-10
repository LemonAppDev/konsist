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

class UpdateTaskNameUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase = mockk()

    private val sut = UpdateTaskNameUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        updateTaskActivityUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, name = oldName)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every { updateTaskActivityUseCase(taskId, date, newName, oldName, TaskActivityType.UPDATE_NAME) } returns mockk()

        // when
        sut(taskId, newName, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `calls updateTaskActivityUseCase`() {
        // given
        val taskId = getTaskId1()
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, name = oldName)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every { updateTaskActivityUseCase(taskId, date, newName, oldName, TaskActivityType.UPDATE_NAME) } returns mockk()

        // when
        sut(taskId, newName, date)

        // then
        verify { updateTaskActivityUseCase(taskId, date, newName, oldName, TaskActivityType.UPDATE_NAME) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldName = "name"
        val newName = "name"
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, name = oldName)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every { updateTaskActivityUseCase(taskId, date, newName, oldName, TaskActivityType.UPDATE_NAME) } returns mockk()

        // when
        sut(taskId, newName, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            updateTaskActivityUseCase(taskId, date, newName, oldName, TaskActivityType.UPDATE_NAME)
        }
    }
}
