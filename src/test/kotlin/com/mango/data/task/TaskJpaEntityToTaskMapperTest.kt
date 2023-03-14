package com.mango.data.task

import com.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.mango.domain.common.model.BusinessTestModel.getFutureDate1
import com.mango.domain.common.model.BusinessTestModel.getFutureDate2
import com.mango.domain.common.model.BusinessTestModel.getUUID1
import com.mango.domain.common.model.BusinessTestModel.getUUID2
import com.mango.domain.common.model.BusinessTestModel.getUUID3
import com.mango.domain.common.model.BusinessTestModel.getUUID4
import com.mango.domain.common.model.BusinessTestModel.getUUID5
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.Priority.PRIORITY_1
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TaskJpaEntityToTaskMapperTest {
    private val sut = TaskJpaEntityToTaskMapper()

    @Test
    fun `map taskJpaEntity to task`() {
        // given
        val taskUUID = getUUID1()
        val name = "name"
        val ownerUUID = getUUID2()
        val creationDate = getCurrentDate()
        val projectUUID = getUUID3()
        val description = "description"
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
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
            PRIORITY_1,
            TaskId(parentTaskUUID),
            UserId(assigneeUUID),
            completeDate,
        )
    }
}
