package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivity
import com.mango.domain.activity.TaskActivityType
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.user.UserRepository
import com.mango.domain.user.model.User
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteTaskActivityUseCaseTest {
    private val activityRepository: ActivityRepository = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = DeleteTaskActivityUseCase(
        activityRepository,
        userRepository,
    )

    @Test
    fun `returns task activity`() {
        // given
        val taskId = getTaskId1()
        val date: LocalDateTime = mockk()
        val user: User = mockk()
        val userId = getUserId1()
        every { user.id } returns userId
        every { userRepository.getCurrentUser() } returns user
        justRun { activityRepository.addTaskActivity(any()) }
        val expected = TaskActivity(userId, TaskActivityType.DELETE, taskId, date, null, null)

        // when
        val actual = sut(taskId, date)

        // then
        actual shouldBeEqualTo expected
    }

    @Test
    fun `adds activity to repository`() {
        // given
        val taskId = getTaskId1()
        val date: LocalDateTime = mockk()
        val user: User = mockk()
        val userId = getUserId1()
        every { user.id } returns userId
        every { userRepository.getCurrentUser() } returns user
        justRun { activityRepository.addTaskActivity(any()) }

        // when
        val actual = sut(taskId, date)

        // then
        verify { activityRepository.addTaskActivity(actual) }
    }
}
