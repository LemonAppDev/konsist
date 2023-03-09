package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.model.activity.task.UpdateTaskDescriptionActivity
import com.mango.business.model.activity.task.UpdateTaskDescriptionActivityFactory
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskDescriptionUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskDescriptionActivityFactory: UpdateTaskDescriptionActivityFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = UpdateTaskDescriptionUseCase(
        taskRepository,
        activityRepository,
        updateTaskDescriptionActivityFactory,
        getTaskOrThrowUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldDescription = "old description"
        val newDescription = "new description"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, description = oldDescription)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(description = newDescription)

        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: UpdateTaskDescriptionActivity = mockk()
        every { updateTaskDescriptionActivityFactory(taskId, date, oldDescription, newDescription) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDescription, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = getTaskId1()
        val oldDescription = "old description"
        val newDescription = "new description"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, description = oldDescription)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(description = newDescription)

        every { taskRepository.saveTask(newTask) } returns mockk()
        every { updateTaskDescriptionActivityFactory(taskId, date, oldDescription, newDescription) } returns mockk()

        val activity: UpdateTaskDescriptionActivity = mockk()
        every { updateTaskDescriptionActivityFactory(taskId, date, oldDescription, newDescription) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDescription, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldDescription = "description"
        val newDescription = "description"
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, description = oldDescription)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(description = newDescription)

        every { taskRepository.saveTask(newTask) } returns mockk()
        every { updateTaskDescriptionActivityFactory(taskId, date, oldDescription, newDescription) } returns mockk()

        val activity: UpdateTaskDescriptionActivity = mockk()
        every { updateTaskDescriptionActivityFactory(taskId, date, oldDescription, newDescription) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newDescription, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.saveTask(newTask)
        }
    }
}
