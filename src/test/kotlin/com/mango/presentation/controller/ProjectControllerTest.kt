package com.mango.presentation.controller

import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.usecase.project.CreateProjectUseCase
import com.mango.business.usecase.project.DeleteProjectUseCase
import com.mango.business.usecase.project.GetAllProjectsUseCase
import com.mango.business.usecase.project.GetProjectOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ProjectControllerTest {
    private val createProjectUseCase: CreateProjectUseCase = mockk()
    private val deleteProjectUseCase: DeleteProjectUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val getAllProjectsUseCase: GetAllProjectsUseCase = mockk()

    private val sut = ProjectController(
        createProjectUseCase,
        deleteProjectUseCase,
        getProjectOrThrowUseCase,
        getAllProjectsUseCase,
    )

    @Test
    fun `createProject() calls createProjectUseCase()`() {
        // given
        val createProjectRequestModel: CreateProjectRequestModel = mockk()
        every { createProjectUseCase(createProjectRequestModel) } returns mockk()

        // when
        sut.createProject(createProjectRequestModel)

        // then
        verify { createProjectUseCase(createProjectRequestModel) }
    }

    @Test
    fun `deleteProject() calls deleteProjectUseCase()`() {
        // given
        val projectId = ProjectId("id")
        justRun { deleteProjectUseCase(projectId) }

        // when
        sut.deleteProject(projectId)

        // then
        verify { deleteProjectUseCase(projectId) }
    }

    @Test
    fun `getProject() calls getProjectUseCase()`() {
        // given
        val projectId = ProjectId("projectId")
        every { getProjectOrThrowUseCase(projectId) } returns mockk()

        // when
        sut.getProject(projectId)

        // then
        verify { getProjectOrThrowUseCase(projectId) }
    }

    @Test
    fun `getProjects() calls getAllProjectsUseCase()`() {
        // given
        every { getAllProjectsUseCase() } returns mockk()

        // when
        sut.getProjects()

        // then
        verify { getAllProjectsUseCase() }
    }
}
