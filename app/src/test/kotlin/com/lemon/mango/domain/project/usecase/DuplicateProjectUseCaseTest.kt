package com.lemon.mango.domain.project.usecase

import com.lemon.mango.domain.activity.model.ProjectActivityType.CREATE
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UUIDFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId2
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.usecase.DuplicateTaskUseCase
import com.lemon.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DuplicateProjectUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val checkNewProjectNameUseCase: CheckNewProjectNameUseCase = mockk()
    private val duplicateTaskUseCase: DuplicateTaskUseCase = mockk()
    private val getNewProjectNameUseCase: GetNewProjectNameUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val projectRepository: ProjectRepository = mockk()
    private val taskRepository: TaskRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val uuidFactory: UUIDFactory = mockk()

    private val sut = DuplicateProjectUseCase(
        addProjectActivityUseCase,
        checkNewProjectNameUseCase,
        duplicateTaskUseCase,
        getNewProjectNameUseCase,
        getProjectOrThrowUseCase,
        localDateTimeFactory,
        projectRepository,
        taskRepository,
        userRepository,
        uuidFactory,
    )

    @Test
    fun `saves duplicated project with default name because name is null`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val name = "name"
        every { oldProject.name } returns name
        val newName = "name copy"
        every { getNewProjectNameUseCase(name) } returns newName
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, name = newName, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldProjectId)

        // then
        verify { projectRepository.saveProject(newProject) }
    }

    @Test
    fun `saves duplicated project with given name which is not null`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val name = "name"
        justRun { checkNewProjectNameUseCase(name) }
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, name = name, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldProjectId, name)

        // then
        verify { projectRepository.saveProject(newProject) }
    }

    @Test
    fun `adds 'project create activity' when name is null`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val name = "name"
        every { oldProject.name } returns name
        val newName = "name copy"
        every { getNewProjectNameUseCase(name) } returns newName
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, name = newName, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldProjectId)

        // then
        verify { addProjectActivityUseCase(newProjectId, CREATE, date) }
    }

    @Test
    fun `adds 'project create activity' when given name is not null`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val name = "name"
        justRun { checkNewProjectNameUseCase(name) }
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, name = name, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldProjectId, name)

        // then
        verify { addProjectActivityUseCase(newProjectId, CREATE, date) }
    }

    @Test
    fun `returns duplicated project when name is null`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val name = "name"
        justRun { checkNewProjectNameUseCase(name) }
        every { oldProject.name } returns name
        val newName = "name copy"
        every { getNewProjectNameUseCase(name) } returns newName
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, name = newName, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        val actual = sut(oldProjectId)

        // then
        actual shouldBeEqualTo newProject
    }

    @Test
    fun `returns duplicated project when name is not null`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val name = "name"
        justRun { checkNewProjectNameUseCase(name) }
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, name = name, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        val actual = sut(oldProjectId, name)

        // then
        actual shouldBeEqualTo newProject
    }

    @Test
    fun `saves duplicated tasks in new project when name is null`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val name = "name"
        every { oldProject.name } returns name
        val newName = "name copy"
        every { getNewProjectNameUseCase(name) } returns newName
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, name = newName, creationDate = date, owner = userId) } returns newProject
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

    @Test
    fun `saves duplicated tasks in new project when name is not null`() {
        // given
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val oldProject: Project = mockk()
        val newProject: Project = mockk()
        every { getProjectOrThrowUseCase(oldProjectId) } returns oldProject
        every { uuidFactory.createProjectId() } returns newProjectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val name = "name"
        justRun { checkNewProjectNameUseCase(name) }
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldProject.copy(id = newProjectId, name = name, creationDate = date, owner = userId) } returns newProject
        every { projectRepository.saveProject(newProject) } returns newProject
        every { addProjectActivityUseCase(newProjectId, CREATE, date) } returns mockk()
        val task: Task = mockk()
        val taskId = getTaskId1()
        every { task.projectId } returns oldProjectId
        every { task.id } returns taskId
        every { duplicateTaskUseCase(taskId, newProjectId, date) } returns mockk()
        every { taskRepository.tasks } returns listOf(task)

        // when
        sut(oldProjectId, name)

        // then
        verify { duplicateTaskUseCase(taskId, newProjectId, date) }
    }
}
