package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectActivityId1
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AddProjectActivityUseCaseTest {
    private val activityRepository: ActivityRepository = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val userRepository: UserRepository = mockk()
    private val uuidFactory: UUIDFactory = mockk()

    private val sut = AddProjectActivityUseCase(
        activityRepository,
        localDateTimeFactory,
        userRepository,
        uuidFactory,
    )

    @Test
    fun `adds project activity when date, new value and old value are not null`() {
        // given
        val projectId = getProjectId1()
        val type: ProjectActivityType = mockk()
        val date: LocalDateTime = mockk()
        val newValue = "newValue"
        val oldValue = "oldValue"
        val projectActivityId = getProjectActivityId1()
        every { uuidFactory.createProjectActivityId() } returns projectActivityId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val projectActivity = ProjectActivity(projectActivityId, userId, type, projectId, date, newValue, oldValue)
        every { activityRepository.addProjectActivity(projectActivity) } returns mockk()

        // when
        sut(projectId, type, date, newValue, oldValue)

        // then
        verify { activityRepository.addProjectActivity(projectActivity) }
    }

    @Test
    fun `adds project activity when date is null`() {
        // given
        val projectId = getProjectId1()
        val type: ProjectActivityType = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val projectActivityId = getProjectActivityId1()
        every { uuidFactory.createProjectActivityId() } returns projectActivityId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val projectActivity = ProjectActivity(projectActivityId, userId, type, projectId, date, null, null)
        every { activityRepository.addProjectActivity(projectActivity) } returns mockk()

        // when
        sut(projectId, type)

        // then
        verify { activityRepository.addProjectActivity(projectActivity) }
    }
}
