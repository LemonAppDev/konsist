package com.mango.domain.comment.usecase

import com.mango.data.comment.CommentRepositoryImpl
import com.mango.domain.activity.usecase.UpdateCommentActivityUseCase
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateCommentUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase = mockk()
    private val updateCommentActivityUseCase: UpdateCommentActivityUseCase = mockk()

    private val sut = UpdateCommentUseCase(
        commentRepository,
        localDateTimeFactory,
        getCommentOrThrowUseCase,
        updateCommentActivityUseCase,
    )

    @Test
    fun `add comment to repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val commentId = getCommentId1()
        val updateCommentRequestModel = UpdateCommentRequestModel(commentId, newText)
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.id } returns commentId
        every { getCommentOrThrowUseCase(commentId) } returns oldComment
        val newComment: Comment = mockk()
        every { newComment.text } returns newText
        every { oldComment.copy(text = newText) } returns newComment
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { commentRepository.saveComment(newComment) } returns mockk()
        every { updateCommentActivityUseCase(newComment, date, newText, oldText) } returns mockk()

        // when
        sut(updateCommentRequestModel)

        // then
        verify { commentRepository.saveComment(newComment) }
    }

    @Test
    fun `calls updateCommentActivityUseCase`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val commentId = getCommentId1()
        val updateCommentRequestModel = UpdateCommentRequestModel(commentId, newText)
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.id } returns commentId
        every { getCommentOrThrowUseCase(commentId) } returns oldComment
        val newComment: Comment = mockk()
        every { newComment.text } returns newText
        every { oldComment.copy(text = newText) } returns newComment
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { commentRepository.saveComment(newComment) } returns mockk()
        every { updateCommentActivityUseCase(newComment, date, newText, oldText) } returns mockk()

        // when
        sut(updateCommentRequestModel)

        // then
        verify { updateCommentActivityUseCase(newComment, date, newText, oldText) }
    }
}
