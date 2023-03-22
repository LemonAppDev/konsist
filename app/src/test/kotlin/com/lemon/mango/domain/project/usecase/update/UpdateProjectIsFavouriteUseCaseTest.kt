package com.lemon.mango.domain.project.usecase.update

import com.lemon.mango.data.project.ProjectRepositoryImpl
import com.lemon.mango.domain.activity.model.ProjectActivityType.UPDATE_IS_FAVOURITE
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.common.model.BusinessTestModel.getProject
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateProjectIsFavouriteUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val projectRepository: ProjectRepositoryImpl = mockk()

    private val sut = UpdateProjectIsFavouriteUseCase(
        addProjectActivityUseCase,
        getProjectOrThrowUseCase,
        projectRepository,
    )

    @Test
    fun `add project to repository`() {
        // given
        val projectId = getProjectId1()
        val date: LocalDateTime = mockk()
        val oldProject = getProject(id = projectId, isFavourite = false)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(isFavourite = true)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_IS_FAVOURITE, date, true.toString(), false.toString()) }

        // when
        sut(projectId, true, date)

        // then
        verify { projectRepository.saveProject(newProject) }
    }

    @Test
    fun `add activity to repository`() {
        // given
        val projectId = getProjectId1()
        val date: LocalDateTime = mockk()
        val oldProject = getProject(id = projectId, isFavourite = false)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(isFavourite = true)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_IS_FAVOURITE, date, true.toString(), false.toString()) }

        // when
        sut(projectId, true, date)

        // then
        verify { addProjectActivityUseCase(projectId, UPDATE_IS_FAVOURITE, date, true.toString(), false.toString()) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val projectId = getProjectId1()
        val date: LocalDateTime = mockk()
        val oldProject = getProject(id = projectId, isFavourite = true)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(isFavourite = true)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_IS_FAVOURITE, date, true.toString(), true.toString()) }

        // when
        sut(projectId, true, date)

        // then
        verify(exactly = 0) {
            projectRepository.saveProject(newProject)
            addProjectActivityUseCase(projectId, UPDATE_IS_FAVOURITE, date, true.toString(), true.toString())
        }
    }
}
