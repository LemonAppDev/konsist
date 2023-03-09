package com.mango.data.comment

import com.mango.domain.comment.model.Comment
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.jvm.optionals.getOrNull

class CommentRepositoryTest {
    private val commentJpaRepository: CommentJpaRepository = mockk()
    private val commentToCommentJpaEntityMapper: CommentToCommentJpaEntityMapper = mockk()
    private val commentJpaEntityToCommentMapper: CommentJpaEntityToCommentMapper = mockk()

    private val sut = CommentRepository(
        commentJpaRepository,
        commentToCommentJpaEntityMapper,
        commentJpaEntityToCommentMapper,
    )

    @Test
    fun `saveComment() saves comment`() {
        // given
        val comment: Comment = mockk()
        val commentJpaEntity: CommentJpaEntity = mockk()
        every { commentToCommentJpaEntityMapper(comment) } returns commentJpaEntity
        every { commentJpaRepository.save(commentJpaEntity) } returns commentJpaEntity
        every { commentJpaEntityToCommentMapper(commentJpaEntity) } returns comment

        // when
        sut.saveComment(comment)

        // then
        verify { commentJpaRepository.save(commentJpaEntity) }
    }

    @Test
    fun `saveComment() returns comment`() {
        // given
        val comment: Comment = mockk()
        val commentJpaEntity: CommentJpaEntity = mockk()
        every { commentToCommentJpaEntityMapper(comment) } returns commentJpaEntity
        every { commentJpaRepository.save(commentJpaEntity) } returns commentJpaEntity
        every { commentJpaEntityToCommentMapper(commentJpaEntity) } returns comment

        // when
        val actual = sut.saveComment(comment)

        // then
        actual shouldBeEqualTo comment
    }

    @Test
    fun `deleteComment() deletes comment`() {
        // given
        val comment: Comment = mockk()
        val commentJpaEntity: CommentJpaEntity = mockk()
        every { commentToCommentJpaEntityMapper(comment) } returns commentJpaEntity
        justRun { commentJpaRepository.delete(commentJpaEntity) }
        every { commentJpaEntityToCommentMapper(commentJpaEntity) } returns comment

        // when
        sut.deleteComment(comment)

        // then
        verify { commentJpaRepository.delete(commentJpaEntity) }
    }

    @Test
    fun `getComment() return comment when it exist`() {
        // given
        val commentId = getCommentId1()
        val comment: Comment = mockk()
        val commentJpaEntity: CommentJpaEntity = mockk()
        every { commentJpaRepository.findById(commentId.value).getOrNull() } returns commentJpaEntity
        every { commentJpaEntityToCommentMapper(commentJpaEntity) } returns comment

        // when
        val actual = sut.getComment(commentId)

        // then
        actual shouldBeEqualTo comment
    }

    @Test
    fun `getComment() return null when it doesn't exist`() {
        // given
        val commentId = getCommentId1()
        every { commentJpaRepository.findById(commentId.value).getOrNull() } returns null

        // when
        val actual = sut.getComment(commentId)

        // then
        actual shouldBeEqualTo null
    }
}
