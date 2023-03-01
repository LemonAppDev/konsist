package com.mango.business.usecase.task

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.factory.UUIDFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivity
import com.mango.business.model.activity.task.CreateTaskActivityFactory
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

class DuplicateTaskUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val uuidFactory: UUIDFactory = mockk()
    private val createTaskActivityFactory: CreateTaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()

    private val sut = DuplicateTaskUseCase(
        taskRepository,
        uuidFactory,
        createTaskActivityFactory,
        activityRepository,
        localDateTimeFactory,
    )

    @Test
    fun `throws exception when task doesn't exist`() {
        // given
        val taskId = TaskId("id")
        every { taskRepository.getTask(taskId) } returns null
        every { taskRepository.tasks } returns listOf()

        // when
        val actual = { sut(taskId) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `add task to repository`() {
        // given
        val oldId = TaskId("oldId")
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldId
        every { taskRepository.getTask(oldId) } returns oldTask
        val newId = TaskId("newId")
        every { uuidFactory.createTaskId() } returns newId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { newTask.id } returns newId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newId, creationDate = date) } returns newTask
        justRun { taskRepository.addTask(newTask) }
        every { createTaskActivityFactory(newId, date) } returns mockk()
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(oldId)

        // then
        verify { taskRepository.addTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val oldId = TaskId("oldId")
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldId
        every { taskRepository.getTask(oldId) } returns oldTask
        val newId = TaskId("newId")
        every { uuidFactory.createTaskId() } returns newId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { oldTask.copy(newId, creationDate = date) } returns newTask
        every { newTask.id } returns newId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newId) } returns newTask
        justRun { taskRepository.addTask(newTask) }
        val createTaskActivity: CreateTaskActivity = mockk()
        every { createTaskActivityFactory(newId, date) } returns createTaskActivity
        justRun { activityRepository.addActivity(any()) }

        // when
        sut(oldId)

        // then
        verify { activityRepository.addActivity(createTaskActivity) }
    }
}
