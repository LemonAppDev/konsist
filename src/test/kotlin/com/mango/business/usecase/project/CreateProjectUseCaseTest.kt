package com.mango.business.usecase.project

import com.mango.business.common.model.BusinessTestModel.getProjectId1
import com.mango.business.factory.ProjectFactory
import com.mango.business.model.Project
import com.mango.business.model.activity.project.CreateProjectActivity
import com.mango.business.model.activity.project.CreateProjectActivityFactory
import com.mango.business.model.request.project.CreateProjectRequestModel
import com.mango.business.model.value.Color
import com.mango.business.model.value.UserId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
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
