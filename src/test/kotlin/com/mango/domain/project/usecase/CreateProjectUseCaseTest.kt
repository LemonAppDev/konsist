package com.mango.domain.project.usecase

import com.mango.data.activity.ActivityRepository
import com.mango.data.project.ProjectRepository
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.Color
import com.mango.domain.project.ProjectFactory
import com.mango.domain.project.activity.CreateProjectActivity
import com.mango.domain.project.activity.CreateProjectActivityFactory
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.request.CreateProjectRequestModel
import com.mango.domain.user.model.UserId
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
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
            Color("0xFF0000"),
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { projectFactory(createProjectRequestModel) } returns project
        every { projectRepository.saveProject(project) } returns mockk()
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        val activity: CreateProjectActivity = mockk()
        every { createProjectActivityFactory(projectId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(createProjectRequestModel)

        // then
        verify { projectRepository.saveProject(project) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val collaborators: List<UserId> = mockk()
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            collaborators,
            false,
            Color("0xFF0000"),
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { projectFactory(createProjectRequestModel) } returns project
        every { projectRepository.saveProject(project) } returns mockk()
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

    @Test
    fun `returns project`() {
        // given
        val collaborators: List<UserId> = mockk()
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
            collaborators,
            false,
            Color("0xFF0000"),
        )
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { projectFactory(createProjectRequestModel) } returns project
        val repositoryProject: Project = mockk()
        every { projectRepository.saveProject(project) } returns repositoryProject
        val date: LocalDateTime = mockk()
        every { project.id } returns projectId
        every { project.creationDate } returns date
        val activity: CreateProjectActivity = mockk()
        every { createProjectActivityFactory(projectId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        val actual = sut(createProjectRequestModel)

        // then
        actual shouldBe repositoryProject
    }
}
