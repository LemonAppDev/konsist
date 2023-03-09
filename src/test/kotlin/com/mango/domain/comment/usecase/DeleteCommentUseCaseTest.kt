package com.mango.domain.comment.usecase

import com.mango.data.activity.ActivityRepositoryImpl
import com.mango.data.comment.CommentRepositoryImpl
import com.mango.domain.comment.model.Comment
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteCommentUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val deleteCommentActivityFactory: com.mango.domain.task.activity.DeleteCommentActivityFactory = mockk()
    private val activityRepository: ActivityRepositoryImpl = mockk()

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
        val activity: com.mango.domain.task.activity.DeleteCommentActivity = mockk()
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
        val activity: com.mango.domain.task.activity.DeleteCommentActivity = mockk()
        every { deleteCommentActivityFactory(taskId, date, commentId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(commentId)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
