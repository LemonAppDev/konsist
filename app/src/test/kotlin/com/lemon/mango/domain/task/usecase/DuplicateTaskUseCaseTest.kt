package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UUIDFactory
import com.lemon.mango.domain.common.model.BusinessTestModel.getProjectId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId2
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId3
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId4
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId5
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DuplicateTaskUseCaseTest {
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()
    private val saveTaskUseCase: SaveTaskUseCase = mockk()
    private val taskRepository: TaskRepository = mockk()
    private val userRepository: UserRepository = mockk()
    private val uuidFactory: UUIDFactory = mockk()

    private val sut = DuplicateTaskUseCase(
        getTaskOrThrowUseCase,
        localDateTimeFactory,
        saveTaskUseCase,
        taskRepository,
        userRepository,
        uuidFactory,
    )

    @Test
    fun `calls 'saveTaskUseCase()`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { oldTask.projectId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldTask.copy(newTaskId, creationDate = date, ownerId = userId) } returns newTask
        every { saveTaskUseCase(newTask, date) } returns mockk()
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        sut(oldTaskId)

        // then
        verify { saveTaskUseCase(newTask, date) }
    }

    @Test
    fun `add duplicated child task`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { oldTask.projectId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        val newChildTaskId = getTaskId3()
        every { uuidFactory.createTaskId() } returns newTaskId andThen newChildTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldTask.copy(newTaskId, creationDate = date, ownerId = userId) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        val repositoryTask: Task = mockk()
        val repositoryTaskId = getTaskId4()
        every { repositoryTask.id } returns repositoryTaskId
        every { repositoryTask.projectId } returns null
        every { saveTaskUseCase(newTask, date) } returns repositoryTask

        val childTask: Task = mockk()
        val childTaskId = getTaskId5()
        val newChildTask: Task = mockk()
        every { newChildTask.id } returns newChildTaskId
        every { newChildTask.creationDate } returns date
        every { childTask.id } returns childTaskId
        every { childTask.parentTaskId } returns oldTaskId
        every { taskRepository.tasks } returns listOf(childTask) andThen listOf()
        every {
            childTask.copy(
                newChildTaskId,
                creationDate = date,
                parentTaskId = repositoryTaskId,
                ownerId = userId,
            )
        } returns newChildTask
        every { saveTaskUseCase(newChildTask, date) } returns mockk()

        // when
        sut(oldTaskId)

        // then
        verifyOrder {
            saveTaskUseCase(newTask, date)
            saveTaskUseCase(newChildTask, date)
        }
    }

    @Test
    fun `returns new task when project id is null`() {
        // given
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { oldTask.projectId } returns null
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldTask.copy(newTaskId, creationDate = date, ownerId = userId) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        every { oldTask.copy(newTaskId) } returns newTask
        val expected: Task = mockk()
        every { saveTaskUseCase(newTask, date) } returns expected
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        val actual = sut(oldTaskId)

        // then
        actual shouldBeEqualTo expected
    }

    @Test
    fun `returns new task when project id is not null`() {
        // given
        val projectId = getProjectId1()
        val oldTaskId = getTaskId1()
        val oldTask: Task = mockk()
        every { oldTask.id } returns oldTaskId
        every { oldTask.parentTaskId } returns null
        every { oldTask.projectId } returns projectId
        every { getTaskOrThrowUseCase(oldTaskId) } returns oldTask
        val newTaskId = getTaskId2()
        every { uuidFactory.createTaskId() } returns newTaskId
        val newTask: Task = mockk()
        val date: LocalDateTime = mockk()
        every { localDateTimeFactory() } returns date
        val userId = getUserId1()
        every { userRepository.getCurrentUser().id } returns userId
        every { oldTask.copy(newTaskId, creationDate = date, ownerId = userId) } returns newTask
        every { newTask.id } returns newTaskId
        every { newTask.creationDate } returns date
        every {
            oldTask.copy(
                id = newTaskId,
                creationDate = date,
                parentTaskId = null,
                projectId = projectId,
                ownerId = userId,
            )
        } returns newTask
        val expected: Task = mockk()
        every { saveTaskUseCase(newTask, date) } returns expected
        every { taskRepository.tasks } returns mockk(relaxed = true)

        // when
        val actual = sut(oldTaskId, projectId)

        // then
        actual shouldBeEqualTo expected
    }
}
