package com.mango.persistence.repository

import com.mango.business.model.Comment
import com.mango.business.model.value.CommentId
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.junit.jupiter.api.Test

class CommentRepositoryTest {
    private val sut = CommentRepository()

    @Test
    fun `addComment() adds new comment to comments`() {
        // given
        val comment: Comment = mockk()
        val actual = sut.comments

        // when
        sut.addComment(comment)

        // then
        actual shouldContain comment
    }

    @Test
    fun `getComment() returns comment when it exist`() {
        // given
        val commentId = CommentId("id")
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        sut.addComment(comment)

        // when
        val actual = sut.getComment(commentId)

        // then
        actual shouldBeEqualTo comment
    }

    @Test
    fun `getComment() returns null when it doesn't exist`() {
        // given
        val commentId = CommentId("id")

        // when
        val actual = sut.getComment(commentId)

        // then
        actual shouldBeEqualTo null
    }

    @Test
    fun `deleteComment() deletes comment from comments`() {
        // given
        val comment: Comment = mockk()
        sut.addComment(comment)
        val actual = sut.comments

        // when
        sut.deleteComment(comment)

        // then
        actual shouldNotContain comment
    }

    @Test
    fun `updateComment() adds updated comment to comments`() {
        // given
        val commentId = CommentId("id")
        val oldComment: Comment = mockk()
        every { oldComment.id } returns commentId
        every { oldComment.text } returns "old text"
        sut.addComment(oldComment)

        val newComment: Comment = mockk()
        every { newComment.id } returns commentId
        every { newComment.text } returns "new text"

        val actual = sut.comments

        // when
        sut.updateComment(newComment)

        // then
        actual shouldContain newComment
    }

    @Test
    fun `updateComment() replace old comment with new comment`() {
        // given
        val commentId = CommentId("id")
        val oldComment: Comment = mockk()
        every { oldComment.id } returns commentId
        every { oldComment.text } returns "old text"
        sut.addComment(oldComment)

        val newComment: Comment = mockk()
        every { newComment.id } returns commentId
        every { newComment.text } returns "new text"

        val actual = sut.comments

        // when
        sut.updateComment(newComment)

        // then
        actual shouldNotContain oldComment
    }
}
