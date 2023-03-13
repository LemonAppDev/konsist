package com.mango.data.activity.comment

import com.mango.domain.activity.model.CommentActivityId
import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.comment.model.CommentId
import com.mango.domain.common.model.BusinessTestModel.getUUID1
import com.mango.domain.common.model.BusinessTestModel.getUUID2
import com.mango.domain.common.model.BusinessTestModel.getUUID3
import com.mango.domain.common.model.BusinessTestModel.getUUID4
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CommentActivityJpaEntityToCommentActivityMapperTest {
    private val sut = CommentActivityJpaEntityToCommentActivityMapper()

    @Test
    fun `map commentActivityJpaEntity to commentActivity`() {
        // given
        val id = getUUID1()
        val ownerId = getUUID2()
        val type = "add"
        val commentId = getUUID3()
        val taskId = getUUID4()
        val date: LocalDateTime = mockk()
        val newValue = "newValue"
        val oldValue = "oldValue"

        val commentActivityJpaEntity = CommentActivityJpaEntity(
            id,
            ownerId,
            type,
            commentId,
            taskId,
            date,
            newValue,
            oldValue,
        )

        // when
        val actual = sut(commentActivityJpaEntity)

        // then
        actual.apply {
            this.id shouldBeEqualTo CommentActivityId(id)
            this.ownerId shouldBeEqualTo UserId(ownerId)
            this.type shouldBeEqualTo CommentActivityType.getByValue(type)
            this.commentId shouldBeEqualTo CommentId(commentId)
            this.taskId shouldBeEqualTo TaskId(taskId)
            this.date shouldBeEqualTo date
            this.newValue shouldBeEqualTo newValue
            this.oldValue shouldBeEqualTo oldValue
        }
    }
}
