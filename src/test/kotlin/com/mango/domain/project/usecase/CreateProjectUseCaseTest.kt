package com.mango.domain.project.usecase

import com.mango.data.project.ProjectRepositoryImpl
import com.mango.domain.activity.usecase.CreateProjectActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.Color
import com.mango.domain.project.ProjectFactory
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.request.CreateProjectRequestModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CreateProjectUseCaseTest {
    private val projectFactory: ProjectFactory = mockk()
    private val projectRepository: ProjectRepositoryImpl = mockk()
    private val createProjectActivityUseCase: CreateProjectActivityUseCase = mockk()

    private val sut = CreateProjectUseCase(
        projectFactory,
        projectRepository,
        createProjectActivityUseCase,
    )

    @Test
    fun `creates and adds new project to projects list`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            false,
            Color("0xFF0000"),
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { projectFactory(createProjectRequestModel) } returns project
        every { projectRepository.saveProject(project) } returns mockk()
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        every { createProjectActivityUseCase(projectId, date) } returns mockk()

        // when
        sut(createProjectRequestModel)

        // then
        verify { projectRepository.saveProject(project) }
    }

    @Test
    fun `calls createProjectActivityUseCase`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            false,
            Color("0xFF0000"),
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { projectFactory(createProjectRequestModel) } returns project
        every { projectRepository.saveProject(project) } returns mockk()
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        every { createProjectActivityUseCase(projectId, date) } returns mockk()

        // when
        sut(createProjectRequestModel)

        // then
        verify { createProjectActivityUseCase(projectId, date) }
    }

    @Test
    fun `returns project`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            false,
            Color("0xFF0000"),
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { projectFactory(createProjectRequestModel) } returns project
        val repositoryProject: Project = mockk()
        every { projectRepository.saveProject(project) } returns repositoryProject
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        every { project.id } returns projectId
        every { project.creationDate } returns date
        every { createProjectActivityUseCase(projectId, date) } returns mockk()

        // when
        val actual = sut(createProjectRequestModel)

        // then
        actual shouldBe repositoryProject
    }
}
