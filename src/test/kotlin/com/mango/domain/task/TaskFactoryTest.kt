package com.mango.domain.task

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.mango.domain.common.model.BusinessTestModel.getFutureDate1
import com.mango.domain.common.model.BusinessTestModel.getFutureDate2
import com.mango.domain.common.model.BusinessTestModel.getFutureDate3
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.common.model.BusinessTestModel.getUserId2
import com.mango.domain.task.model.Priority.PRIORITY_2
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TaskFactoryTest {
    private val uuidFactory: UUIDFactory = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = TaskFactory(
        uuidFactory,
        localDateTimeFactory,
        userRepository,
    )

    @Test
    fun `returns task`() {
        // given
        val taskId = getTaskId1()
        every { uuidFactory.createTaskId() } returns taskId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val date = getCurrentDate()
        every { localDateTimeFactory() } returns date
        val name = "name"
        val description = "description"
        val dueDate = getFutureDate1()
        val targetDate = getFutureDate2()
        val priority = 2
        val projectId = getProjectId1()
        val parentTaskId = getTaskId2()
        val assigneeId = getUserId2()
        val completeDate = getFutureDate3()
        val createTaskRequestModel = CreateTaskRequestModel(
            name,
            description,
            dueDate,
            targetDate,
            priority,
            projectId,
            parentTaskId,
            assigneeId,
        )

        // when
        val actual = sut(createTaskRequestModel, completeDate = completeDate)

        // then
        actual shouldBeEqualTo Task(
            taskId,
            name,
            userId,
            date,
            projectId,
            description,
            dueDate,
            targetDate,
            PRIORITY_2,
            parentTaskId,
            assigneeId,
            completeDate,
        )
    }
}
