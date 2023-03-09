package com.mango.persistence.entity.mapper

import com.mango.business.common.model.BusinessTestModel.getCommentId1
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.common.model.BusinessTestModel.getUserId1
import com.mango.business.model.Comment
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CommentToCommentJpaEntityMapperTest {
    private val sut = CommentToCommentJpaEntityMapper()

    @Test
    fun `map comment to commentJpaEntity`() {
        // given
        val commentId = getCommentId1()
        val text = "text"
        val taskId = getTaskId1()
        val creatorId = getUserId1()
        val creationDate: LocalDateTime = mockk()

        val comment = Comment(
            commentId,
            text,
            taskId,
            creatorId,
            creationDate,
        )

        // when
        val actual = sut(comment)

        // then
        actual.apply {
            this.id shouldBeEqualTo commentId.value
            this.creationDate shouldBeEqualTo creationDate
            this.ownerId shouldBeEqualTo creatorId.value
            this.text shouldBeEqualTo text
            this.taskId shouldBeEqualTo taskId.value
        }
    }
}
