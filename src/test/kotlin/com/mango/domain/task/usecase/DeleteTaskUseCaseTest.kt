package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.model.ProjectActivityType.TASK_REMOVED
import com.mango.domain.activity.model.TaskActivityType.DELETE
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteTaskUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()

    private val sut = DeleteTaskUseCase(
        taskRepository,
        localDateTimeFactory,
        addTaskActivityUseCase,
        addProjectActivityUseCase,
    )

    @Test
    fun `deletes task from repository`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns null
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        every { taskRepository.tasks } returns mockk(relaxed = true)
        justRun { addTaskActivityUseCase(taskId, DELETE, date) }

        // when
        sut(taskId)

        // then
        verify { taskRepository.deleteTask(task) }
    }

    @Test
    fun `deletes child tasks from repository`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns null
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
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
        every { taskRepository.tasks } returns listOf(childTask1, childTask2)
        justRun { taskRepository.deleteTask(childTask1) }
        justRun { taskRepository.deleteTask(childTask2) }
        justRun { addTaskActivityUseCase(taskId, DELETE, date) }
        justRun { addTaskActivityUseCase(id1, DELETE, date) }
        justRun { addTaskActivityUseCase(id2, DELETE, date) }

        // when
        sut(taskId)

        // then
        verifyOrder {
            taskRepository.deleteTask(childTask1)
            taskRepository.deleteTask(childTask2)
        }
    }

    @Test
    fun `adds activity for the task and its child tasks`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns null
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
        val childTask1: Task = mockk()
        val id1 = getTaskId2()
        every { childTask1.id } returns id1
        every { childTask1.parentTaskId } returns taskId
        every { childTask1.projectId } returns null
        val childTask2: Task = mockk()
        val id2 = getTaskId2()
        every { childTask2.id } returns id2
        every { childTask2.parentTaskId } returns taskId
        every { childTask2.projectId } returns null
        every { taskRepository.tasks } returns listOf(childTask1, childTask2)
        justRun { taskRepository.deleteTask(childTask1) }
        justRun { taskRepository.deleteTask(childTask2) }
        justRun { addTaskActivityUseCase(taskId, DELETE, date) }
        justRun { addTaskActivityUseCase(id1, DELETE, date) }
        justRun { addTaskActivityUseCase(id2, DELETE, date) }

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
    fun `adds 'project task removed' activity for the task and its child tasks`() {
        // given
        val projectId = getProjectId1()
        val taskId = getTaskId1()
        val task: Task = mockk()
        every { task.id } returns taskId
        every { task.projectId } returns projectId
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.getTask(taskId) } returns task
        justRun { taskRepository.deleteTask(task) }
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
        every { taskRepository.tasks } returns listOf(childTask1, childTask2)
        justRun { taskRepository.deleteTask(childTask1) }
        justRun { taskRepository.deleteTask(childTask2) }
        justRun { addTaskActivityUseCase(taskId, DELETE, date) }
        justRun { addTaskActivityUseCase(id1, DELETE, date) }
        justRun { addTaskActivityUseCase(id2, DELETE, date) }
        justRun { addProjectActivityUseCase(projectId, TASK_REMOVED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(projectId, TASK_REMOVED, date, id1.toString()) }
        justRun { addProjectActivityUseCase(projectId, TASK_REMOVED, date, id2.toString()) }

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
