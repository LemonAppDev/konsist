package com.lemon.mango.application.controller

import com.lemon.mango.application.model.project.CreateProjectRequestModel
import com.lemon.mango.application.model.project.DuplicateProjectRequestModel
import com.lemon.mango.application.model.project.UpdateProjectRequestModel
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.Color
import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.project.usecase.CreateProjectUseCase
import com.lemon.mango.domain.project.usecase.DeleteProjectUseCase
import com.lemon.mango.domain.project.usecase.DuplicateProjectUseCase
import com.lemon.mango.domain.project.usecase.GetAllProjectsUseCase
import com.lemon.mango.domain.project.usecase.GetProjectActivitiesUseCase
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import com.lemon.mango.domain.project.usecase.update.UpdateProjectUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ProjectControllerTest {
    private val createProjectUseCase: CreateProjectUseCase = mockk()
    private val deleteProjectUseCase: DeleteProjectUseCase = mockk()
    private val duplicateProjectUseCase: DuplicateProjectUseCase = mockk()
    private val getAllProjectsUseCase: GetAllProjectsUseCase = mockk()
    private val getProjectActivitiesUseCase: GetProjectActivitiesUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val updateProjectUseCase: UpdateProjectUseCase = mockk()

    private val sut = ProjectController(
        createProjectUseCase,
        deleteProjectUseCase,
        duplicateProjectUseCase,
        getAllProjectsUseCase,
        getProjectActivitiesUseCase,
        getProjectOrThrowUseCase,
        updateProjectUseCase,
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

    @Test
    fun `duplicateProject() calls duplicateProjectUseCase()`() {
        // given
        val projectId = getProjectId1()
        val requestModel = DuplicateProjectRequestModel(projectId)
        every { duplicateProjectUseCase(projectId) } returns mockk()

        // when
        sut.duplicateProject(requestModel)

        // then
        verify { duplicateProjectUseCase(projectId) }
    }

    @Test
    fun `updateProject() calls updateProjectUseCase()`() {
        // given
        val projectId = getProjectId1()
        val name = "name"
        val color = Color("0xFFFFFF")
        val isFavourite = true
        val requestModel = UpdateProjectRequestModel(projectId, name, color, isFavourite)
        val project: Project = mockk()
        every { updateProjectUseCase(projectId, name, color, isFavourite) } returns mockk()
        justRun { updateProjectUseCase(projectId, name, color, isFavourite) }
        every { getProjectOrThrowUseCase(projectId) } returns project

        // when
        sut.updateProject(requestModel)

        // then
        verify { updateProjectUseCase(projectId, name, color, isFavourite) }
    }

    @Test
    fun `updateProject() returns updated project`() {
        // given
        val projectId = getProjectId1()
        val name = "name"
        val color = Color("0xFFFFFF")
        val isFavourite = true
        val requestModel = UpdateProjectRequestModel(projectId, name, color, isFavourite)
        val project: Project = mockk()
        every { updateProjectUseCase(projectId, name, color, isFavourite) } returns mockk()
        justRun { updateProjectUseCase(projectId, name, color, isFavourite) }
        every { getProjectOrThrowUseCase(projectId) } returns project

        // when
        val actual = sut.updateProject(requestModel)

        // then
        actual shouldBeEqualTo project
    }
}
