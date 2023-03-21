package com.lemon.mango.data.activity.comment

import com.lemon.mango.domain.activity.model.CommentActivity
import com.lemon.mango.domain.activity.model.CommentActivityId
import com.lemon.mango.domain.activity.model.CommentActivityType.Companion.getByValue
import com.lemon.mango.domain.comment.model.CommentId
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID2
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID3
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID4
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
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
        actual shouldBeEqualTo CommentActivity(
            CommentActivityId(id),
            UserId(ownerId),
            getByValue(type),
            CommentId(commentId),
            TaskId(taskId),
            date,
            newValue,
            oldValue,
        )
    }
}
