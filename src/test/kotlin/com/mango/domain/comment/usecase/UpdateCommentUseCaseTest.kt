package com.mango.domain.comment.usecase

import com.mango.data.comment.CommentRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.CommentActivity
import com.mango.domain.activity.CommentActivityFactory
import com.mango.domain.activity.CommentActivityType
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateCommentUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase = mockk()
    private val commentActivityFactory: CommentActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = UpdateCommentUseCase(
        commentRepository,
        localDateTimeFactory,
        getCommentOrThrowUseCase,
        commentActivityFactory,
        activityRepository,
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
        val activity: CommentActivity = mockk()
        every { commentActivityFactory(newComment, date, CommentActivityType.UPDATE_COMMENT, newText, oldText) } returns activity
        justRun { activityRepository.addCommentActivity(activity) }

        // when
        sut(updateCommentRequestModel)

        // then
        verify { commentRepository.saveComment(newComment) }
    }

    @Test
    fun `adds activity to repository`() {
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
        val activity: CommentActivity = mockk()
        every { commentActivityFactory(newComment, date, CommentActivityType.UPDATE_COMMENT, newText, oldText) } returns activity
        justRun { activityRepository.addCommentActivity(activity) }

        // when
        sut(updateCommentRequestModel)

        // then
        verify { activityRepository.addCommentActivity(activity) }
    }
}
