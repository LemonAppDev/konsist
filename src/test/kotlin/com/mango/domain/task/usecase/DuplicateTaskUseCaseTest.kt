package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.model.ProjectActivityType.TASK_ADDED
import com.mango.domain.activity.model.TaskActivityType.CREATE
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getTaskId3
import com.mango.domain.common.model.BusinessTestModel.getTaskId4
import com.mango.domain.common.model.BusinessTestModel.getTaskId5
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DuplicateTaskUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val uuidFactory: UUIDFactory = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()

    private val sut = DuplicateTaskUseCase(
        taskRepository,
        uuidFactory,
        localDateTimeFactory,
        getTaskOrThrowUseCase,
        addTaskActivityUseCase,
        addProjectActivityUseCase,
    )

    @Test
    fun `add duplicated task`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newTaskId, creationDate = date) } returns newTask
        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(newTask) } returns repositoryTask
        every { repositoryTask.projectId } returns null
        justRun { addTaskActivityUseCase(newTaskId, CREATE, date) }
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldTaskId)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `adds 'task create activity'`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { oldTask.copy(newTaskId, creationDate = date) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newTaskId) } returns newTask
        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(newTask) } returns repositoryTask
        every { repositoryTask.projectId } returns null
        justRun { addTaskActivityUseCase(newTaskId, CREATE, date) }
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldTaskId)

        // then
        verify { addTaskActivityUseCase(newTaskId, CREATE, date) }
    }

    @Test
    fun `add duplicated child task`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        val newChildTaskId = getTaskId3()
        every { uuidFactory.createTaskId() } returns newTaskId andThen newChildTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { oldTask.copy(newTaskId, creationDate = date) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        val repositoryTask: Task = mockk()
        val repositoryTaskId = getTaskId4()
        every { taskRepository.saveTask(newTask) } returns repositoryTask
        every { repositoryTask.projectId } returns null
        every { repositoryTask.id } returns repositoryTaskId
        justRun { addTaskActivityUseCase(newTaskId, CREATE, date) }

        val childTask: Task = mockk()
        val childTaskId = getTaskId5()
        val newChildTask: Task = mockk()
        every { newChildTask.id } returns newChildTaskId
        every { newChildTask.creationDate } returns date
        every { childTask.id } returns childTaskId
        every { childTask.parentTaskId } returns oldTaskId
        every { taskRepository.tasks } returns listOf(childTask) andThen listOf()
        every { childTask.copy(newChildTaskId, creationDate = date, parentTaskId = repositoryTaskId) } returns newChildTask
        val newRepositoryTask: Task = mockk()
        every { newRepositoryTask.id } returns repositoryTaskId
        every { newRepositoryTask.projectId } returns null
        every { taskRepository.saveTask(newChildTask) } returns newRepositoryTask
        justRun { addTaskActivityUseCase(newChildTaskId, CREATE, date) }
        // when
        sut(oldTaskId)

        // then
        verify { taskRepository.saveTask(newChildTask) }
    }

    @Test
    fun `adds 'task create activity' for duplicated child task`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        val newChildTaskId = getTaskId3()
        every { uuidFactory.createTaskId() } returns newTaskId andThen newChildTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { oldTask.copy(newTaskId, creationDate = date, parentTaskId = null) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        val repositoryTask: Task = mockk()
        val repositoryTaskId = getTaskId4()
        every { taskRepository.saveTask(newTask) } returns repositoryTask
        every { repositoryTask.projectId } returns null
        every { repositoryTask.id } returns repositoryTaskId
        justRun { addTaskActivityUseCase(newTaskId, CREATE, date) }

        val childTask: Task = mockk()
        val childTaskId = getTaskId5()
        val newChildTask: Task = mockk()
        every { newChildTask.id } returns newChildTaskId
        every { newChildTask.creationDate } returns date
        every { childTask.id } returns childTaskId
        every { childTask.parentTaskId } returns oldTaskId
        every { taskRepository.tasks } returns listOf(childTask) andThen listOf()
        every { childTask.copy(newChildTaskId, creationDate = date, parentTaskId = repositoryTaskId) } returns newChildTask
        val newRepositoryTask: Task = mockk()
        every { newRepositoryTask.id } returns repositoryTaskId
        every { newRepositoryTask.projectId } returns null
        every { taskRepository.saveTask(newChildTask) } returns newRepositoryTask
        justRun { addTaskActivityUseCase(newChildTaskId, CREATE, date) }

        // when
        sut(oldTaskId)

        // then
        verify { addTaskActivityUseCase(newChildTaskId, CREATE, date) }
    }

    @Test
    fun `adds 'project task added activity' when project is non-null`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { oldTask.copy(newTaskId, creationDate = date) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newTaskId) } returns newTask
        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(newTask) } returns repositoryTask
        val projectId = getProjectId1()
        every { repositoryTask.id } returns newTaskId
        every { repositoryTask.projectId } returns projectId
        justRun { addTaskActivityUseCase(newTaskId, CREATE, date) }
        justRun { addProjectActivityUseCase(projectId, TASK_ADDED, date, newTaskId.toString()) }
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldTaskId)

        // then
        verify { addProjectActivityUseCase(projectId, TASK_ADDED, date, newTaskId.toString()) }
    }

    @Test
    fun `adds 'project task added activity' for duplicated child task when project is non-null`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        val newChildTaskId = getTaskId3()
        every { uuidFactory.createTaskId() } returns newTaskId andThen newChildTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { oldTask.copy(newTaskId, creationDate = date) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        val repositoryTaskId = getTaskId4()
        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(newTask) } returns repositoryTask
        every { repositoryTask.projectId } returns null
        every { repositoryTask.id } returns repositoryTaskId
        justRun { addTaskActivityUseCase(newTaskId, CREATE, date) }
        val projectId = getProjectId1()
        every { repositoryTask.projectId } returns projectId

        val childTask: Task = mockk()
        val childTaskId = getTaskId5()
        val newChildTask: Task = mockk()
        every { newChildTask.id } returns newChildTaskId
        every { newChildTask.creationDate } returns date
        every { childTask.id } returns childTaskId
        every { childTask.parentTaskId } returns oldTaskId
        every { taskRepository.tasks } returns listOf(childTask) andThen listOf()
        every { childTask.copy(newChildTaskId, creationDate = date, parentTaskId = repositoryTaskId) } returns newChildTask
        val newRepositoryTask: Task = mockk()
        every { newRepositoryTask.id } returns repositoryTaskId
        every { newRepositoryTask.projectId } returns projectId
        every { taskRepository.saveTask(newChildTask) } returns newRepositoryTask
        justRun { addTaskActivityUseCase(newChildTaskId, CREATE, date) }
        justRun { addProjectActivityUseCase(projectId, TASK_ADDED, date, newTaskId.toString()) }
        justRun { addProjectActivityUseCase(projectId, TASK_ADDED, date, newChildTaskId.toString()) }

        // when
        sut(oldTaskId)

        // then
        verifyOrder {
            addProjectActivityUseCase(projectId, TASK_ADDED, date, newTaskId.toString())
            addProjectActivityUseCase(projectId, TASK_ADDED, date, newChildTaskId.toString())
        }
    }

    @Test
    fun `returns new task`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { oldTask.copy(newTaskId, creationDate = date) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newTaskId) } returns newTask
        val repositoryTask: Task = mockk()
        every { taskRepository.saveTask(newTask) } returns repositoryTask
        every { repositoryTask.projectId } returns null
        justRun { addTaskActivityUseCase(newTaskId, CREATE, date) }
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        val actual = sut(oldTaskId)

        // then
        actual shouldBeEqualTo repositoryTask
    }
}
