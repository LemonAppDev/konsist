package com.mango.business.usecase.task

import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CheckTaskIdUseCaseTest {
    private val taskRepository: TaskRepository = mockk()

    private val sut = CheckTaskIdUseCase(
        taskRepository,
    )

    @Test
    fun `throw exception when task doesn't exist`() {
        // given
        val taskId = TaskId("id")
        every { taskRepository.containsTask(taskId) } returns false

        // when
        val actual = { sut(taskId) }

        // then
        actual shouldThrow IllegalStateException::class withMessage "Task with id: $taskId doesn't exist"
    }
}
