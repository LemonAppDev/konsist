package com.lemon.mango.data.activity.task

import com.lemon.mango.domain.activity.model.TaskActivity
import com.lemon.mango.domain.activity.model.TaskActivityType.CREATE
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskActivityId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TaskActivityToTaskActivityJpaEntityMapperTest {
    private val sut = TaskActivityToTaskActivityJpaEntityMapper()

    @Test
    fun `map taskActivity to taskActivityJpaEntity`() {
        // given
        val id = getTaskActivityId1()
        val userId = getUserId1()
        val type = CREATE
        val taskId = getTaskId1()
        val date: LocalDateTime = mockk()
        val newValue = "newValue"
        val oldValue = "oldValue"

        val taskActivity = TaskActivity(
            id,
            userId,
            type,
            taskId,
            date,
            newValue,
            oldValue,
        )

        // when
        val actual = sut(taskActivity)

        // then
        with(actual) {
            this.id shouldBeEqualTo id.value
            this.userId shouldBeEqualTo userId.value
            this.type shouldBeEqualTo type.value
            this.taskId shouldBeEqualTo taskId.value
            this.date shouldBeEqualTo date
            this.newValue shouldBeEqualTo newValue
            this.oldValue shouldBeEqualTo oldValue
        }
    }
}
