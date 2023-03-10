package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CheckTaskIdUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = CheckTaskIdUseCase(
        taskRepository,
    )

    @Test
    fun `throw exception when task doesn't exist`() {
        // given
        val taskId = getTaskId1()
        every { taskRepository.containsTask(taskId) } returns false

        // when
        val actual = { sut(taskId) }

        // then
        actual shouldThrow IllegalStateException::class withMessage "Task with id: $taskId doesn't exist"
    }
}
