package com.mango.application.controller

import com.mango.application.model.project.CreateProjectRequestModel
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.Color
import com.mango.domain.project.usecase.CreateProjectUseCase
import com.mango.domain.project.usecase.DeleteProjectUseCase
import com.mango.domain.project.usecase.GetAllProjectsUseCase
import com.mango.domain.project.usecase.GetProjectActivitiesUseCase
import com.mango.domain.project.usecase.GetProjectOrThrowUseCase
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
    private val getProjectActivitiesUseCase: GetProjectActivitiesUseCase = mockk()

    private val sut = ProjectController(
        createProjectUseCase,
        deleteProjectUseCase,
        getProjectOrThrowUseCase,
        getAllProjectsUseCase,
        getProjectActivitiesUseCase,
    )

    @Test
    fun `createProject() calls createProjectUseCase()`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            Color("0xFFFFFF"),
            true,
        )
        every {
            createProjectUseCase(
                createProjectRequestModel.name,
                createProjectRequestModel.color,
                createProjectRequestModel.isFavourite,
            )
        } returns mockk()

        // when
        sut.createProject(createProjectRequestModel)

        // then
        verify {
            createProjectUseCase(
                createProjectRequestModel.name,
                createProjectRequestModel.color,
                createProjectRequestModel.isFavourite,
            )
        }
    }

    @Test
    fun `deleteProject() calls deleteProjectUseCase()`() {
        // given
        val projectId = getProjectId1()
        justRun { deleteProjectUseCase(projectId) }

        // when
        sut.deleteProject(projectId)

        // then
        verify { deleteProjectUseCase(projectId) }
    }

    @Test
    fun `getProject() calls getProjectUseCase()`() {
        // given
        val projectId = getProjectId1()
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

    @Test
    fun `getProjectActivities() calls getProjectActivitiesUseCase()`() {
        // given
        val projectId = getProjectId1()
        every { getProjectActivitiesUseCase(projectId) } returns mockk()

        // when
        sut.getProjectActivities(projectId)

        // then
        verify { getProjectActivitiesUseCase(projectId) }
    }
}
