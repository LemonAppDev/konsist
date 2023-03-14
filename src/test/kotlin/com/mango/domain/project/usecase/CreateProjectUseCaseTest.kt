package com.mango.domain.project.usecase

import com.mango.data.project.ProjectRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.ProjectActivityFactory
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.ProjectActivityType.CREATE
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.Color
import com.mango.domain.project.ProjectFactory
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.request.CreateProjectRequestModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CreateProjectUseCaseTest {
    private val projectFactory: ProjectFactory = mockk()
    private val projectRepository: ProjectRepositoryImpl = mockk()
    private val projectActivityFactory: ProjectActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = CreateProjectUseCase(
        projectFactory,
        projectRepository,
        projectActivityFactory,
        activityRepository,
    )

    @Test
    fun `creates and adds new project to projects list`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
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
        val activity: ProjectActivity = mockk()
        every { projectActivityFactory(projectId, date, CREATE) } returns activity
        every { activityRepository.addProjectActivity(activity) } returns mockk()

        // when
        sut(createProjectRequestModel)

        // then
        verify { projectRepository.saveProject(project) }
    }

    @Test
    fun `adds activity to repository`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
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
        val activity: ProjectActivity = mockk()
        every { projectActivityFactory(projectId, date, CREATE) } returns activity
        every { activityRepository.addProjectActivity(activity) } returns mockk()

        // when
        sut(createProjectRequestModel)

        // then
        verify { activityRepository.addProjectActivity(activity) }
    }

    @Test
    fun `returns project`() {
        // given
        val createProjectRequestModel = CreateProjectRequestModel(
            "name",
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
        every { project.id } returns projectId
        every { project.creationDate } returns date
        val activity: ProjectActivity = mockk()
        every { projectActivityFactory(projectId, date, CREATE) } returns activity
        every { activityRepository.addProjectActivity(activity) } returns mockk()

        // when
        val actual = sut(createProjectRequestModel)

        // then
        actual shouldBe repositoryProject
    }
}
