package com.mango.domain.project.usecase

import com.mango.data.activity.ActivityRepository
import com.mango.data.project.ProjectRepository
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.project.activity.DeleteProjectActivity
import com.mango.domain.project.activity.DeleteProjectActivityFactory
import com.mango.domain.project.model.Project
import com.mango.domain.project.model.ProjectId
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID

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
        val projectId = ProjectId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        val date = mockk<LocalDateTime>()
        every { localDateTimeFactory() } returns date
        val activity: DeleteProjectActivity = mockk()
        every { deleteProjectActivityFactory(projectId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(projectId)

        // then
        verify { projectRepository.deleteProject(project) }
    }

    @Test
    fun `calls addActivity() method in ActivityRepository`() {
        // given
        val projectId = ProjectId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        val date = mockk<LocalDateTime>()
        every { localDateTimeFactory() } returns date
        val activity: DeleteProjectActivity = mockk()
        every { deleteProjectActivityFactory(projectId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(projectId)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
