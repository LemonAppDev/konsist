package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getTaskId3
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskParentTaskUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase = mockk()

    private val sut = UpdateTaskParentTaskUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        updateTaskActivityUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId3()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, parentTaskId = oldParentTaskId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns mockk()
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.getTask(taskId) } returns oldTask
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
                TaskActivityType.UPDATE_PARENT_TASK,
            )
        } returns mockk()

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `calls updateTaskActivityUseCase`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId3()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, parentTaskId = oldParentTaskId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns mockk()
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
                TaskActivityType.UPDATE_PARENT_TASK,
            )
        } returns mockk()

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify {
            updateTaskActivityUseCase(
                taskId,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
                TaskActivityType.UPDATE_PARENT_TASK,
            )
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, parentTaskId = oldParentTaskId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns mockk()
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
                TaskActivityType.UPDATE_PARENT_TASK,
            )
        } returns mockk()

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            updateTaskActivityUseCase(
                taskId,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
                TaskActivityType.UPDATE_PARENT_TASK,
            )
        }
    }
}
