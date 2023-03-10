package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.CommentActivity
import com.mango.domain.activity.CommentActivityType
import com.mango.domain.comment.model.Comment
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.user.UserRepository
import com.mango.domain.user.model.User
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteCommentActivityUseCaseTest {
    private val activityRepository: ActivityRepository = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = DeleteCommentActivityUseCase(
        activityRepository,
        userRepository,
    )

    @Test
    fun `returns task activity`() {
        // given
        val date: LocalDateTime = mockk()
        val commentId = getCommentId1()
        val taskId = getTaskId1()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { comment.taskId } returns taskId
        val user: User = mockk()
        val userId = getUserId1()
        every { user.id } returns userId
        every { userRepository.getCurrentUser() } returns user
        justRun { activityRepository.addCommentActivity(any()) }
        val expected = CommentActivity(userId, CommentActivityType.DELETE_COMMENT, commentId, taskId, date, null, null)

        // when
        val actual = sut(comment, date)

        // then
        actual shouldBeEqualTo expected
    }

    @Test
    fun `adds activity to repository`() {
        // given
        val date: LocalDateTime = mockk()
        val commentId = getCommentId1()
        val taskId = getTaskId1()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { comment.taskId } returns taskId
        val user: User = mockk()
        val userId = getUserId1()
        every { user.id } returns userId
        every { userRepository.getCurrentUser() } returns user
        justRun { activityRepository.addCommentActivity(any()) }

        // when
        val actual = sut(comment, date)

        // then
        verify { activityRepository.addCommentActivity(actual) }
    }
}
