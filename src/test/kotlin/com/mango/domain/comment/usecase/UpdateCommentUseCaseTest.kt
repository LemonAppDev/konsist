package com.mango.domain.comment.usecase

import com.mango.data.comment.CommentRepositoryImpl
import com.mango.domain.activity.model.CommentActivityType.UPDATE_COMMENT
import com.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.mango.domain.comment.model.Comment
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class UpdateCommentUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase = mockk()
    private val addCommentActivityUseCase: AddCommentActivityUseCase = mockk()

    private val sut = UpdateCommentUseCase(
        commentRepository,
        getCommentOrThrowUseCase,
        addCommentActivityUseCase,
    )

    @Test
    fun `add comment to repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val commentId = getCommentId1()
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.id } returns commentId
        every { getCommentOrThrowUseCase(commentId) } returns oldComment
        val newComment: Comment = mockk()
        every { newComment.text } returns newText
        every { oldComment.copy(text = newText) } returns newComment
        every { commentRepository.saveComment(newComment) } returns mockk()
        justRun { addCommentActivityUseCase(newComment, UPDATE_COMMENT, newValue = newText, oldValue = oldText) }

        // when
        sut(commentId, newText)

        // then
        verify { commentRepository.saveComment(newComment) }
    }

    @Test
    fun `adds comment activity to repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val commentId = getCommentId1()
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.id } returns commentId
        every { getCommentOrThrowUseCase(commentId) } returns oldComment
        val newComment: Comment = mockk()
        every { newComment.text } returns newText
        every { oldComment.copy(text = newText) } returns newComment
        every { commentRepository.saveComment(newComment) } returns mockk()
        justRun { addCommentActivityUseCase(newComment, UPDATE_COMMENT, newValue = newText, oldValue = oldText) }

        // when
        sut(commentId, newText)

        // then
        verify { addCommentActivityUseCase(newComment, UPDATE_COMMENT, newValue = newText, oldValue = oldText) }
    }
}
