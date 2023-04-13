package com.lemon.mango.domain.project.usecase

import com.lemon.mango.data.project.ProjectRepositoryImpl
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CheckProjectIdUseCaseTest {
    private val projectRepository: ProjectRepositoryImpl = mockk()

    private val sut = CheckProjectIdUseCase(
        projectRepository,
    )

    @Test
    fun `throw exception when project doesn't exist`() {
        // given
        val projectId = getProjectId1()
        every { projectRepository.containsProject(projectId) } returns false

        // when
        val actual = { sut(projectId) }

        // then
        actual shouldThrow IllegalStateException::class withMessage "Project with id: $projectId doesn't exist"
    }
}
