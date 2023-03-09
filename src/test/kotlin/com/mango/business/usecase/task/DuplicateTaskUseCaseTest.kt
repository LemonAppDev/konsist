package com.mango.business.usecase.task

import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.common.model.BusinessTestModel.getTaskId2
import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.factory.UUIDFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivity
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DuplicateTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val uuidFactory: UUIDFactory = mockk()
    private val createTaskActivityFactory: CreateTaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()

    private val sut = DuplicateTaskUseCase(
        taskRepository,
        uuidFactory,
        createTaskActivityFactory,
        activityRepository,
        localDateTimeFactory,
        getTaskOrThrowUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newTaskId, creationDate = date) } returns newTask
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(newTaskId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(oldTaskId)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { oldTask.copy(newTaskId, creationDate = date) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newTaskId) } returns newTask
        every { taskRepository.saveTask(newTask) } returns mockk()
        val activity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(newTaskId, date) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(oldTaskId)

        // then
        verify { activityRepository.addActivity(activity) }
    }
}
