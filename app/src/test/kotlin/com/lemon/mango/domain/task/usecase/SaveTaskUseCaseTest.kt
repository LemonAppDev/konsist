package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.activity.model.ProjectActivityType.TASK_ADDED
import com.lemon.mango.domain.activity.model.TaskActivityType.CREATE
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SaveTaskUseCaseTest {
    private val addProjectActivityUseCase: AddProjectActivityUseCase = mockk()
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val taskRepository: TaskRepository = mockk()

    private val sut = SaveTaskUseCase(
        addProjectActivityUseCase,
        addTaskActivityUseCase,
        localDateTimeFactory,
        taskRepository,
    )

    @Test
    fun `saves task when project is null and date is not null`() {
        // given
        val givenTask: Task = mockk()
        val repositoryTask: Task = mockk()
        val repositoryTaskId = getTaskId1()
        every { repositoryTask.id } returns repositoryTaskId
        every { repositoryTask.projectId } returns null
        val date: LocalDateTime = mockk()
        every { taskRepository.saveTask(givenTask) } returns repositoryTask
        every { addTaskActivityUseCase(repositoryTaskId, CREATE, date) } returns mockk()

        // when
        sut(givenTask, date)

        // then
        verify { taskRepository.saveTask(givenTask) }
    }

    @Test
    fun `adds 'task create activity' when project is null and date is not null`() {
        // given
        val givenTask: Task = mockk()
        val repositoryTask: Task = mockk()
        val repositoryTaskId = getTaskId1()
        every { repositoryTask.id } returns repositoryTaskId
        every { repositoryTask.projectId } returns null
        val date: LocalDateTime = mockk()
        every { taskRepository.saveTask(givenTask) } returns repositoryTask
        every { addTaskActivityUseCase(repositoryTaskId, CREATE, date) } returns mockk()

        // when
        sut(givenTask, date)

        // then
        verify { addTaskActivityUseCase(repositoryTaskId, CREATE, date) }
    }

    @Test
    fun `adds 'task create activity' when project and date are null`() {
        // given
        val givenTask: Task = mockk()
        val repositoryTask: Task = mockk()
        val repositoryTaskId = getTaskId1()
        every { repositoryTask.id } returns repositoryTaskId
        every { repositoryTask.projectId } returns null
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { taskRepository.saveTask(givenTask) } returns repositoryTask
        every { addTaskActivityUseCase(repositoryTaskId, CREATE, date) } returns mockk()

        // when
        sut(givenTask)

        // then
        verify { addTaskActivityUseCase(repositoryTaskId, CREATE, date) }
    }

    @Test
    fun `adds 'project task added activity' when project and date are not null`() {
        // given
        val givenTask: Task = mockk()
        val repositoryTask: Task = mockk()
        val repositoryTaskId = getTaskId1()
        every { repositoryTask.id } returns repositoryTaskId
        val projectId = getProjectId1()
        every { repositoryTask.projectId } returns projectId
        val date: LocalDateTime = mockk()
        every { taskRepository.saveTask(givenTask) } returns repositoryTask
        every { addTaskActivityUseCase(repositoryTaskId, CREATE, date) } returns mockk()
        every { addProjectActivityUseCase(projectId, TASK_ADDED, date) } returns mockk()

        // when
        sut(givenTask, date)

        // then
        verify { addProjectActivityUseCase(projectId, TASK_ADDED, date) }
    }
}
