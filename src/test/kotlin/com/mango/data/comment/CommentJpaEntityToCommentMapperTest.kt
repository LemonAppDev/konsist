package com.mango.data.comment

import com.mango.domain.comment.model.CommentId
import com.mango.domain.common.model.BusinessTestModel.getUUID1
import com.mango.domain.common.model.BusinessTestModel.getUUID2
import com.mango.domain.common.model.BusinessTestModel.getUUID3
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
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
