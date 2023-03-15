package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.comment.model.Comment
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getCommentActivityId1
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AddCommentActivityUseCaseTest {
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val uuidFactory: UUIDFactory = mockk()

    private val sut = AddCommentActivityUseCase(
        localDateTimeFactory,
        activityRepository,
        userRepository,
        uuidFactory,
    )

    @Test
    fun `adds comment activity when date is provided`() {
        // given
        val comment: Comment = mockk()
        val commentId = getCommentId1()
        every { comment.id } returns commentId
        val taskId = getTaskId1()
        every { comment.taskId } returns taskId
        val type: CommentActivityType = mockk()
        val date: LocalDateTime = mockk()
        val commentActivityId = getCommentActivityId1()
        every { uuidFactory.createCommentActivityId() } returns commentActivityId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val oldValue = "oldValue"
        val newValue = "newValue"
        val commentActivity = CommentActivity(commentActivityId, userId, type, commentId, taskId, date, newValue, oldValue)
        every { activityRepository.addCommentActivity(commentActivity) } returns mockk()

        // when
        sut(comment, type, date, newValue, oldValue)

        // then
        verify { activityRepository.addCommentActivity(commentActivity) }
    }

    @Test
    fun `adds comment activity when date is not provided`() {
        // given
        val comment: Comment = mockk()
        val commentId = getCommentId1()
        every { comment.id } returns commentId
        val taskId = getTaskId1()
        every { comment.taskId } returns taskId
        val type: CommentActivityType = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val commentActivityId = getCommentActivityId1()
        every { uuidFactory.createCommentActivityId() } returns commentActivityId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val oldValue = "oldValue"
        val newValue = "newValue"
        val commentActivity = CommentActivity(commentActivityId, userId, type, commentId, taskId, date, newValue, oldValue)
        every { activityRepository.addCommentActivity(commentActivity) } returns mockk()

        // when
        sut(comment, type, newValue = newValue, oldValue = oldValue)

        // then
        verify { activityRepository.addCommentActivity(commentActivity) }
    }
}
