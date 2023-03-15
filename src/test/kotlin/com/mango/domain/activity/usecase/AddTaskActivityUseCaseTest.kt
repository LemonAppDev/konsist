package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getTaskActivityId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AddTaskActivityUseCaseTest {
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val uuidFactory: UUIDFactory = mockk()

    private val sut = AddTaskActivityUseCase(
        localDateTimeFactory,
        activityRepository,
        userRepository,
        uuidFactory,
    )

    @Test
    fun `adds task activity when date is null`() {
        // given
        val taskId = getTaskId1()
        val type: TaskActivityType = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val taskActivityId = getTaskActivityId1()
        every { uuidFactory.createTaskActivityId() } returns taskActivityId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val newValue = "newValue"
        val oldValue = "oldValue"
        val taskActivity = TaskActivity(taskActivityId, userId, type, taskId, date, newValue, oldValue)
        every { activityRepository.addTaskActivity(taskActivity) } returns mockk()

        // when
        sut(taskId, type, newValue = newValue, oldValue = oldValue)

        // then
        verify { activityRepository.addTaskActivity(taskActivity) }
    }

    @Test
    fun `adds task activity when date is not null`() {
        // given
        val taskId = getTaskId1()
        val type: TaskActivityType = mockk()
        val date: LocalDateTime = mockk()
        val taskActivityId = getTaskActivityId1()
        every { uuidFactory.createTaskActivityId() } returns taskActivityId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val newValue = "newValue"
        val oldValue = "oldValue"
        val taskActivity = TaskActivity(taskActivityId, userId, type, taskId, date, newValue, oldValue)
        every { activityRepository.addTaskActivity(taskActivity) } returns mockk()

        // when
        sut(taskId, type, date, newValue, oldValue)

        // then
        verify { activityRepository.addTaskActivity(taskActivity) }
    }
}
