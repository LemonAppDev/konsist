package com.lemon.mango.domain.project.usecase.update

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateProjectUseCaseTest {
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val updateProjectColorUseCase: UpdateProjectColorUseCase = mockk()
    private val updateProjectIsFavouriteUseCase: UpdateProjectIsFavouriteUseCase = mockk()
    private val updateProjectNameUseCase: UpdateProjectNameUseCase = mockk()

    private val sut = UpdateProjectUseCase(
        getProjectOrThrowUseCase,
        localDateTimeFactory,
        updateProjectColorUseCase,
        updateProjectIsFavouriteUseCase,
        updateProjectNameUseCase,
    )

    @Test
    fun `calls all update project use cases`() {
        // given
        val project: Project = mockk()
        val projectId = getProjectId1()
        every { project.id } returns projectId
        val newName = "new name"
        val newColor = Color("new color")
        val newIsFavourite = true
        val date: LocalDateTime = mockk()

        every { getProjectOrThrowUseCase(projectId) } returns project
        every { localDateTimeFactory() } returns date
        justRun { updateProjectNameUseCase(projectId, newName, date) }
        justRun { updateProjectColorUseCase(projectId, newColor, date) }
        justRun { updateProjectIsFavouriteUseCase(projectId, newIsFavourite, date) }

        // when
        sut(projectId, newName, newColor, newIsFavourite)

        // then
        verifyAll {
            updateProjectNameUseCase(projectId, newName, date)
            updateProjectColorUseCase(projectId, newColor, date)
            updateProjectIsFavouriteUseCase(projectId, newIsFavourite, date)
        }
    }
}
