package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.ProjectActivity
import com.mango.domain.activity.ProjectActivityType
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
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

class CreateProjectActivityUseCaseTest {
    private val activityRepository: ActivityRepository = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = CreateProjectActivityUseCase(
        activityRepository,
        userRepository,
    )

    @Test
    fun `returns task activity`() {
        // given
        val projectId = getProjectId1()
        val date: LocalDateTime = mockk()
        val user: User = mockk()
        val userId = getUserId1()
        every { user.id } returns userId
        every { userRepository.getCurrentUser() } returns user
        justRun { activityRepository.addProjectActivity(any()) }
        val expected = ProjectActivity(userId, ProjectActivityType.CREATE, projectId, date)

        // when
        val actual = sut(projectId, date)

        // then
        actual shouldBeEqualTo expected
    }

    @Test
    fun `adds activity to repository`() {
        // given
        val projectId = getProjectId1()
        val date: LocalDateTime = mockk()
        val user: User = mockk()
        val userId = getUserId1()
        every { user.id } returns userId
        every { userRepository.getCurrentUser() } returns user
        justRun { activityRepository.addProjectActivity(any()) }

        // when
        val actual = sut(projectId, date)

        // then
        verify { activityRepository.addProjectActivity(actual) }
    }
}
