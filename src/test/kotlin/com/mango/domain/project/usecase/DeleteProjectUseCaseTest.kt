package com.mango.domain.project.usecase

import com.mango.data.project.ProjectRepositoryImpl
import com.mango.domain.activity.usecase.DeleteProjectActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.project.model.Project
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteProjectUseCaseTest {
    private val projectRepository: ProjectRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val deleteProjectActivityUseCase: DeleteProjectActivityUseCase = mockk()

    private val sut = DeleteProjectUseCase(
        projectRepository,
        localDateTimeFactory,
        deleteProjectActivityUseCase,
    )

    @Test
    fun `deletes project from repository`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        val date = mockk<LocalDateTime>()
        every { localDateTimeFactory() } returns date
        every { deleteProjectActivityUseCase(projectId, date) } returns mockk()

        // when
        sut(projectId)

        // then
        verify { projectRepository.deleteProject(project) }
    }

    @Test
    fun `calls deleteProjectActivityUseCase`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        val date = mockk<LocalDateTime>()
        every { localDateTimeFactory() } returns date
        every { deleteProjectActivityUseCase(projectId, date) } returns mockk()

        // when
        sut(projectId)

        // then
        verify { deleteProjectActivityUseCase(projectId, date) }
    }
}
