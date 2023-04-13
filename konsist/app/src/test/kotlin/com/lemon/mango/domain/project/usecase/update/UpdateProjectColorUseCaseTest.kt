package com.lemon.mango.domain.project.usecase.update

import com.lemon.mango.data.project.ProjectRepositoryImpl
import com.lemon.mango.domain.activity.model.ProjectActivityType.UPDATE_COLOR
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.common.model.BusinessTestModel.getProject
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateProjectColorUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val projectRepository: ProjectRepositoryImpl = mockk()

    private val sut = UpdateProjectColorUseCase(
        addProjectActivityUseCase,
        getProjectOrThrowUseCase,
        projectRepository,
    )

    @Test
    fun `add project to repository`() {
        // given
        val projectId = getProjectId1()
        val oldColor = Color("oldColor")
        val newColor = Color("newColor")
        val date: LocalDateTime = mockk()

        val oldProject = getProject(id = projectId, color = oldColor)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(color = newColor)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_COLOR, date, newColor.value, oldColor.value) }

        // when
        sut(projectId, newColor, date)

        // then
        verify { projectRepository.saveProject(newProject) }
    }

    @Test
    fun `add activity to repository`() {
        // given
        val projectId = getProjectId1()
        val oldColor = Color("oldColor")
        val newColor = Color("newColor")
        val date: LocalDateTime = mockk()

        val oldProject = getProject(id = projectId, color = oldColor)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(color = newColor)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_COLOR, date, newColor.value, oldColor.value) }

        // when
        sut(projectId, newColor, date)

        // then
        verify { addProjectActivityUseCase(projectId, UPDATE_COLOR, date, newColor.value, oldColor.value) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val projectId = getProjectId1()
        val oldColor = Color("color")
        val newColor = Color("color")
        val date: LocalDateTime = mockk()

        val oldProject = getProject(id = projectId, color = oldColor)
        every { getProjectOrThrowUseCase(projectId) } returns oldProject
        val newProject = oldProject.copy(color = newColor)
        every { projectRepository.saveProject(newProject) } returns mockk()
        justRun { addProjectActivityUseCase(projectId, UPDATE_COLOR, date, newColor.value, oldColor.value) }

        // when
        sut(projectId, newColor, date)

        // then
        verify(exactly = 0) {
            projectRepository.saveProject(newProject)
            addProjectActivityUseCase(projectId, UPDATE_COLOR, date, newColor.value, oldColor.value)
        }
    }
}
