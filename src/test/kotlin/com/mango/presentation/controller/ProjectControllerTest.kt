package com.mango.presentation.controller

import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.usecase.project.CreateProjectUseCase
import com.mango.business.usecase.project.GetProjectOrThrowUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ProjectControllerTest {
    private val createProjectUseCase: CreateProjectUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()

    private val sut = ProjectController(
        createProjectUseCase,
        getProjectOrThrowUseCase,
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
    fun `getProject() calls getProjectUseCase()`() {
        // given
        val projectId = ProjectId("projectId")
        every { getProjectOrThrowUseCase(projectId) } returns mockk()

        // when
        sut.getProject(projectId)

        // then
        verify { getProjectOrThrowUseCase(projectId) }
    }
}
