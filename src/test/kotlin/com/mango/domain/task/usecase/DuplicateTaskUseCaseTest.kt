package com.mango.domain.task.usecase

import com.mango.data.task.TaskRepositoryImpl
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivity
import com.mango.domain.activity.model.TaskActivityType.CREATE
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DuplicateTaskUseCaseTest {
    private val taskRepository: TaskRepositoryImpl = mockk()
    private val uuidFactory: UUIDFactory = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskActivityFactory: TaskActivityFactory = mockk()
    private val activityRepository: ActivityRepository = mockk()

    private val sut = DuplicateTaskUseCase(
        taskRepository,
        uuidFactory,
        localDateTimeFactory,
        getTaskOrThrowUseCase,
        taskActivityFactory,
        activityRepository,
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
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(newTaskId, date, CREATE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(oldTaskId)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `adds activity to repository`() {
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
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(newTaskId, date, CREATE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        sut(oldTaskId)

        // then
        verify { activityRepository.addTaskActivity(activity) }
    }

    @Test
    fun `returns new task`() {
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
        val activity: TaskActivity = mockk()
        every { taskActivityFactory(newTaskId, date, CREATE) } returns activity
        every { activityRepository.addTaskActivity(activity) } returns mockk()

        // when
        val actual = sut(oldTaskId)

        // then
        actual shouldBeEqualTo newTask
    }
}
