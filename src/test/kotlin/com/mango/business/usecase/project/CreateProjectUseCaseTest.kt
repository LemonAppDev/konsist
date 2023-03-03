package com.mango.business.usecase.project

import com.mango.business.factory.ProjectFactory
import com.mango.business.model.Color
import com.mango.business.model.Project
import com.mango.business.model.activity.project.CreateProjectActivity
import com.mango.business.model.activity.project.CreateProjectActivityFactory
import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CreateProjectUseCaseTest {
    private val projectFactory: ProjectFactory = mockk()
    private val projectRepository: ProjectRepository = mockk()
    private val createProjectActivityFactory: CreateProjectActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = CreateProjectUseCase(
        projectFactory,
        projectRepository,
        createProjectActivityFactory,
        activityRepository,
    )

    @Test
    fun `creates and adds new project to projects list`() {
        // given
        val collaborators: List<UserId> = mockk()
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            collaborators,
            false,
            Color.GREY,
        )
        val projectId = ProjectId("id")
        val project: Project = mockk()
        every { projectFactory(createProjectRequestModel) } returns project
        justRun { projectRepository.addProject(project) }
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        val activity: CreateProjectActivity = mockk()
        every { createProjectActivityFactory(projectId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(createProjectRequestModel)

        // then
        verify { projectRepository.addProject(project) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val collaborators: List<UserId> = mockk()
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            collaborators,
            false,
            Color.GREY,
        )
        val projectId = ProjectId("id")
        val project: Project = mockk()
        every { projectFactory(createProjectRequestModel) } returns project
        justRun { projectRepository.addProject(project) }
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        val activity: CreateProjectActivity = mockk()
        every { createProjectActivityFactory(projectId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(createProjectRequestModel)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
