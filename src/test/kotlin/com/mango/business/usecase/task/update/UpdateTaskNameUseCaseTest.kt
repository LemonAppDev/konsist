package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.model.activity.task.UpdateTaskNameActivity
import com.mango.business.model.activity.task.UpdateTaskNameActivityFactory
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
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
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = UpdateTaskNameUseCase(
        taskRepository,
        activityRepository,
        updateTaskNameActivityFactory,
        getTaskOrThrowUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, name = oldName)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)

        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: UpdateTaskNameActivity = mockk()
        every { updateTaskNameActivityFactory(taskId, date, oldName, newName) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newName, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = getTaskId1()
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, name = oldName)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)

        every { taskRepository.saveTask(newTask) } returns mockk()

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
        val taskId = getTaskId1()
        val oldName = "name"
        val newName = "name"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, name = oldName)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(name = newName)

        every { taskRepository.saveTask(newTask) } returns mockk()

        val activity: UpdateTaskNameActivity = mockk()
        every { updateTaskNameActivityFactory(taskId, date, oldName, newName) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newName, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.saveTask(newTask)
        }
    }
}
