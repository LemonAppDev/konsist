package com.mango.business.usecase.task.update

import com.mango.business.common.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskDescriptionActivity
import com.mango.business.model.activity.task.UpdateTaskDescriptionActivityFactory
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

class UpdateTaskDescriptionUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskDescriptionActivityFactory: UpdateTaskDescriptionActivityFactory = mockk()

    private val sut = UpdateTaskDescriptionUseCase(
        taskRepository,
        activityRepository,
        updateTaskDescriptionActivityFactory,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = TaskId("id")
        every { taskRepository.getTask(taskId) } returns null

        // when
        val actual = { sut(taskId, "newDescription", mockk()) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldDescription = "old description"
        val newDescription = "new description"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, description = oldDescription)
        val newTask = oldTask.copy(description = newDescription)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        val activity: UpdateTaskDescriptionActivity = mockk()
        every { updateTaskDescriptionActivityFactory(taskId, date, oldDescription, newDescription) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDescription, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("1")
        val oldDescription = "old description"
        val newDescription = "new description"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, description = oldDescription)
        val newTask = oldTask.copy(description = newDescription)
        every { taskRepository.getTask(taskId) } returns oldTask

        justRun { taskRepository.updateTask(newTask) }
        every { updateTaskDescriptionActivityFactory(taskId, date, oldDescription, newDescription) } returns mockk()

        val activity: UpdateTaskDescriptionActivity = mockk()
        every { updateTaskDescriptionActivityFactory(taskId, date, oldDescription, newDescription) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDescription, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
