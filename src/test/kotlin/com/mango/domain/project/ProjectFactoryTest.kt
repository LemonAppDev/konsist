package com.mango.domain.project

import com.mango.application.model.project.CreateProjectRequestModel
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.common.model.Color
import com.mango.domain.project.model.Project
import com.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ProjectFactoryTest {
    private val uuidFactory: UUIDFactory = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = ProjectFactory(
        uuidFactory,
        localDateTimeFactory,
        userRepository,
    )

    @Test
    fun `returns project with default color and isFavourite`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel("name", null, null)
        val projectId = getProjectId1()
        every { uuidFactory.createProjectId() } returns projectId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val date = getCurrentDate()
        every { localDateTimeFactory() } returns date

        // when
        val actual = sut(
            createProjectRequestModel.name,
            createProjectRequestModel.color,
            createProjectRequestModel.isFavourite,
        )

        // then
        actual shouldBeEqualTo Project(getProjectId1(), getUserId1(), getCurrentDate(), "name", Color("#666666"), false)
    }

    @Test
    fun `returns project with given date`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel("name", Color("#FFFFFF"), true)
        val projectId = getProjectId1()
        every { uuidFactory.createProjectId() } returns projectId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val date = getCurrentDate()
        every { localDateTimeFactory() } returns date

        // when
        val actual = sut(
            createProjectRequestModel.name,
            createProjectRequestModel.color,
            createProjectRequestModel.isFavourite,
        )

        // then
        actual shouldBeEqualTo Project(getProjectId1(), getUserId1(), getCurrentDate(), "name", Color("#FFFFFF"), true)
    }
}
