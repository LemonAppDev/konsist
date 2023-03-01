package com.mango.business.usecase.task.update

import com.mango.business.common.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskParentTaskActivity
import com.mango.business.model.activity.task.UpdateTaskParentTaskActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskParentTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskParentTaskIdActivityFactory: UpdateTaskParentTaskActivityFactory = mockk()

    private val sut = UpdateTaskParentTaskUseCase(
        taskRepository,
        activityRepository,
        updateTaskParentTaskIdActivityFactory,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = TaskId("id")
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(taskId, taskId, mockk()) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldParentTaskId = TaskId("old parentTaskId")
        val newParentTaskId = TaskId("new parentTaskId")
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, parentTaskId = oldParentTaskId)
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        justRun { activityRepository.addActivity(any()) }
        every { updateTaskParentTaskIdActivityFactory(taskId, date, oldParentTaskId, newParentTaskId) } returns mockk()

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
        val newTask = oldTask.copy(parentTaskId = newParentTaskId)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        justRun { activityRepository.addActivity(any()) }

        val activity: UpdateTaskParentTaskActivity = mockk()
        every { updateTaskParentTaskIdActivityFactory(taskId, date, oldParentTaskId, newParentTaskId) } returns activity

        // when
        sut(taskId, newParentTaskId, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
