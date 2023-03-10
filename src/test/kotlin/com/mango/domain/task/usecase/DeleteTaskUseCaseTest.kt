package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.usecase.DeleteTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteTaskUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val deleteTaskActivityUseCase: DeleteTaskActivityUseCase = mockk()

    private val sut = DeleteTaskUseCase(
        taskRepository,
        localDateTimeFactory,
        deleteTaskActivityUseCase,
    )

    @Test
    fun `delete task in TaskRepository`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.value } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        every { deleteTaskActivityUseCase(taskId, date) } returns mockk()

        // when
        sut(taskId)

        // then
        verify { taskRepository.deleteTask(task) }
    }

    @Test
    fun `calls deleteTaskActivityUseCase method`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.value } returns taskId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        every { deleteTaskActivityUseCase(taskId, date) } returns mockk()

        // when
        sut(taskId)

        // then
        verify { deleteTaskActivityUseCase(taskId, date) }
    }
}
