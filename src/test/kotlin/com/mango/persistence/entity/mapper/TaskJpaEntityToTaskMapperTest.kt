package com.mango.persistence.entity.mapper

import com.mango.business.common.model.BusinessTestModel.getUUID1
import com.mango.business.common.model.BusinessTestModel.getUUID2
import com.mango.business.common.model.BusinessTestModel.getUUID3
import com.mango.business.common.model.BusinessTestModel.getUUID4
import com.mango.business.common.model.BusinessTestModel.getUUID5
import com.mango.business.model.Priority
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.entity.TaskJpaEntity
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

class TaskJpaEntityToTaskMapperTest {
    private val sut = TaskJpaEntityToTaskMapper()

    @Test
    fun `map taskJpaEntity to task`() {
        // given
        val taskUUID = getUUID1()
        val name = "name"
        val ownerUUID = getUUID2()
        val creationDate = LocalDateTime.of(2023, Month.MARCH, 1, 10, 0, 0)
        val projectUUID = getUUID3()
        val description = "description"
        val dueDate = LocalDateTime.of(2023, Month.MARCH, 2, 10, 0, 0)
        val targetDate = LocalDateTime.of(2023, Month.MARCH, 3, 10, 0, 0)
        val priority = 1
        val parentTaskUUID = getUUID4()
        val assigneeUUID = getUUID5()
        val completeDate: LocalDateTime = mockk()

        val taskJpaEntity = TaskJpaEntity(
            taskUUID,
            name,
            ownerUUID,
            creationDate,
            projectUUID,
            description,
            dueDate,
            targetDate,
            priority,
            parentTaskUUID,
            assigneeUUID,
            completeDate,
        )

        // when
        val actual = sut(taskJpaEntity)

        // then
        actual.apply {
            this.id shouldBeEqualTo TaskId(taskUUID)
            this.name shouldBeEqualTo name
            this.ownerId shouldBeEqualTo UserId(ownerUUID)
            this.creationDate shouldBeEqualTo creationDate
            this.projectId shouldBeEqualTo ProjectId(projectUUID)
            this.description shouldBeEqualTo description
            this.dueDate shouldBeEqualTo dueDate
            this.targetDate shouldBeEqualTo targetDate
            this.priority shouldBeEqualTo Priority.PRIORITY_1
            this.parentTaskId shouldBeEqualTo TaskId(parentTaskUUID)
            this.assigneeId shouldBeEqualTo UserId(assigneeUUID)
            this.completeDate shouldBeEqualTo completeDate
        }
    }
}
