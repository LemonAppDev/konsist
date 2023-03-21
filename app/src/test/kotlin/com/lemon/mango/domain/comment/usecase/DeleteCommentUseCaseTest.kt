package com.lemon.mango.domain.comment.usecase

import com.lemon.mango.data.comment.CommentRepositoryImpl
import com.lemon.mango.domain.activity.model.CommentActivityType.DELETE_COMMENT
import com.lemon.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.common.model.BusinessTestModel.getCommentId1
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class DeleteCommentUseCaseTest {
    private val addCommentActivityUseCase: AddCommentActivityUseCase = mockk()
    private val commentRepository: CommentRepositoryImpl = mockk()

    private val sut = DeleteCommentUseCase(
        addCommentActivityUseCase,
        commentRepository,
    )

    @Test
    fun `delete comment from repository`() {
        // given
        val commentId = getCommentId1()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { commentRepository.getComment(commentId) } returns comment
        justRun { commentRepository.deleteComment(comment) }
        justRun { addCommentActivityUseCase(comment, DELETE_COMMENT) }

        // when
        sut(commentId)

        // then
        verify { commentRepository.deleteComment(comment) }
    }

    @Test
    fun `adds comment activity to repository`() {
        // given
        val commentId = getCommentId1()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { commentRepository.getComment(commentId) } returns comment
        justRun { commentRepository.deleteComment(comment) }
        justRun { addCommentActivityUseCase(comment, DELETE_COMMENT) }

        // when
        sut(commentId)

        // then
        verify { addCommentActivityUseCase(comment, DELETE_COMMENT) }
    }
}
