package com.mango.domain.project.usecase

import com.mango.application.model.project.CreateProjectRequestModel
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
    private val projectFactory: ProjectFactory = mockk()
    private val projectRepository: ProjectRepositoryImpl = mockk()
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()

    private val sut = CreateProjectUseCase(
        projectFactory,
        projectRepository,
        addProjectActivityUseCase,
    )

    @Test
    fun `creates and adds new project to projects list`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            Color("0xFF0000"),
            false,
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every {
            projectFactory(
                createProjectRequestModel.name,
                createProjectRequestModel.color,
                createProjectRequestModel.isFavourite,
            )
        } returns project
        every { projectRepository.saveProject(project) } returns mockk()
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        justRun { addProjectActivityUseCase(projectId, CREATE, date) }

        // when
        sut(
            createProjectRequestModel.name,
            createProjectRequestModel.color,
            createProjectRequestModel.isFavourite,
        )

        // then
        verify { projectRepository.saveProject(project) }
    }

    @Test
    fun `calls addProjectActivityUseCase()`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            Color("0xFF0000"),
            false,
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every {
            projectFactory(
                createProjectRequestModel.name,
                createProjectRequestModel.color,
                createProjectRequestModel.isFavourite,
            )
        } returns project
        every { projectRepository.saveProject(project) } returns mockk()
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        justRun { addProjectActivityUseCase(projectId, CREATE, date) }

        // when
        sut(
            createProjectRequestModel.name,
            createProjectRequestModel.color,
            createProjectRequestModel.isFavourite,
        )

        // then
        verify { addProjectActivityUseCase(projectId, CREATE, date) }
    }

    @Test
    fun `returns project`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            Color("0xFF0000"),
            false,
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every {
            projectFactory(
                createProjectRequestModel.name,
                createProjectRequestModel.color,
                createProjectRequestModel.isFavourite,
            )
        } returns project
        val repositoryProject: Project = mockk()
        every { projectRepository.saveProject(project) } returns repositoryProject
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        every { project.id } returns projectId
        every { project.creationDate } returns date
        justRun { addProjectActivityUseCase(projectId, CREATE, date) }

        // when
        val actual = sut(
            createProjectRequestModel.name,
            createProjectRequestModel.color,
            createProjectRequestModel.isFavourite,
        )

        // then
        actual shouldBe repositoryProject
    }
}
