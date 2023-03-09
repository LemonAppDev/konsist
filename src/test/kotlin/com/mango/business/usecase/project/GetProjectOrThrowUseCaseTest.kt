package com.mango.business.usecase.project

import com.mango.business.common.model.BusinessTestModel.getProjectId1
import com.mango.business.model.Project
import com.mango.persistence.repository.ProjectRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class GetProjectOrThrowUseCaseTest {
    private val projectRepository: ProjectRepository = mockk()

    private val sut = GetProjectOrThrowUseCase(
        projectRepository,
    )

    @Test
    fun `throws exception when project doesn't exist`() {
        // given
        val projectId = getProjectId1()
        every { projectRepository.getProject(projectId) } returns null

        // when
        val actual = { sut(projectId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Project with id: $projectId doesn't exist"
    }

    @Test
    fun `returns projects from projectRepository`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { projectRepository.getProject(projectId) } returns project

        // when
        val actual = sut(projectId)

        // then
        actual shouldBeEqualTo project
    }
}
