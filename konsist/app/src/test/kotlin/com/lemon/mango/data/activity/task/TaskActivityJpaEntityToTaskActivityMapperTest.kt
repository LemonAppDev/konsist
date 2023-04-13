package com.lemon.mango.data.activity.task

import com.lemon.mango.domain.activity.model.TaskActivity
import com.lemon.mango.domain.activity.model.TaskActivityId
import com.lemon.mango.domain.activity.model.TaskActivityType.Companion.getByValue
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID2
import com.lemon.mango.domain.common.model.BusinessTestModel.getUUID3
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TaskActivityJpaEntityToTaskActivityMapperTest {
    private val sut = TaskActivityJpaEntityToTaskActivityMapper()

    @Test
    fun `map taskActivityJpaEntity to taskActivity`() {
        // given
        val id = getUUID1()
        val userId = getUUID2()
        val type = "create"
        val taskId = getUUID3()
        val date: LocalDateTime = mockk()
        val newValue = "newValue"
        val oldValue = "oldValue"

        val taskActivityJpaEntity = TaskActivityJpaEntity(
            id,
            userId,
            type,
            taskId,
            date,
            newValue,
            oldValue,
        )

        // when
        val actual = sut(taskActivityJpaEntity)

        // then
        actual shouldBeEqualTo TaskActivity(
            TaskActivityId(id),
            UserId(userId),
            getByValue(type),
            TaskId(taskId),
            date,
            newValue,
            oldValue,
        )
    }
}
