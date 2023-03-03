package com.mango.persistence.repository

import com.mango.business.model.Project
import io.mockk.mockk
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class ProjectRepositoryTest {
    private val sut = ProjectRepository()

    @Test
    fun `addProject() adds project to projects`() {
        // given
        val project: Project = mockk()

        // when
        sut.addProject(project)

        // then
        sut.projects shouldContain project
    }
}
