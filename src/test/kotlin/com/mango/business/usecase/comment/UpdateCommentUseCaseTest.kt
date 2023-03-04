package com.mango.business.usecase.comment

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.Comment
import com.mango.business.model.activity.task.UpdateCommentActivity
import com.mango.business.model.activity.task.UpdateCommentActivityFactory
import com.mango.business.model.request.comment.UpdateCommentRequestModel
import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
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
    private val getCommentUseCase: GetCommentUseCase = mockk()

    private val sut = UpdateCommentUseCase(
        commentRepository,
        localDateTimeFactory,
        updateCommentActivityFactory,
        activityRepository,
        getCommentUseCase,
    )

    @Test
    fun `add comment to repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val taskId = TaskId("id")
        val commentId = CommentId("id")
        val updateCommentRequestModel = UpdateCommentRequestModel(commentId, newText)
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.taskId } returns taskId
        every { getCommentUseCase(commentId) } returns oldComment
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
        verify { commentRepository.updateComment(newComment) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val newText = "new text"
        val oldText = "old text"
        val taskId = TaskId("id")
        val commentId = CommentId("id")
        val updateCommentRequestModel = UpdateCommentRequestModel(commentId, newText)
        val oldComment: Comment = mockk()
        every { oldComment.text } returns oldText
        every { oldComment.taskId } returns taskId
        every { getCommentUseCase(commentId) } returns oldComment
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
