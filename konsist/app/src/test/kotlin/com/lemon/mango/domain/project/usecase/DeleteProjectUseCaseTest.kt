package com.lemon.mango.domain.project.usecase

import com.lemon.mango.data.project.ProjectRepositoryImpl
import com.lemon.mango.domain.activity.model.ProjectActivityType.DELETE
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.usecase.DeleteTaskUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteProjectUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val deleteTaskUseCase: DeleteTaskUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val projectRepository: ProjectRepositoryImpl = mockk()
    private val taskRepository: TaskRepository = mockk()

    private val sut = DeleteProjectUseCase(
        addProjectActivityUseCase,
        deleteTaskUseCase,
        localDateTimeFactory,
        projectRepository,
        taskRepository,
    )

    @Test
    fun `deletes project from repository`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        every { taskRepository.tasks } returns mockk(relaxed = true)
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        justRun { addProjectActivityUseCase(projectId, DELETE, date) }

        // when
        sut(projectId)

        // then
        verify { projectRepository.deleteProject(project) }
    }

    @Test
    fun `deletes project tasks from repository`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }

        val task1: Task = mockk()
        val id1 = getTaskId1()
        every { task1.id } returns id1
        every { task1.projectId } returns projectId
        val task2: Task = mockk()
        val id2 = getTaskId2()
        every { task2.id } returns id2
        every { task2.projectId } returns projectId
        every { taskRepository.tasks } returns listOf(task1, task2)
        val date = mockk<LocalDateTime>()
        every { localDateTimeFactory() } returns date
        justRun { addProjectActivityUseCase(projectId, DELETE, date) }
        justRun { deleteTaskUseCase(id1, date) }
        justRun { deleteTaskUseCase(id2, date) }

        // when
        sut(projectId)

        // then
        verifyOrder {
            deleteTaskUseCase(id1, date)
            deleteTaskUseCase(id2, date)
        }
    }

    @Test
    fun `adds project activity`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        every { taskRepository.tasks } returns mockk(relaxed = true)
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        justRun { addProjectActivityUseCase(projectId, DELETE, date) }

        // when
        sut(projectId)

        // then
        verify { addProjectActivityUseCase(projectId, DELETE, date) }
    }
}
