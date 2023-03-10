package com.mango.domain.comment.usecase

import com.mango.data.comment.CommentRepositoryImpl
import com.mango.domain.activity.usecase.DeleteCommentActivityUseCase
import com.mango.domain.comment.model.Comment
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteCommentUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val deleteCommentActivityUseCase: DeleteCommentActivityUseCase = mockk()

    private val sut = DeleteCommentUseCase(
        commentRepository,
        localDateTimeFactory,
        deleteCommentActivityUseCase,
    )

    @Test
    fun `delete comment from repository`() {
        // given
        val commentId = getCommentId1()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { commentRepository.getComment(commentId) } returns comment
        justRun { commentRepository.deleteComment(comment) }
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { deleteCommentActivityUseCase(comment, date) } returns mockk()

        // when
        sut(commentId)

        // then
        verify { commentRepository.deleteComment(comment) }
    }

    @Test
    fun `calls deleteCommentActivityUseCase`() {
        // given
        val commentId = getCommentId1()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { commentRepository.getComment(commentId) } returns comment
        justRun { commentRepository.deleteComment(comment) }
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { deleteCommentActivityUseCase(comment, date) } returns mockk()

        // when
        sut(commentId)

        // then
        verify { deleteCommentActivityUseCase(comment, date) }
    }
}
