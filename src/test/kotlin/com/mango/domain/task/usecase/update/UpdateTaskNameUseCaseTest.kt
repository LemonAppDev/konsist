package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.model.TaskActivityType.UPDATE_NAME
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskNameUseCaseTest {
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = UpdateTaskNameUseCase(
        addTaskActivityUseCase,
        getTaskOrThrowUseCase,
        taskRepository,
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
        justRun { addTaskActivityUseCase(taskId, UPDATE_NAME, date, newName, oldName) }

        // when
        sut(taskId, newName, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to repository`() {
        // given
        val taskId = getTaskId1()
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, name = oldName)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun { addTaskActivityUseCase(taskId, UPDATE_NAME, date, newName, oldName) }

        // when
        sut(taskId, newName, date)

        // then
        verify { addTaskActivityUseCase(taskId, UPDATE_NAME, date, newName, oldName) }
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
        justRun { addTaskActivityUseCase(taskId, UPDATE_NAME, date, newName, oldName) }

        // when
        sut(taskId, newName, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            addTaskActivityUseCase(taskId, UPDATE_NAME, date, newName, oldName)
        }
    }
}
