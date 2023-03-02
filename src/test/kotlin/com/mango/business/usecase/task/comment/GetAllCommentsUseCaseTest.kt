package com.mango.business.usecase.task.comment

import com.mango.business.model.Comment
import com.mango.business.model.Task
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.CommentRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetAllCommentsUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val commentRepository: CommentRepository = mockk()

    private val sut = GetAllCommentsUseCase(
        taskRepository,
        commentRepository,
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
    fun `returns list of comments for given task`() {
        // given
        val taskId = TaskId("id")
        val task: Task = mockk()
        val comments = listOf<Comment>()
        every { task.id } returns taskId
        every { taskRepository.getTask(taskId) } returns task
        every { commentRepository.comments } returns comments

        // when
        val actual = sut(taskId)

        // then
        actual shouldBeEqualTo comments
    }
}
