package com.mango.data.task

import com.mango.domain.common.model.BusinessTestModel.getUUID1
import com.mango.domain.common.model.BusinessTestModel.getUUID2
import com.mango.domain.common.model.BusinessTestModel.getUUID3
import com.mango.domain.common.model.BusinessTestModel.getUUID4
import com.mango.domain.common.model.BusinessTestModel.getUUID5
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
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
        actual shouldBeEqualTo Task(
            TaskId(taskUUID),
            name,
            UserId(ownerUUID),
            creationDate,
            ProjectId(projectUUID),
            description,
            dueDate,
            targetDate,
            com.mango.domain.task.model.Priority.PRIORITY_1,
            TaskId(parentTaskUUID),
            UserId(assigneeUUID),
            completeDate,
        )
    }
}
