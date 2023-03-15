package com.mango.domain.comment

import com.mango.domain.comment.model.Comment
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CommentFactoryTest {
    private val uuidFactory: UUIDFactory = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = CommentFactory(
        uuidFactory,
        localDateTimeFactory,
        userRepository,
    )

    @Test
    fun `returns comment`() {
        // given
        val taskId = getTaskId1()
        val text = "text"
        val commentId = getCommentId1()
        every { uuidFactory.createCommentId() } returns commentId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date

        // when
        val actual = sut(taskId, text)

        // then
        actual shouldBeEqualTo Comment(commentId, text, taskId, userId, date)
    }
}
