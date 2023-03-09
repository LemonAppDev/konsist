package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GetAllTasksUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()

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
