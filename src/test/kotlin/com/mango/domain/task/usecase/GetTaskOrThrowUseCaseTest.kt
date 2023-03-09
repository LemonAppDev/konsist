package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepository
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetTaskOrThrowUseCaseTest {
    private val taskRepository: TaskRepository = mockk()

    private val sut = GetTaskOrThrowUseCase(
        taskRepository,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = getTaskId1()
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(taskId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `returns task`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { taskRepository.getTask(taskId) } returns task

        // when
        val actual = sut(taskId)

        // then
        actual shouldBeEqualTo task
    }
}
