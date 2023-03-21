package com.mango.domain.project.usecase

import com.mango.data.project.ProjectRepositoryImpl
import com.mango.domain.activity.model.ProjectActivityType.CREATE
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.Color
import com.mango.domain.project.ProjectFactory
import com.mango.domain.project.model.Project
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CreateProjectUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val checkNewProjectNameUseCase: CheckNewProjectNameUseCase = mockk()
    private val projectFactory: ProjectFactory = mockk()
    private val projectRepository: ProjectRepositoryImpl = mockk()

    private val sut = CreateProjectUseCase(
        addProjectActivityUseCase,
        checkNewProjectNameUseCase,
        projectFactory,
        projectRepository,
    )

    @Test
    fun `creates and adds new project to projects list`() {
        // given
        val name = "name"
        val color = Color("0xFF0000")
        val isFavourite = false
        val projectId = getProjectId1()
        val project: Project = mockk()
        justRun { checkNewProjectNameUseCase(name) }
        every { projectFactory(name, color, isFavourite) } returns project
        every { projectRepository.saveProject(project) } returns mockk()
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        justRun { addProjectActivityUseCase(projectId, CREATE, date) }

        // when
        sut(name, color, isFavourite)

        // then
        verify { projectRepository.saveProject(project) }
    }

    @Test
    fun `calls addProjectActivityUseCase()`() {
        // given
        val name = "name"
        val color = Color("0xFF0000")
        val isFavourite = false
        val projectId = getProjectId1()
        val project: Project = mockk()
        justRun { checkNewProjectNameUseCase(name) }
        every { projectFactory(name, color, isFavourite) } returns project
        every { projectRepository.saveProject(project) } returns mockk()
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        justRun { addProjectActivityUseCase(projectId, CREATE, date) }

        // when
        sut(name, color, isFavourite)

        // then
        verify { addProjectActivityUseCase(projectId, CREATE, date) }
    }

    @Test
    fun `returns project`() {
        // given
        val name = "name"
        val color = Color("0xFF0000")
        val isFavourite = false
        val projectId = getProjectId1()
        val project: Project = mockk()
        justRun { checkNewProjectNameUseCase(name) }
        every { projectFactory(name, color, isFavourite) } returns project
        val repositoryProject: Project = mockk()
        every { projectRepository.saveProject(project) } returns repositoryProject
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        every { project.id } returns projectId
        every { project.creationDate } returns date
        justRun { addProjectActivityUseCase(projectId, CREATE, date) }

        // when
        val actual = sut(name, color, isFavourite)

        // then
        actual shouldBe repositoryProject
    }
}
