package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.common.model.BusinessTestModel
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.common.model.BusinessTestModel.getTaskId3
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskParentTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskParentTaskIdActivityFactory: com.mango.domain.task.activity.UpdateTaskParentTaskActivityFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = UpdateTaskParentTaskUseCase(
        taskRepository,
        activityRepository,
        updateTaskParentTaskIdActivityFactory,
        getTaskOrThrowUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId3()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, parentTaskId = oldParentTaskId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns mockk()
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.getTask(taskId) } returns oldTask

        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: com.mango.domain.task.activity.UpdateTaskParentTaskActivity = mockk()
        every { updateTaskParentTaskIdActivityFactory(taskId, date, oldParentTaskId, newParentTaskId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId3()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, parentTaskId = oldParentTaskId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns mockk()
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val activity: com.mango.domain.task.activity.UpdateTaskParentTaskActivity = mockk()
        every { updateTaskParentTaskIdActivityFactory(taskId, date, oldParentTaskId, newParentTaskId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldParentTaskId = getTaskId2()
        val newParentTaskId = getTaskId2()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, parentTaskId = oldParentTaskId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getTaskOrThrowUseCase(newParentTaskId) } returns mockk()
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val activity: com.mango.domain.task.activity.UpdateTaskParentTaskActivity = mockk()
        every { updateTaskParentTaskIdActivityFactory(taskId, date, oldParentTaskId, newParentTaskId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.saveTask(newTask)
        }
    }
}
