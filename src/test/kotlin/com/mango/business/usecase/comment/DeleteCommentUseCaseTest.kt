package com.mango.business.usecase.comment

import com.mango.business.common.model.BusinessTestModel.getCommentId1
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.Comment
import com.mango.business.model.activity.task.DeleteCommentActivity
import com.mango.business.model.activity.task.DeleteCommentActivityFactory
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteCommentUseCaseTest {
    private val commentRepository: CommentRepository = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val deleteCommentActivityFactory: DeleteCommentActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = DeleteCommentUseCase(
        commentRepository,
        localDateTimeFactory,
        deleteCommentActivityFactory,
        activityRepository,
    )

    @Test
    fun `delete comment from repository`() {
        // given
        val commentId = getCommentId1()
        val taskId = getTaskId1()
        val comment: Comment = mockk()
        every { comment.taskId } returns taskId
        every { comment.id } returns commentId
        every { commentRepository.getComment(commentId) } returns comment
        justRun { commentRepository.deleteComment(comment) }
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val activity: DeleteCommentActivity = mockk()
        every { deleteCommentActivityFactory(taskId, date, commentId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(commentId)

        // then
        verify { commentRepository.deleteComment(comment) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val commentId = getCommentId1()
        val taskId = getTaskId1()
        val comment: Comment = mockk()
        every { comment.taskId } returns taskId
        every { comment.id } returns commentId
        every { commentRepository.getComment(commentId) } returns comment
        justRun { commentRepository.deleteComment(comment) }
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val activity: DeleteCommentActivity = mockk()
        every { deleteCommentActivityFactory(taskId, date, commentId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(commentId)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
