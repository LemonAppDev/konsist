package com.mango.business.usecase.task

import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GetAllTasksUseCaseTest {
    private val taskRepository: TaskRepository = mockk()

    private val sut = GetAllTasksUseCase(
        taskRepository,
    )

    @Test
    fun `returns tasks from taskRepository`() {
        // given
        every { taskRepository.tasks } returns mockk()

        // when
        sut()

        // then
        verify { taskRepository.tasks }
    }
}
