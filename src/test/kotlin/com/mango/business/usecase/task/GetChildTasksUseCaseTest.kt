package com.mango.business.usecase.task

import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.model.Task
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetChildTasksUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val checkTaskIdUseCase: CheckTaskIdUseCase = mockk()

    private val sut = GetChildTasksUseCase(
        taskRepository,
        checkTaskIdUseCase,
    )

    @Test
    fun `get childTasks for given task`() {
        // given
        val taskId = getTaskId1()
        justRun { checkTaskIdUseCase(taskId) }
        val task1: Task = mockk()
        every { task1.parentTaskId } returns taskId
        val task2: Task = mockk()
        every { task2.parentTaskId } returns taskId
        val expected = listOf(task1, task2)
        every { taskRepository.tasks } returns expected

        // when
        val actual = sut(taskId)

        // then
        actual shouldBeEqualTo expected
    }
}
