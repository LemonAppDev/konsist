package com.mango.business.usecase.project

import com.mango.persistence.repository.ProjectRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GetAllProjectsUseCaseTest {
    private val projectRepository: ProjectRepository = mockk()

    private val sut = GetAllProjectsUseCase(
        projectRepository,
    )

    @Test
    fun `returns projects from projectRepository`() {
        // given
        every { projectRepository.projects } returns mockk()

        // when
        sut()

        // then
        verify { projectRepository.projects }
    }
}
