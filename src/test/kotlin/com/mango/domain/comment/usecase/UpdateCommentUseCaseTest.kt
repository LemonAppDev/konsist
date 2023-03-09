package com.mango.domain.comment.usecase

import com.mango.data.activity.ActivityRepositoryImpl
import com.mango.data.comment.CommentRepositoryImpl
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateCommentUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val updateCommentActivityFactory: com.mango.domain.task.activity.UpdateCommentActivityFactory = mockk()
    private val activityRepository: ActivityRepositoryImpl = mockk()
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
        val activity: com.mango.domain.task.activity.UpdateCommentActivity = mockk()
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
        val activity: com.mango.domain.task.activity.UpdateCommentActivity = mockk()
        every { updateCommentActivityFactory(taskId, date, oldText, newText) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(updateCommentRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
