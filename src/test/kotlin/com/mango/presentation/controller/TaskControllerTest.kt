package com.mango.presentation.controller

import com.mango.business.model.request.CreateTaskRequestModel
import com.mango.business.model.request.UpdateTaskRequestModel
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.CreateTaskUseCase
import com.mango.business.usecase.task.DeleteTaskUseCase
import com.mango.business.usecase.task.DuplicateTaskUseCase
import com.mango.business.usecase.task.GetAllTasksUseCase
import com.mango.business.usecase.task.GetTaskActivitiesUseCase
import com.mango.business.usecase.task.update.UpdateTaskUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class TaskControllerTest {
    private val createTaskUseCase: CreateTaskUseCase = mockk()
    private val deleteTaskUseCase: DeleteTaskUseCase = mockk()
    private val getAllTasksUseCase: GetAllTasksUseCase = mockk()
    private val updateTaskUseCase: UpdateTaskUseCase = mockk()
    private val getTaskActivitiesUseCase: GetTaskActivitiesUseCase = mockk()
    private val duplicateTaskUseCase: DuplicateTaskUseCase = mockk()

    private val sut = TaskController(
        createTaskUseCase,
        deleteTaskUseCase,
        getAllTasksUseCase,
        updateTaskUseCase,
        duplicateTaskUseCase,
        getTaskActivitiesUseCase,
    )

    @Test
    fun `createTask() calls createTaskUseCase() method`() {
        // given
        val createTaskRequestModel: CreateTaskRequestModel = mockk()
        every { createTaskUseCase(createTaskRequestModel) } returns mockk()

        // when
        sut.createTask(createTaskRequestModel)

        // then
        verify { createTaskUseCase(createTaskRequestModel) }
    }

    @Test
    fun `deleteTask() calls deleteTaskUseCase() method`() {
        // given
        val taskId = TaskId("id")
        justRun { deleteTaskUseCase(taskId) }

        // when
        sut.deleteTask(taskId)

        // then
        verify { deleteTaskUseCase(taskId) }
    }

    @Test
    fun `getAllTasks() calls getAllTasksUseCase() method`() {
        // given
        every { getAllTasksUseCase() } returns mockk()

        // when
        sut.getAllTasks()

        // then
        verify { getAllTasksUseCase() }
    }

    @Test
    fun `updateTask() calls updateTaskUseCase() method`() {
        // given
        val updateTaskRequestModel: UpdateTaskRequestModel = mockk()
        justRun { updateTaskUseCase(updateTaskRequestModel) }

        // when
        sut.updateTask(updateTaskRequestModel)

        // then
        verify { updateTaskUseCase(updateTaskRequestModel) }
    }

    @Test
    fun `duplicateTask() calls duplicateTaskUseCase() method`() {
        // given
        val taskId = TaskId("id")
        every { duplicateTaskUseCase(taskId) } returns mockk()

        // when
        sut.duplicateTask(taskId)

        // then
        verify { duplicateTaskUseCase(taskId) }
    }

    @Test
    fun `getTaskActivities() calls getTaskActivityUseCase() method`() {
        // given
        val taskId = TaskId("id")
        every { getTaskActivitiesUseCase(taskId) } returns mockk()

        // when
        sut.getTaskActivity(taskId)

        // then
        verify { getTaskActivitiesUseCase(taskId) }
    }
}
