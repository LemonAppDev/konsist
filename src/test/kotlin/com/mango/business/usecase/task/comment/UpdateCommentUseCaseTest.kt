package com.mango.business.usecase.task.comment

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.Comment
import com.mango.business.model.activity.task.UpdateCommentActivity
import com.mango.business.model.activity.task.UpdateCommentActivityFactory
import com.mango.business.model.request.UpdateCommentRequestModel
import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateCommentUseCaseTest {
    private val commentRepository: CommentRepository = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val updateCommentActivityFactory: UpdateCommentActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = UpdateCommentUseCase(
        commentRepository,
        localDateTimeFactory,
        updateCommentActivityFactory,
        activityRepository,
    )

    @Test
    fun `throws exception when comment doesn't exist`() {
        // given
        val updateCommentRequestModel = UpdateCommentRequestModel(CommentId("id"), "text")
        val commentId = updateCommentRequestModel.commentId
        every { commentRepository.getComment(CommentId("id")) } returns null

        // when
        val actual = { sut(updateCommentRequestModel) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Comment doesn't exist: commentId: $commentId"
    }

    @Test
    fun `add comment to repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val updateCommentRequestModel = UpdateCommentRequestModel(CommentId("id"), newText)
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.taskId } returns TaskId("id")
        every { commentRepository.getComment(CommentId("id")) } returns oldComment
        val newComment: Comment = mockk()
        every { newComment.text } returns newText
        every { oldComment.copy(text = newText) } returns newComment
        every { localDateTimeFactory() } returns mockk()
        justRun { commentRepository.updateComment(newComment) }
        every { updateCommentActivityFactory(any(), any(), any(), any()) } returns mockk()
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(updateCommentRequestModel)

        // then
        verify { commentRepository.updateComment(newComment) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val taskId = TaskId("id")
        val updateCommentRequestModel = UpdateCommentRequestModel(CommentId("id"), newText)
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.taskId } returns taskId
        every { commentRepository.getComment(CommentId("id")) } returns oldComment
        val newComment: Comment = mockk()
        every { newComment.text } returns newText
        every { oldComment.copy(text = newText) } returns newComment
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        justRun { commentRepository.updateComment(newComment) }
        val activity: UpdateCommentActivity = mockk()
        every { updateCommentActivityFactory(taskId, date, oldText, newText) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(updateCommentRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
