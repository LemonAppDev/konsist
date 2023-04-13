package com.lemon.mango.domain.task.usecase

import com.lemon.mango.data.task.TaskRepositoryImpl
import com.lemon.mango.domain.activity.model.ProjectActivityType.TASK_REMOVED
import com.lemon.mango.domain.activity.model.TaskActivityType.DELETE
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.lemon.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteTaskUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = DeleteTaskUseCase(
        addProjectActivityUseCase,
        addTaskActivityUseCase,
        localDateTimeFactory,
        taskRepository,
    )

    @Test
    fun `deletes task`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns null
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        every { taskRepository.tasks } returns mockk(relaxed = true)
        justRun { taskRepository.deleteTask(task) }
        every { addTaskActivityUseCase(taskId, DELETE, date) } returns mockk()

        // when
        sut(taskId)

        // then
        verify { taskRepository.deleteTask(task) }
    }

    @Test
    fun `adds 'task delete activity'`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns null
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        every { taskRepository.tasks } returns mockk(relaxed = true)
        justRun { taskRepository.deleteTask(task) }
        every { addTaskActivityUseCase(taskId, DELETE, date) } returns mockk()

        // when
        sut(taskId)

        // then
        verify { addTaskActivityUseCase(taskId, DELETE, date) }
    }

    @Test
    fun `deletes task and its child tasks`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns null
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        every { addTaskActivityUseCase(taskId, DELETE, date) } returns mockk()
        val childTask1: Task = mockk()
        val id1 = getTaskId2()
        every { childTask1.id } returns id1
        every { childTask1.projectId } returns null
        every { childTask1.parentTaskId } returns taskId
        val childTask2: Task = mockk()
        val id2 = getTaskId2()
        every { childTask2.id } returns id2
        every { childTask2.projectId } returns null
        every { childTask2.parentTaskId } returns taskId
        every { taskRepository.tasks } returns listOf(childTask1, childTask2) andThen listOf()
        justRun { taskRepository.deleteTask(childTask1) }
        every { addTaskActivityUseCase(id1, DELETE, date) } returns mockk()
        justRun { taskRepository.deleteTask(childTask2) }
        every { addTaskActivityUseCase(id2, DELETE, date) } returns mockk()

        // when
        sut(taskId)

        // then
        verifyOrder {
            taskRepository.deleteTask(task)
            taskRepository.deleteTask(childTask1)
            taskRepository.deleteTask(childTask2)
        }
    }

    @Test
    fun `adds 'task delete activity' for task and its child tasks`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns null
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        every { addTaskActivityUseCase(taskId, DELETE, date) } returns mockk()
        val childTask1: Task = mockk()
        val id1 = getTaskId2()
        every { childTask1.id } returns id1
        every { childTask1.projectId } returns null
        every { childTask1.parentTaskId } returns taskId
        val childTask2: Task = mockk()
        val id2 = getTaskId2()
        every { childTask2.id } returns id2
        every { childTask2.projectId } returns null
        every { childTask2.parentTaskId } returns taskId
        every { taskRepository.tasks } returns listOf(childTask1, childTask2) andThen listOf()
        justRun { taskRepository.deleteTask(childTask1) }
        every { addTaskActivityUseCase(id1, DELETE, date) } returns mockk()
        justRun { taskRepository.deleteTask(childTask2) }
        every { addTaskActivityUseCase(id2, DELETE, date) } returns mockk()

        // when
        sut(taskId)

        // then
        verifyOrder {
            addTaskActivityUseCase(taskId, DELETE, date)
            addTaskActivityUseCase(id1, DELETE, date)
            addTaskActivityUseCase(id2, DELETE, date)
        }
    }

    @Test
    fun `adds 'project task removed activity' when project is not null`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        val projectId = getProjectId1()
        every { task.projectId } returns projectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        every { taskRepository.tasks } returns mockk(relaxed = true)
        justRun { taskRepository.deleteTask(task) }
        every { addTaskActivityUseCase(taskId, DELETE, date) } returns mockk()
        every { addProjectActivityUseCase(projectId, TASK_REMOVED, date, taskId.toString()) } returns mockk()

        // when
        sut(taskId)

        // then
        verify { addProjectActivityUseCase(projectId, TASK_REMOVED, date, taskId.toString()) }
    }

    @Test
    fun `adds 'project task removed activity' for task and its child tasks when project is not null`() {
        // given
        val taskId = getTaskId1()
        val projectId = getProjectId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns projectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        every { addTaskActivityUseCase(taskId, DELETE, date) } returns mockk()
        every { addProjectActivityUseCase(projectId, TASK_REMOVED, date, taskId.toString()) } returns mockk()
        val childTask1: Task = mockk()
        val id1 = getTaskId2()
        every { childTask1.id } returns id1
        every { childTask1.projectId } returns projectId
        every { childTask1.parentTaskId } returns taskId
        val childTask2: Task = mockk()
        val id2 = getTaskId2()
        every { childTask2.id } returns id2
        every { childTask2.projectId } returns projectId
        every { childTask2.parentTaskId } returns taskId
        every { taskRepository.tasks } returns listOf(childTask1, childTask2) andThen listOf()
        justRun { taskRepository.deleteTask(childTask1) }
        every { addTaskActivityUseCase(id1, DELETE, date) } returns mockk()
        every { addProjectActivityUseCase(projectId, TASK_REMOVED, date, id1.toString()) } returns mockk()
        justRun { taskRepository.deleteTask(childTask2) }
        every { addTaskActivityUseCase(id2, DELETE, date) } returns mockk()
        every { addProjectActivityUseCase(projectId, TASK_REMOVED, date, id2.toString()) } returns mockk()

        // when
        sut(taskId)

        // then
        verifyOrder {
            addProjectActivityUseCase(projectId, TASK_REMOVED, date, taskId.toString())
            addProjectActivityUseCase(projectId, TASK_REMOVED, date, id1.toString())
            addProjectActivityUseCase(projectId, TASK_REMOVED, date, id2.toString())
        }
    }
}
