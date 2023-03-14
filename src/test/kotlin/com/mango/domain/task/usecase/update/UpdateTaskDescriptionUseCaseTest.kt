package com.mango.domain.task.usecase.update

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType.UPDATE_DESCRIPTION
import com.mango.domain.common.model.BusinessTestModel.getTask
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskDescriptionUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskActivityFactory: TaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = UpdateTaskDescriptionUseCase(
        taskRepository,
        getTaskOrThrowUseCase,
        taskActivityFactory,
        activityRepository,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldDescription = "old description"
        val newDescription = "new description"
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, description = oldDescription)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(description = newDescription)

        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                UPDATE_DESCRIPTION,
                newDescription,
                oldDescription,
            )
        } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId, newDescription, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to repository`() {
        // given
        val taskId = getTaskId1()
        val oldDescription = "old description"
        val newDescription = "new description"
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, description = oldDescription)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(description = newDescription)

        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                UPDATE_DESCRIPTION,
                newDescription,
                oldDescription,
            )
        } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId, newDescription, date)

        // then
        verify { activityRepository.addTaskActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldDescription = "description"
        val newDescription = "description"
        val date: LocalDateTime = mockk()

        val oldTask = getTask(id = taskId, description = oldDescription)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(description = newDescription)

        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: TaskActivity = mockk()
        every {
            taskActivityFactory(
                taskId,
                date,
                UPDATE_DESCRIPTION,
                newDescription,
                oldDescription,
            )
        } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(taskId, newDescription, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            activityRepository.addTaskActivity(activity)
        }
    }
}
