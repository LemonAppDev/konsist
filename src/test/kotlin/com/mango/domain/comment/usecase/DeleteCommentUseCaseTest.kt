package com.mango.domain.comment.usecase

import com.mango.data.comment.CommentRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.CommentActivityFactory
import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.comment.model.Comment
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteCommentUseCaseTest {
    private val commentRepository: CommentRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val commentActivityFactory: CommentActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = DeleteCommentUseCase(
        commentRepository,
        localDateTimeFactory,
        commentActivityFactory,
        activityRepository,
    )

    @Test
    fun `delete comment from repository`() {
        // given
        val commentId = getCommentId1()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { commentRepository.getComment(commentId) } returns comment
        justRun { commentRepository.deleteComment(comment) }
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val activity: CommentActivity = mockk()
        every { commentActivityFactory(comment, date, CommentActivityType.DELETE_COMMENT) } returns activity
        every { activityRepository.addCommentActivity(activity) } returns mockk()

        // when
        sut(commentId)

        // then
        verify { commentRepository.deleteComment(comment) }
    }

    @Test
    fun `adds activity to repository`() {
        // given
        val commentId = getCommentId1()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { commentRepository.getComment(commentId) } returns comment
        justRun { commentRepository.deleteComment(comment) }
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val activity: CommentActivity = mockk()
        every { commentActivityFactory(comment, date, CommentActivityType.DELETE_COMMENT) } returns activity
        every { activityRepository.addCommentActivity(activity) } returns mockk()

        // when
        sut(commentId)

        // then
        verify { activityRepository.addCommentActivity(activity) }
    }
}
