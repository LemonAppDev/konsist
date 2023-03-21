package com.mango.domain.project

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
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val userRepository: UserRepository = mockk()
    private val uuidFactory: UUIDFactory = mockk()

    private val sut = ProjectFactory(
        localDateTimeFactory,
        userRepository,
        uuidFactory,
    )

    @Test
    fun `returns project with default color and isFavourite`() {
        // given
        val name = "name"
        val color = null
        val isFavourite = null
        val projectId = getProjectId1()
        every { uuidFactory.createProjectId() } returns projectId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val date = getCurrentDate()
        every { localDateTimeFactory() } returns date

        // when
        val actual = sut(name, color, isFavourite)

        // then
        actual shouldBeEqualTo Project(getProjectId1(), getUserId1(), getCurrentDate(), "name", Color("#666666"), false)
    }

    @Test
    fun `returns project with given date`() {
        // given
        val name = "name"
        val color = Color("#FFFFFF")
        val isFavourite = true
        val projectId = getProjectId1()
        every { uuidFactory.createProjectId() } returns projectId
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        val date = getCurrentDate()
        every { localDateTimeFactory() } returns date

        // when
        val actual = sut(name, color, isFavourite)

        // then
        actual shouldBeEqualTo Project(getProjectId1(), getUserId1(), getCurrentDate(), "name", Color("#FFFFFF"), true)
    }
}
