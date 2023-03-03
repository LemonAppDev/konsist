package com.mango.business.usecase.task

import com.mango.business.model.Task
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()

    private val sut = GetTaskUseCase(
        taskRepository,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = TaskId("id")
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(taskId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `returns task`() {
        // given
        val taskId = TaskId("taskId")
        val task: Task = mockk()
        every { taskRepository.getTask(taskId) } returns task

        // when
        val actual = sut(taskId)

        // then
        actual shouldBeEqualTo task
    }
}
