package com.mango.business.usecase.task.update

import com.mango.business.common.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskNameActivity
import com.mango.business.model.activity.task.UpdateTaskNameActivityFactory
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

class UpdateTaskNameUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskNameActivityFactory: UpdateTaskNameActivityFactory = mockk()

    private val sut = UpdateTaskNameUseCase(
        taskRepository,
        activityRepository,
        updateTaskNameActivityFactory,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = TaskId("id")
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(taskId, "newName", mockk()) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldName = "oldName"
        val newName = "newName"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, name = oldName)
        val newTask = oldTask.copy(name = newName)
        every { taskRepository.getTask(taskId) } returns oldTask

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
        val newTask = oldTask.copy(name = newName)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }

        val activity: UpdateTaskNameActivity = mockk()
        every { updateTaskNameActivityFactory(taskId, date, oldName, newName) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newName, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
