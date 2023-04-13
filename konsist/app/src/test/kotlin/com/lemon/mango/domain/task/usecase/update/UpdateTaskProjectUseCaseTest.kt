package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.data.task.TaskRepositoryImpl
import com.lemon.mango.domain.activity.model.ProjectActivityType.TASK_ADDED
import com.lemon.mango.domain.activity.model.ProjectActivityType.TASK_MOVED
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_PROJECT
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId2
import com.lemon.mango.domain.common.model.BusinessTestModel.getTask
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId3
import com.lemon.mango.domain.project.usecase.GetProjectOrThrowUseCase
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskProjectUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = UpdateTaskProjectUseCase(
        addProjectActivityUseCase,
        addTaskActivityUseCase,
        getProjectOrThrowUseCase,
        getTaskOrThrowUseCase,
        taskRepository,
    )

    @Test
    fun `add task with changed project id`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.tasks } returns mockk(relaxed = true)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PROJECT,
                date,
                newProjectId.value.toString(),
                oldProjectId.value.toString(),
            )
        }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `adds child tasks with changed project id`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.tasks } returns mockk(relaxed = true)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val task1: Task = mockk()
        val id1 = getTaskId2()
        every { task1.id } returns id1
        every { task1.parentTaskId } returns taskId
        every { task1.projectId } returns oldProjectId
        val task2: Task = mockk()
        val id2 = getTaskId3()
        every { task2.id } returns id2
        every { task2.parentTaskId } returns taskId
        every { task2.projectId } returns oldProjectId
        every { taskRepository.tasks } returns listOf(task1, task2)
        every { task1.copy(projectId = newProjectId) } returns task1
        every { taskRepository.saveTask(task1) } returns mockk()
        every { task2.copy(projectId = newProjectId) } returns task2
        every { taskRepository.saveTask(task2) } returns mockk()
        justRun {
            addTaskActivityUseCase(taskId, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun {
            addTaskActivityUseCase(id1, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun {
            addTaskActivityUseCase(id2, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, id1.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, id1.toString()) }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, id2.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, id2.toString()) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verifyOrder {
            taskRepository.saveTask(task1)
            taskRepository.saveTask(task2)
        }
    }

    @Test
    fun `add 'project task_added activity'`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.tasks } returns mockk(relaxed = true)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PROJECT,
                date,
                newProjectId.value.toString(),
                oldProjectId.value.toString(),
            )
        }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
    }

    @Test
    fun `add 'project task_moved activity'`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.tasks } returns mockk(relaxed = true)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PROJECT,
                date,
                newProjectId.value.toString(),
                oldProjectId.value.toString(),
            )
        }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }
    }

    @Test
    fun `add 'project task_added activity' two times when task has a child task`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.tasks } returns mockk(relaxed = true)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val task1: Task = mockk()
        val id1 = getTaskId2()
        every { task1.id } returns id1
        every { task1.parentTaskId } returns taskId
        every { task1.projectId } returns oldProjectId
        every { taskRepository.tasks } returns listOf(task1)
        every { task1.copy(projectId = newProjectId) } returns task1
        every { taskRepository.saveTask(task1) } returns mockk()
        justRun {
            addTaskActivityUseCase(taskId, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun {
            addTaskActivityUseCase(id1, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, id1.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, id1.toString()) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verifyOrder {
            addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString())
            addProjectActivityUseCase(newProjectId, TASK_ADDED, date, id1.toString())
        }
    }

    @Test
    fun `add 'project task_moved activity' two times when task has a child task`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.tasks } returns mockk(relaxed = true)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val task1: Task = mockk()
        val id1 = getTaskId2()
        every { task1.id } returns id1
        every { task1.parentTaskId } returns taskId
        every { task1.projectId } returns oldProjectId
        every { taskRepository.tasks } returns listOf(task1)
        every { task1.copy(projectId = newProjectId) } returns task1
        every { taskRepository.saveTask(task1) } returns mockk()
        justRun {
            addTaskActivityUseCase(taskId, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun {
            addTaskActivityUseCase(id1, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, id1.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, id1.toString()) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verifyOrder {
            addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString())
            addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, id1.toString())
        }
    }

    @Test
    fun `add 'task update activity'`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.tasks } returns mockk(relaxed = true)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PROJECT,
                date,
                newProjectId.value.toString(),
                oldProjectId.value.toString(),
            )
        }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify { addTaskActivityUseCase(taskId, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString()) }
    }

    @Test
    fun `add two 'task update activities' when task has a child task`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.tasks } returns mockk(relaxed = true)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val task1: Task = mockk()
        val id1 = getTaskId2()
        every { task1.id } returns id1
        every { task1.parentTaskId } returns taskId
        every { task1.projectId } returns oldProjectId
        every { taskRepository.tasks } returns listOf(task1)
        every { task1.copy(projectId = newProjectId) } returns task1
        every { taskRepository.saveTask(task1) } returns mockk()
        justRun {
            addTaskActivityUseCase(taskId, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun {
            addTaskActivityUseCase(id1, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, taskId.toString()) }
        justRun { addProjectActivityUseCase(newProjectId, TASK_ADDED, date, id1.toString()) }
        justRun { addProjectActivityUseCase(oldProjectId, TASK_MOVED, date, id1.toString()) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verifyOrder {
            addTaskActivityUseCase(taskId, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
            addTaskActivityUseCase(id1, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId1()
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)

        // when
        sut(taskId, newProjectId, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            addTaskActivityUseCase(taskId, UPDATE_PROJECT, date, newProjectId.value.toString(), oldProjectId.value.toString())
        }
    }
}
