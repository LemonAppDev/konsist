package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.model.TaskActivityType.UPDATE_PARENT_TASK
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getProjectId2
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getTaskId3
import com.mango.domain.task.model.Task
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskParentTaskUseCaseTest {
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = UpdateTaskParentTaskUseCase(
        addTaskActivityUseCase,
        getTaskOrThrowUseCase,
        taskRepository,
    )

    @Test
    fun `throws exception when task and new parent task are not in the same project`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId3()
        val newParentTask: Task = mockk()
        val parentTaskProjectId = getProjectId1()
        every { newParentTask.projectId } returns parentTaskProjectId
        val taskProjectId = getProjectId2()
        val date: LocalDateTime = mockk()
        val oldTask = getTask(id = taskId, parentTaskId = oldParentTaskId, projectId = taskProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns newParentTask

        // when
        val actual = { sut(taskId, newParentTaskId, date) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task and parent task are not in the same project"
    }

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId3()
        val newParentTask: Task = mockk()
        val projectId = getProjectId1()
        every { newParentTask.projectId } returns projectId
        val date: LocalDateTime = mockk()
        val oldTask = getTask(id = taskId, parentTaskId = oldParentTaskId, projectId = projectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns newParentTask
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.getTask(taskId) } returns oldTask
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PARENT_TASK,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
            )
        }

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to repository`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId3()
        val newParentTask: Task = mockk()
        val projectId = getProjectId1()
        every { newParentTask.projectId } returns projectId
        val date: LocalDateTime = mockk()
        val oldTask = getTask(id = taskId, parentTaskId = oldParentTaskId, projectId = projectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns newParentTask
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PARENT_TASK,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
            )
        }

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PARENT_TASK,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
            )
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId2()
        val newParentTask: Task = mockk()
        val projectId = getProjectId1()
        every { newParentTask.projectId } returns projectId
        val date: LocalDateTime = mockk()
        val oldTask = getTask(id = taskId, parentTaskId = oldParentTaskId, projectId = projectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns newParentTask
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_PARENT_TASK,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
            )
        }

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            addTaskActivityUseCase(
                taskId,
                UPDATE_PARENT_TASK,
                date,
                newParentTaskId.value.toString(),
                oldParentTaskId.value.toString(),
            )
        }
    }
}
