package com.lemon.mango.domain.comment

import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UuidFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import com.lemon.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CommentFactoryTest {
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val userRepository: UserRepository = mockk()
    private val uuidFactory: UuidFactory = mockk()

    private val sut = CommentFactory(
        localDateTimeFactory,
        userRepository,
        uuidFactory,
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
