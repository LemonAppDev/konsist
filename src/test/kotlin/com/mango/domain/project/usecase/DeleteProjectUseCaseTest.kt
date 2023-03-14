package com.mango.domain.project.usecase

import com.mango.data.project.ProjectRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.ProjectActivityFactory
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.ProjectActivityType.DELETE
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.project.model.Project
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DeleteProjectUseCaseTest {
    private val projectRepository: ProjectRepositoryImpl = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val projectActivityFactory: ProjectActivityFactory = mockk()
    private val taskActivityFactory: TaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val taskRepository: TaskRepository = mockk()

    private val sut = DeleteProjectUseCase(
        projectRepository,
        localDateTimeFactory,
        taskRepository,
        projectActivityFactory,
        taskActivityFactory,
        activityRepository,
    )

    @Test
    fun `deletes project from repository`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        every { taskRepository.tasks } returns mockk(relaxed = true)
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val activity: ProjectActivity = mockk()
        every { projectActivityFactory(projectId, date, DELETE) } returns activity
        every { activityRepository.addProjectActivity(activity) } returns mockk()

        // when
        sut(projectId)

        // then
        verify { projectRepository.deleteProject(project) }
    }

    @Test
    fun `deletes project tasks from repository`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }

        val task1: Task = mockk()
        val id1 = getTaskId1()
        every { task1.id } returns id1
        every { task1.projectId } returns projectId
        val task2: Task = mockk()
        val id2 = getTaskId2()
        every { task2.id } returns id2
        every { task2.projectId } returns projectId
        every { taskRepository.tasks } returns listOf(task1, task2)
        val date = mockk<LocalDateTime>()
        every { localDateTimeFactory() } returns date
        val projectActivity: ProjectActivity = mockk()
        every { projectActivityFactory(projectId, date, DELETE) } returns projectActivity
        every { activityRepository.addProjectActivity(projectActivity) } returns mockk()
        justRun { taskRepository.deleteTask(task1) }
        justRun { taskRepository.deleteTask(task2) }
        val taskActivity: TaskActivity = mockk()
        every { taskActivityFactory(id1, date, TaskActivityType.DELETE) } returns taskActivity
        every { taskActivityFactory(id2, date, TaskActivityType.DELETE) } returns taskActivity
        every { activityRepository.addTaskActivity(taskActivity) } returns mockk()

        // when
        sut(projectId)

        // then
        verifyOrder {
            taskRepository.deleteTask(task1)
            taskRepository.deleteTask(task2)
        }
    }

    @Test
    fun `adds project activity to repository`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }
        every { taskRepository.tasks } returns mockk(relaxed = true)
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val activity: ProjectActivity = mockk()
        every { projectActivityFactory(projectId, date, DELETE) } returns activity
        every { activityRepository.addProjectActivity(activity) } returns mockk()

        // when
        sut(projectId)

        // then
        verify { activityRepository.addProjectActivity(activity) }
    }

    @Test
    fun `add task activity to repository twice`() {
        // given
        val projectId = getProjectId1()
        val project: Project = mockk()
        every { project.id } returns projectId
        every { projectRepository.getProject(projectId) } returns project
        justRun { projectRepository.deleteProject(project) }

        val task1: Task = mockk()
        val id1 = getTaskId1()
        every { task1.id } returns id1
        every { task1.projectId } returns projectId
        val task2: Task = mockk()
        val id2 = getTaskId2()
        every { task2.id } returns id2
        every { task2.projectId } returns projectId
        every { taskRepository.tasks } returns listOf(task1, task2)
        val date = mockk<LocalDateTime>()
        every { localDateTimeFactory() } returns date
        val projectActivity: ProjectActivity = mockk()
        every { projectActivityFactory(projectId, date, DELETE) } returns projectActivity
        every { activityRepository.addProjectActivity(projectActivity) } returns mockk()
        justRun { taskRepository.deleteTask(task1) }
        justRun { taskRepository.deleteTask(task2) }
        val taskActivity: TaskActivity = mockk()
        every { taskActivityFactory(id1, date, TaskActivityType.DELETE) } returns taskActivity
        every { taskActivityFactory(id2, date, TaskActivityType.DELETE) } returns taskActivity
        every { activityRepository.addTaskActivity(taskActivity) } returns mockk()

        // when
        sut(projectId)

        // then
        verify(exactly = 2) { activityRepository.addTaskActivity(taskActivity) }
    }
}
