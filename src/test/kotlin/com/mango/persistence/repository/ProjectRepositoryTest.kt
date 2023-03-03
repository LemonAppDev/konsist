package com.mango.persistence.repository

import com.mango.business.model.Project
import com.mango.business.model.value.ProjectId
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
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

    @Test
    fun `getProject() return project when it exist`() {
        // given
        val projectId = ProjectId("id")
        val project: Project = mockk()
        every { project.id } returns projectId
        sut.addProject(project)

        // when
        val actual = sut.getProject(projectId)

        // then
        actual shouldBeEqualTo project
    }

    @Test
    fun `getProject() return null when it doesn't exist`() {
        // given
        val projectId = ProjectId("id")
        val project: Project = mockk()
        every { project.id } returns projectId

        // when
        val actual = sut.getProject(projectId)

        // then
        actual shouldBeEqualTo null
    }

    @Test
    fun `containsProject() return true when project exist`() {
        // given
        val projectId = ProjectId("id")
        val project: Project = mockk()
        every { project.id } returns projectId
        sut.addProject(project)

        // when
        val actual = sut.containsProject(projectId)

        // then
        actual shouldBeEqualTo true
    }

    @Test
    fun `containsProject() return false when project doesn't exist`() {
        // given
        val projectId = ProjectId("id")

        // when
        val actual = sut.containsProject(projectId)

        // then
        actual shouldBeEqualTo false
    }
}
