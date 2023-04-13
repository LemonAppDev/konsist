package com.lemon.mango.domain.comment.usecase

import com.lemon.mango.data.comment.CommentRepositoryImpl
import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.common.model.BusinessTestModel.getCommentId1
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetCommentOrThrowUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()

    private val sut = GetCommentOrThrowUseCase(
        commentRepository,
    )

    @Test
    fun `throws exception when comment doesn't exist`() {
        // given
        val commentId = getCommentId1()
        every { commentRepository.getComment(commentId) } returns null

        // when
        val actual = { sut(commentId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Comment with id: $commentId doesn't exist"
    }

    @Test
    fun `returns task`() {
        // given
        val commentId = getCommentId1()
        val comment: Comment = mockk()
        every { commentRepository.getComment(commentId) } returns comment

        // when
        val actual = sut(commentId)

        // then
        actual shouldBeEqualTo comment
    }
}
