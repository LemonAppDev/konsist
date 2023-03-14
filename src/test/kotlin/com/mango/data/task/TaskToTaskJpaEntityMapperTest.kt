package com.mango.data.task

import com.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.mango.domain.common.model.BusinessTestModel.getFutureDate1
import com.mango.domain.common.model.BusinessTestModel.getFutureDate2
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.common.model.BusinessTestModel.getUserId2
import com.mango.domain.task.model.Priority.PRIORITY_1
import com.mango.domain.task.model.Task
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TaskToTaskJpaEntityMapperTest {
    private val sut = TaskToTaskJpaEntityMapper()

    @Test
    fun `map taskJpaEntity to task`() {
        // given
        val taskId = getTaskId1()
        val name = "name"
        val ownerId = getUserId1()
        val creationDate = getCurrentDate()
        val projectId = getProjectId1()
        val description = "description"
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
        val priority = PRIORITY_1
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
        with(actual) {
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
