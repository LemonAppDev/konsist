package com.mango.persistence.entity.mapper

import com.mango.business.common.model.BusinessTestModel.getProjectId1
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.common.model.BusinessTestModel.getTaskId2
import com.mango.business.common.model.BusinessTestModel.getUserId1
import com.mango.business.common.model.BusinessTestModel.getUserId2
import com.mango.business.model.Priority
import com.mango.business.model.Task
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

class TaskToTaskJpaEntityMapperTest {
    private val sut = TaskToTaskJpaEntityMapper()

    @Test
    fun `map taskJpaEntity to task`() {
        // given
        val taskId = getTaskId1()
        val name = "name"
        val ownerId = getUserId1()
        val creationDate = LocalDateTime.of(2023, Month.MARCH, 1, 10, 0, 0)
        val projectId = getProjectId1()
        val description = "description"
        val dueDate = LocalDateTime.of(2023, Month.MARCH, 2, 10, 0, 0)
        val targetDate = LocalDateTime.of(2023, Month.MARCH, 3, 10, 0, 0)
        val priority = Priority.PRIORITY_1
        val parentTaskId = getTaskId2()
        val assigneeId = getUserId2()
        val completeDate: LocalDateTime = mockk()

        val task = Task(
            taskId,
            name,
            ownerId,
            creationDate,
            projectId,
            description,
            dueDate,
            targetDate,
            priority,
            parentTaskId,
            assigneeId,
            completeDate,
        )

        // when
        val actual = sut(task)

        // then
        actual.apply {
            this.id shouldBeEqualTo taskId.value
            this.name shouldBeEqualTo name
            this.ownerId shouldBeEqualTo ownerId.value
            this.creationDate shouldBeEqualTo creationDate
            this.projectId shouldBeEqualTo projectId.value
            this.description shouldBeEqualTo description
            this.dueDate shouldBeEqualTo dueDate
            this.targetDate shouldBeEqualTo targetDate
            this.priority shouldBeEqualTo 1
            this.parentTaskId shouldBeEqualTo parentTaskId.value
            this.assigneeId shouldBeEqualTo assigneeId.value
            this.completeDate shouldBeEqualTo completeDate
        }
    }
}
