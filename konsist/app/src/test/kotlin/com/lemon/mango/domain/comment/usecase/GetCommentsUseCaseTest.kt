package com.lemon.mango.domain.comment.usecase

import com.lemon.mango.data.comment.CommentRepositoryImpl
import com.lemon.mango.data.task.TaskRepositoryImpl
import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetCommentsUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = GetCommentsUseCase(
        commentRepository,
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
    fun `returns list of comments for given task`() {
        // given
        val taskId = getTaskId1()
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
