package com.mango.business.usecase.comment

import com.mango.business.model.Comment
import com.mango.business.model.value.CommentId
import com.mango.persistence.repository.CommentRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetCommentUseCaseTest {
    private val commentRepository: CommentRepository = mockk()

    private val sut = GetCommentUseCase(
        commentRepository,
    )

    @Test
    fun `throws exception when comment doesn't exist`() {
        // given
        val commentId = CommentId("id")
        every { commentRepository.getComment(commentId) } returns null

        // when
        val actual = { sut(commentId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Comment with id: $commentId doesn't exist"
    }

    @Test
    fun `returns task`() {
        // given
        val commentId = CommentId("commentId")
        val comment: Comment = mockk()
        every { commentRepository.getComment(commentId) } returns comment

        // when
        val actual = sut(commentId)

        // then
        actual shouldBeEqualTo comment
    }
}
