package com.lemon.mango.data.comment

import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.comment.model.CommentId
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID2
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID3
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
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
        actual shouldBeEqualTo Comment(
            CommentId(commentId),
            text,
            TaskId(taskId),
            UserId(ownerId),
            creationDate,
        )
    }
}
