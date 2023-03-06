package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskNameActivity
import com.mango.business.model.activity.task.UpdateTaskNameActivityFactory
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

class UpdateTaskNameUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskNameActivityFactory: UpdateTaskNameActivityFactory = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()

    private val sut = UpdateTaskNameUseCase(
        taskRepository,
        activityRepository,
        updateTaskNameActivityFactory,
        getTaskUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, name = oldName)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)

        justRun { taskRepository.updateTask(newTask) }
        val activity: UpdateTaskNameActivity = mockk()
        every { updateTaskNameActivityFactory(taskId, date, oldName, newName) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newName, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("1")
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, name = oldName)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)

        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskNameActivity = mockk()
        every { updateTaskNameActivityFactory(taskId, date, oldName, newName) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newName, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = TaskId("1")
        val oldName = "name"
        val newName = "name"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, name = oldName)
        every { getTaskUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)

        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskNameActivity = mockk()
        every { updateTaskNameActivityFactory(taskId, date, oldName, newName) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newName, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.updateTask(newTask)
        }
    }
}
