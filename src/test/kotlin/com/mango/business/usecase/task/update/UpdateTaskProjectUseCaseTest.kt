package com.mango.business.usecase.task.update

import com.mango.business.common.BusinessTestModel
import com.mango.business.model.Task
import com.mango.business.model.activity.task.UpdateTaskProjectActivity
import com.mango.business.model.activity.task.UpdateTaskProjectActivityFactory
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskProjectUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskProjectActivityFactory: UpdateTaskProjectActivityFactory = mockk()
    private val getTaskUseCase: GetTaskUseCase = mockk()
    private val projectRepository: ProjectRepository = mockk()

    private val sut = UpdateTaskProjectUseCase(
        taskRepository,
        activityRepository,
        updateTaskProjectActivityFactory,
        getTaskUseCase,
        projectRepository,
    )

    @Test
    fun `throws exception when new project doesn't exist`() {
        // given
        val taskId = TaskId("1")
        val newProjectId = ProjectId("new projectId")
        val date: LocalDateTime = mockk()
        every { projectRepository.containsProject(newProjectId) } returns false
        val task: Task = mockk()
        every { getTaskUseCase(taskId) } returns task

        // when
        val actual = { sut(taskId, newProjectId, date) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Project with id: $newProjectId doesn't exist"
    }

    @Test
    fun `add task to repository`() {
        // given
        val taskId = TaskId("1")
        val oldProjectId = ProjectId("old projectId")
        val newProjectId = ProjectId("new projectId")
        val date: LocalDateTime = mockk()

        val oldTask = BusinessTestModel.getTask(id = taskId, projectId = oldProjectId)
        every { getTaskUseCase(taskId) } returns oldTask
        every { projectRepository.containsProject(newProjectId) } returns true
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
        every { getTaskUseCase(taskId) } returns oldTask
        every { projectRepository.containsProject(newProjectId) } returns true
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
}
