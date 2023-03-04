package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskParentTaskActivity
import com.mango.business.model.activity.task.UpdateTaskParentTaskActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskParentTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskParentTaskIdActivityFactory: UpdateTaskParentTaskActivityFactory = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()

    private val sut = UpdateTaskParentTaskUseCase(
        taskRepository,
        activityRepository,
        updateTaskParentTaskIdActivityFactory,
        getTaskUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldParentTaskId = TaskId("old parentTaskId")
        val newParentTaskId = TaskId("new parentTaskId")
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, parentTaskId = oldParentTaskId)
        every { getTaskUseCase(taskId) } returns oldTask
        every { getTaskUseCase(newParentTaskId) } returns mockk()
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        val activity: UpdateTaskParentTaskActivity = mockk()
        every { updateTaskParentTaskIdActivityFactory(taskId, date, oldParentTaskId, newParentTaskId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("1")
        val oldParentTaskId = TaskId("old parentTaskId")
        val newParentTaskId = TaskId("new parentTaskId")
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, parentTaskId = oldParentTaskId)
        every { getTaskUseCase(taskId) } returns oldTask
        every { getTaskUseCase(newParentTaskId) } returns mockk()
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskParentTaskActivity = mockk()
        every { updateTaskParentTaskIdActivityFactory(taskId, date, oldParentTaskId, newParentTaskId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
