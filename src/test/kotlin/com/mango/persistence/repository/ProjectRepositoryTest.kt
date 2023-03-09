package com.mango.persistence.repository

import com.mango.business.common.model.BusinessTestModel.getProjectId1
import com.mango.business.model.Project
import com.mango.persistence.datasource.ProjectJpaRepository
import com.mango.persistence.entity.ProjectJpaEntity
import com.mango.persistence.entity.mapper.ProjectJpaEntityToProjectMapper
import com.mango.persistence.entity.mapper.ProjectToProjectJpaEntityMapper
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.jvm.optionals.getOrNull

class ProjectRepositoryTest {
    private val projectJpaRepository: ProjectJpaRepository = mockk()
    private val projectToProjectJpaEntityMapper: ProjectToProjectJpaEntityMapper = mockk()
    private val projectJpaEntityToProjectMapper: ProjectJpaEntityToProjectMapper = mockk()

    private val sut = ProjectRepository(
        projectJpaRepository,
        projectToProjectJpaEntityMapper,
        projectJpaEntityToProjectMapper,
    )

    @Test
    fun `saveProject() saves project`() {
        // given
        val project: Project = mockk()
        val projectJpaEntity: ProjectJpaEntity = mockk()
        every { projectToProjectJpaEntityMapper(project) } returns projectJpaEntity
        every { projectJpaRepository.save(projectJpaEntity) } returns projectJpaEntity
        every { projectJpaEntityToProjectMapper(projectJpaEntity) } returns project

        // when
        sut.saveProject(project)

        // then
        verify { projectJpaRepository.save(projectJpaEntity) }
    }

    @Test
    fun `saveProject() returns project`() {
        // given
        val project: Project = mockk()
        val projectJpaEntity: ProjectJpaEntity = mockk()
        every { projectToProjectJpaEntityMapper(project) } returns projectJpaEntity
        every { projectJpaRepository.save(projectJpaEntity) } returns projectJpaEntity
        every { projectJpaEntityToProjectMapper(projectJpaEntity) } returns project

        // when
        val actual = sut.saveProject(project)

        // then
        actual shouldBeEqualTo project
    }

    @Test
    fun `deleteProject() deletes project`() {
        // given
        val project: Project = mockk()
        val projectJpaEntity: ProjectJpaEntity = mockk()
        every { projectToProjectJpaEntityMapper(project) } returns projectJpaEntity
        justRun { projectJpaRepository.delete(projectJpaEntity) }
        every { projectJpaEntityToProjectMapper(projectJpaEntity) } returns project

        // when
        sut.deleteProject(project)

        // then
        verify { projectJpaRepository.delete(projectJpaEntity) }
    }

    @Test
    fun `getProject() return project when it exist`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        val projectJpaEntity: ProjectJpaEntity = mockk()
        every { projectJpaRepository.findById(projectId.value).getOrNull() } returns projectJpaEntity
        every { projectJpaEntityToProjectMapper(projectJpaEntity) } returns project

        // when
        val actual = sut.getProject(projectId)

        // then
        actual shouldBeEqualTo project
    }

    @Test
    fun `getProject() return null when it doesn't exist`() {
        // given
        val projectId = getProjectId1()
        every { projectJpaRepository.findById(projectId.value).getOrNull() } returns null

        // when
        val actual = sut.getProject(projectId)

        // then
        actual shouldBeEqualTo null
    }

    @Test
    fun `containsProject() return true when project exist`() {
        // given
        val projectId = getProjectId1()
        every { projectJpaRepository.existsById(projectId.value) } returns true

        // when
        val actual = sut.containsProject(projectId)

        // then
        actual shouldBeEqualTo true
    }

    @Test
    fun `containsProject() return false when project doesn't exist`() {
        // given
        val projectId = getProjectId1()
        every { projectJpaRepository.existsById(projectId.value) } returns false

        // when
        val actual = sut.containsProject(projectId)

        // then
        actual shouldBeEqualTo false
    }
}
