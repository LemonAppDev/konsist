package com.mango.business.usecase.project

import com.mango.business.model.value.ProjectId
import com.mango.persistence.repository.ProjectRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CheckProjectIdUseCaseTest {
    private val projectRepository: ProjectRepository = mockk()

    private val sut = CheckProjectIdUseCase(
        projectRepository,
    )

    @Test
    fun `throw exception when project doesn't exist`() {
        // given
        val projectId = ProjectId("id")
        every { projectRepository.containsProject(projectId) } returns false

        // when
        val actual = { sut(projectId) }

        // then
        actual shouldThrow IllegalStateException::class withMessage "Project with id: $projectId doesn't exist"
    }
}
