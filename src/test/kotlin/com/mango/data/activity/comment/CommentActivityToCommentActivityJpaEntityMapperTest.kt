package com.mango.data.activity.comment

import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.CommentActivityType.ADD_COMMENT
import com.mango.domain.common.model.BusinessTestModel.getCommentActivityId1
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CommentActivityToCommentActivityJpaEntityMapperTest {
    private val sut = CommentActivityToCommentActivityJpaEntityMapper()

    @Test
    fun `map commentActivity to commentActivityJpaEntity`() {
        // given
        val id = getCommentActivityId1()
        val ownerId = getUserId1()
        val type = ADD_COMMENT
        val commentId = getCommentId1()
        val taskId = getTaskId1()
        val date: LocalDateTime = mockk()
        val newValue = "newValue"
        val oldValue = "oldValue"

        val commentActivity = CommentActivity(
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
        val actual = sut(commentActivity)

        // then
        with(actual) {
            this.id shouldBeEqualTo id.value
            this.ownerId shouldBeEqualTo ownerId.value
            this.type shouldBeEqualTo type.value
            this.commentId shouldBeEqualTo commentId.value
            this.taskId shouldBeEqualTo taskId.value
            this.date shouldBeEqualTo date
            this.newValue shouldBeEqualTo newValue
            this.oldValue shouldBeEqualTo oldValue
        }
    }
}
