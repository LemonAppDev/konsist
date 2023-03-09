package com.mango.business.usecase.comment

import com.mango.business.common.model.BusinessTestModel.getCommentId1
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.Comment
import com.mango.business.model.activity.task.UpdateCommentActivity
import com.mango.business.model.activity.task.UpdateCommentActivityFactory
import com.mango.business.model.request.comment.UpdateCommentRequestModel
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateCommentUseCaseTest {
    private val commentRepository: CommentRepository = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val updateCommentActivityFactory: UpdateCommentActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase = mockk()

    private val sut = UpdateCommentUseCase(
        commentRepository,
        localDateTimeFactory,
        updateCommentActivityFactory,
        activityRepository,
        getCommentOrThrowUseCase,
    )

    @Test
    fun `add comment to repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val taskId = getTaskId1()
        val commentId = getCommentId1()
        val updateCommentRequestModel = UpdateCommentRequestModel(commentId, newText)
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.taskId } returns taskId
        every { getCommentOrThrowUseCase(commentId) } returns oldComment
        val newComment: Comment = mockk()
        every { newComment.text } returns newText
        every { oldComment.copy(text = newText) } returns newComment
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { commentRepository.saveComment(newComment) } returns mockk()
        val activity: UpdateCommentActivity = mockk()
        every { updateCommentActivityFactory(taskId, date, oldText, newText) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(updateCommentRequestModel)

        // then
        verify { commentRepository.saveComment(newComment) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val taskId = getTaskId1()
        val commentId = getCommentId1()
        val updateCommentRequestModel = UpdateCommentRequestModel(commentId, newText)
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.taskId } returns taskId
        every { getCommentOrThrowUseCase(commentId) } returns oldComment
        val newComment: Comment = mockk()
        every { newComment.text } returns newText
        every { oldComment.copy(text = newText) } returns newComment
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { commentRepository.saveComment(newComment) } returns mockk()
        val activity: UpdateCommentActivity = mockk()
        every { updateCommentActivityFactory(taskId, date, oldText, newText) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(updateCommentRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
