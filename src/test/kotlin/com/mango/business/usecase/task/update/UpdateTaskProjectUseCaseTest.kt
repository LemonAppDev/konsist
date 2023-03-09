package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.common.model.BusinessTestModel.getProjectId1
import com.mango.business.common.model.BusinessTestModel.getProjectId2
import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.model.activity.task.UpdateTaskProjectActivity
import com.mango.business.model.activity.task.UpdateTaskProjectActivityFactory
import com.mango.business.usecase.project.GetProjectOrThrowUseCase
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskProjectUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskProjectActivityFactory: UpdateTaskProjectActivityFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase = mockk()

    private val sut = UpdateTaskProjectUseCase(
        taskRepository,
        activityRepository,
        updateTaskProjectActivityFactory,
        getTaskOrThrowUseCase,
        getProjectOrThrowUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: UpdateTaskProjectActivity = mockk()
        every { updateTaskProjectActivityFactory(taskId, date, oldProjectId, newProjectId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId2()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.saveTask(newTask) } returns mockk()

        val activity: UpdateTaskProjectActivity = mockk()
        every { updateTaskProjectActivityFactory(taskId, date, oldProjectId, newProjectId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val taskId = getTaskId1()
        val oldProjectId = getProjectId1()
        val newProjectId = getProjectId1()
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: UpdateTaskProjectActivity = mockk()
        every { updateTaskProjectActivityFactory(taskId, date, oldProjectId, newProjectId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.saveTask(newTask)
        }
    }
}
