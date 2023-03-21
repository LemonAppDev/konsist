package com.mango.domain.project.usecase

import com.mango.domain.activity.model.ProjectActivityType.CREATE
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getProjectId2
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.Project
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.usecase.DuplicateTaskUseCase
import com.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DuplicateProjectUseCaseTest {
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val uuidFactory: UUIDFactory = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val projectRepository: ProjectRepository = mockk()
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val taskRepository: TaskRepository = mockk()
    private val duplicateTaskUseCase: DuplicateTaskUseCase = mockk()
    private val userRepository: UserRepository = mockk()

    private val sut = DuplicateProjectUseCase(
        getProjectOrThrowUseCase,
        uuidFactory,
        localDateTimeFactory,
        projectRepository,
        addProjectActivityUseCase,
        taskRepository,
        duplicateTaskUseCase,
        userRepository,
    )

    @Test
    fun `saves duplicated project`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldProjectId)

        // then
        verify { projectRepository.saveProject(newProject) }
    }

    @Test
    fun `adds 'project create activity'`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldProjectId)

        // then
        verify { addProjectActivityUseCase(newProjectId, CREATE, date) }
    }

    @Test
    fun `returns duplicated project`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        val actual = sut(oldProjectId)

        // then
        actual shouldBeEqualTo newProject
    }

    @Test
    fun `saves duplicated tasks in new project `() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        val task: Task = mockk()
        val taskId = getTaskId1()
        every { task.projectId } returns oldProjectId
        every { task.id } returns taskId
        every { duplicateTaskUseCase(taskId, newProjectId, date) } returns mockk()
        every { taskRepository.tasks } returns listOf(task)

        // when
        sut(oldProjectId)

        // then
        verify { duplicateTaskUseCase(taskId, newProjectId, date) }
    }
}
