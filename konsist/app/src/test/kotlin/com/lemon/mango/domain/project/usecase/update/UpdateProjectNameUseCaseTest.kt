package com.lemon.mango.domain.project.usecase.update

import com.lemon.mango.data.project.ProjectRepositoryImpl
import com.lemon.mango.domain.activity.model.ProjectActivityType.UPDATE_NAME
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

class UpdateProjectNameUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val projectRepository: ProjectRepositoryImpl = mockk()

    private val sut = UpdateProjectNameUseCase(
        addProjectActivityUseCase,
        getProjectOrThrowUseCase,
        projectRepository,
    )

    @Test
    fun `add project to repository`() {
        // given
        val projectId = getProjectId1()
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldProject = getProject(id = projectId, name = oldName)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(name = newName)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_NAME, date, newName, oldName) }

        // when
        sut(projectId, newName, date)

        // then
        verify { projectRepository.saveProject(newProject) }
    }

    @Test
    fun `add activity to repository`() {
        // given
        val projectId = getProjectId1()
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldProject = getProject(id = projectId, name = oldName)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(name = newName)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_NAME, date, newName, oldName) }

        // when
        sut(projectId, newName, date)

        // then
        verify { addProjectActivityUseCase(projectId, UPDATE_NAME, date, newName, oldName) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val projectId = getProjectId1()
        val oldName = "name"
        val newName = "name"
        val date: LocalDateTime = mockk()

        val oldProject = getProject(id = projectId, name = oldName)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(name = newName)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_NAME, date, newName, oldName) }

        // when
        sut(projectId, newName, date)

        // then
        verify(exactly = 0) {
            projectRepository.saveProject(newProject)
            addProjectActivityUseCase(projectId, UPDATE_NAME, date, newName, oldName)
        }
    }
}
