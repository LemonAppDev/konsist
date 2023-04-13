package com.lemon.mango.domain.project.usecase

import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.Project
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CheckNewProjectNameUseCaseTest {
    private val projectRepository: ProjectRepository = mockk()

    private val sut = CheckNewProjectNameUseCase(
        projectRepository,
    )

    @Test
    fun `throws exception when given name is blank`() {
        // given
        val name = "    "

        // when
        val actual = { sut(name) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Project name is blank: $name"
    }

    @Test
    fun `throws exception when exists project in repository with given name`() {
        // given
        val name = "name"
        val project: Project = mockk()
        every { project.name } returns name
        every { projectRepository.projects } returns listOf(project)

        // when
        val actual = { sut(name) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Project with name: $name already exists"
    }
}
