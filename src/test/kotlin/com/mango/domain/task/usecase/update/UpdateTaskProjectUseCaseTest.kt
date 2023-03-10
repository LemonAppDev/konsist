package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getProjectId2
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.project.usecase.GetProjectOrThrowUseCase
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskProjectUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase = mockk()

    private val sut = UpdateTaskProjectUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        getProjectOrThrowUseCase,
        updateTaskActivityUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newProjectId.toString(),
                oldProjectId.toString(),
                TaskActivityType.UPDATE_PROJECT,
            )
        } returns mockk()

        // when
        sut(taskId, newProjectId, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `calls updateTaskActivityUseCase`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newProjectId.toString(),
                oldProjectId.toString(),
                TaskActivityType.UPDATE_PROJECT,
            )
        } returns mockk()

        // when
        sut(taskId, newProjectId, date)

        // then
        verify {
            updateTaskActivityUseCase(
                taskId,
                date,
                newProjectId.toString(),
                oldProjectId.toString(),
                TaskActivityType.UPDATE_PROJECT,
            )
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId1()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        every {
            updateTaskActivityUseCase(
                taskId,
                date,
                newProjectId.toString(),
                oldProjectId.toString(),
                TaskActivityType.UPDATE_PROJECT,
            )
        } returns mockk()

        // when
        sut(taskId, newProjectId, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            updateTaskActivityUseCase(taskId, date, newProjectId.toString(), oldProjectId.toString(), TaskActivityType.UPDATE_PROJECT)
        }
    }
}
