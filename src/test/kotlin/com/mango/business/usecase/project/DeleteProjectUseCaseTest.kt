package com.mango.business.usecase.project

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.Project
import com.mango.business.model.activity.project.DeleteProjectActivity
import com.mango.business.model.activity.project.DeleteProjectActivityFactory
import com.mango.business.model.value.ProjectId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class DeleteProjectUseCaseTest {
    private val projectRepository: ProjectRepository = mockk()
    private val deleteProjectActivityFactory: DeleteProjectActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()

    private val sut = DeleteProjectUseCase(
        projectRepository,
        deleteProjectActivityFactory,
        activityRepository,
        localDateTimeFactory,
    )

    @Test
    fun `deletes project from repository`() {
        // given
        val projectId = ProjectId("id")
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        every { localDateTimeFactory() } returns mockk()
        val activity: DeleteProjectActivity = mockk()
        every { deleteProjectActivityFactory(projectId, any()) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(projectId)

        // then
        verify { projectRepository.deleteProject(project) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val projectId = ProjectId("id")
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        every { localDateTimeFactory() } returns mockk()
        val activity: DeleteProjectActivity = mockk()
        every { deleteProjectActivityFactory(projectId, any()) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(projectId)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
