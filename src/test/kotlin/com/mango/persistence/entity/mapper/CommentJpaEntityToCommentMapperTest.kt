package com.mango.persistence.entity.mapper

import com.mango.business.common.model.BusinessTestModel.getUUID1
import com.mango.business.common.model.BusinessTestModel.getUUID2
import com.mango.business.common.model.BusinessTestModel.getUUID3
import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.entity.CommentJpaEntity
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CommentJpaEntityToCommentMapperTest {
    private val sut = CommentJpaEntityToCommentMapper()

    @Test
    fun `map commentJpaEntity to comment`() {
        // given
        val commentId = getUUID1()
        val creationDate: LocalDateTime = mockk()
        val ownerId = getUUID2()
        val text = "text"
        val taskId = getUUID3()

        val commentJpaEntity = CommentJpaEntity(
            commentId,
            creationDate,
            ownerId,
            text,
            taskId,
        )

        // when
        val actual = sut(commentJpaEntity)

        // then
        actual.apply {
            this.id shouldBeEqualTo CommentId(commentId)
            this.text shouldBeEqualTo text
            this.taskId shouldBeEqualTo TaskId(taskId)
            this.creatorId shouldBeEqualTo UserId(ownerId)
            this.creationDate shouldBeEqualTo creationDate
        }
    }
}
