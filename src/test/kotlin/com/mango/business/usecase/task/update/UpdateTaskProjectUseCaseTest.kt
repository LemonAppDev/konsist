package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskProjectActivity
import com.mango.business.model.activity.task.UpdateTaskProjectActivityFactory
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
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
        val taskId = TaskId("1")
        val oldProjectId = ProjectId("old projectId")
        val newProjectId = ProjectId("new projectId")
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        justRun { taskRepository.updateTask(newTask) }
        val activity: UpdateTaskProjectActivity = mockk()
        every { updateTaskProjectActivityFactory(taskId, date, oldProjectId, newProjectId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val taskId = TaskId("1")
        val oldProjectId = ProjectId("old projectId")
        val newProjectId = ProjectId("new projectId")
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        justRun { taskRepository.updateTask(newTask) }

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
        val taskId = TaskId("1")
        val oldProjectId = ProjectId("projectId")
        val newProjectId = ProjectId("projectId")
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, projectId = oldProjectId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        every { getProjectOrThrowUseCase(newProjectId) } returns mockk()
        val newTask = oldTask.copy(projectId = newProjectId)
        justRun { taskRepository.updateTask(newTask) }
        val activity: UpdateTaskProjectActivity = mockk()
        every { updateTaskProjectActivityFactory(taskId, date, oldProjectId, newProjectId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newProjectId, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.updateTask(newTask)
        }
    }
}
