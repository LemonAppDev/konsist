package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepository
import com.mango.domain.task.model.TaskId
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.util.UUID

class CheckTaskIdUseCaseTest {
    private val taskRepository: TaskRepository = mockk()

    private val sut = CheckTaskIdUseCase(
        taskRepository,
    )

    @Test
    fun `throw exception when task doesn't exist`() {
        // given
        val taskId = TaskId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
        every { taskRepository.containsTask(taskId) } returns false

        // when
        val actual = { sut(taskId) }

        // then
        actual shouldThrow IllegalStateException::class withMessage "Task with id: $taskId doesn't exist"
    }
}
