package com.example.mango.data.repository

import com.example.mango.data.factory.LocalDateTimeFactory
import com.example.mango.data.model.Priority
import com.example.mango.data.model.Task
import com.example.mango.data.model.User
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

internal class TaskRepositoryTest {
    private val localDateTimeFactory = mockk<LocalDateTimeFactory>()
    private val userRepository = mockk<UserRepository>()

    private val sut = TaskRepository(
        localDateTimeFactory,
        userRepository,
    )

    @Suppress("detekt.LongParameterList")
    private fun givenTaskIsAdded(
        taskId: Int = 1,
        userId: Int = 2,
        name: String = "name",
        description: String? = "description",
        dueDate: LocalDateTime? = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55),
        targetDate: LocalDateTime? = LocalDateTime.of(2022, Month.APRIL, 17, 10, 55),
        priorityId: Int? = 3,
        projectId: Int? = 4,
        parentId: Int? = null,
        assigneeId: Int? = 6,
        completeDate: LocalDateTime? = null,
    ): Task {
        val creationDate = null

        val user = mockk<User>()
        every { user.id } returns userId

        every { userRepository.getUser(userId) } returns user

        sut.addTask(
            userId,
            name,
            description,
            dueDate,
            targetDate,
            priorityId,
            projectId,
            parentId,
            assigneeId,
        )

        return Task(
            taskId,
            user,
            creationDate,
            name,
            description,
            dueDate,
            targetDate,
            Priority.getByValue(priorityId),
            projectId,
            parentId,
            assigneeId,
            completeDate,
        )
    }

    @Test
    fun `addTask() add new task to tasks`() {
        // given
        val taskId = 1
        val userId = 2
        val creationDate = null
        val name = "name"
        val description = "description"
        val dueDate = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val targetDate = LocalDateTime.of(2022, Month.APRIL, 17, 10, 55)
        val priorityId = 3
        val projectId = 4
        val parentId = null
        val assigneeId = 6

        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(userId) } returns user

        val task = Task(
            taskId,
            user,
            creationDate,
            name,
            description,
            dueDate,
            targetDate,
            Priority.getByValue(priorityId),
            projectId,
            parentId,
            assigneeId,
        )

        // when
        sut.addTask(
            userId,
            name,
            description,
            dueDate,
            targetDate,
            priorityId,
            projectId,
            parentId,
            assigneeId,
        )

        // then
        sut.getAllTasks() shouldContain task
    }

    @Test
    fun `deleteTask() delete task from tasks`() {
        // given
        val taskId = 1
        val userId = 2
        val task = givenTaskIsAdded(taskId = taskId, userId = userId)

        // when
        sut.deleteTask(userId, taskId)

        // then
        sut.getAllTasks() shouldNotContain task
    }

    @Test
    fun `deleteTask() throw exception when task doesn't exist`() {
        // given
        val taskId = 2
        val userId = 3
        givenTaskIsAdded(taskId = 1, userId = userId)

        // when
        val actual = { sut.deleteTask(userId, taskId) }

        // then
        actual shouldThrow Exception::class withMessage ("Task with id: $taskId doesn't exist")
    }

    @Test
    fun `updateTask() update task name`() {
        // given
        val oldName = "old name"
        val updName = "new name"
        val taskId = 1
        val userId = 2

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            name = oldName,
        )

        // when
        sut.updateTask(userId = userId, taskId = taskId, name = updName)

        // then
        val updatedTask = sut.getTasks(userId).first { it.id == taskId }
        updatedTask.name shouldBeEqualTo updName
    }

    @Test
    fun `updateTask() update task project`() {
        // given
        val taskId = 1
        val userId = 2
        val firstProjectId = 3
        val updProjectId = 4

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            projectId = firstProjectId,
        )

        // when
        sut.updateTask(userId = userId, taskId = taskId, projectId = updProjectId)

        // then
        val updatedTask = sut.getTasks(userId).first { it.id == taskId }
        updatedTask.projectId shouldBeEqualTo updProjectId
    }

    @Test
    fun `updateTask() throw exception when task doesn't exist`() {
        // given
        val userId = 0
        val incorrectTaskId = 1
        val taskId = 2

        givenTaskIsAdded(taskId = incorrectTaskId, userId = userId)

        // when
        val actual = { sut.updateTask(userId = userId, taskId = taskId) }

        // then
        actual shouldThrow Exception::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `getTask() return task when it exist`() {
        // given
        val taskId = 1

        val task = givenTaskIsAdded(taskId = taskId)

        // when
        val actual = sut.getTask(taskId)

        // then
        actual shouldBeEqualTo task
    }

    @Test
    fun `getTask() throw exception when task doesn't exist`() {
        // given
        val taskId = 1

        // when
        val actual = { sut.getTask(taskId) }

        // then
        actual shouldThrow Exception::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `getTasks() return creators tasks`() {
        // given
        val creatorId = 1

        val creator = mockk<User>()
        every { creator.id } returns creatorId

        val task1 = givenTaskIsAdded(userId = creatorId, taskId = 1)
        val task2 = givenTaskIsAdded(userId = creatorId, taskId = 2)

        // when
        val actual = sut.getTasks(creatorId)

        // then
        actual shouldBeEqualTo listOf(task1, task2)
    }

    @Test
    fun `completeTask() mark task as complete`() {
        // given
        val taskId = 1
        val completeDate = LocalDateTime.of(2022, Month.APRIL, 12, 10, 55)

        val task = givenTaskIsAdded(taskId = taskId, completeDate = completeDate)

        every { localDateTimeFactory.create() } returns completeDate

        // when
        sut.completeTask(task.creator.id, taskId)

        // then
        task.completeDate shouldBeEqualTo completeDate
    }

    @Test
    fun `completeTask() throw exception when task doesn't exist`() {
        // given
        val userId = 0
        val incorrectTaskId = 1
        val taskId = 2

        givenTaskIsAdded(userId = userId, taskId = incorrectTaskId)

        // when
        val actual = { sut.completeTask(userId, taskId) }

        // then
        actual shouldThrow Exception::class withMessage "Task with id: $taskId doesn't exist"
    }

    @Test
    fun `copyTask() add new task to tasks`() {
        // given
        val userId = 0
        val firstTaskId = 1
        val secondTaskId = 2

        val user = mockk<User>()
        every { user.id } returns userId

        val firstTask = givenTaskIsAdded(taskId = firstTaskId, userId = userId)

        val secondTask = Task(
            secondTaskId,
            firstTask.creator,
            firstTask.creationDate,
            firstTask.name,
            firstTask.description,
            firstTask.dueDate,
            firstTask.targetDate,
            firstTask.priority,
            firstTask.projectId,
            firstTask.parentId,
            firstTask.assigneeId,
            firstTask.completeDate,
        )

        // when
        sut.copyTask(userId, firstTaskId)

        // then
        sut.getAllTasks() shouldContain secondTask
    }

    @Test
    fun `copyTask() return duplicated task`() {
        // given
        val userId = 0
        val firstTaskId = 1
        val secondTaskId = 2

        val user = mockk<User>()
        every { user.id } returns userId

        val firstTask = givenTaskIsAdded(taskId = firstTaskId, userId = userId)

        val secondTask = Task(
            secondTaskId,
            firstTask.creator,
            firstTask.creationDate,
            firstTask.name,
            firstTask.description,
            firstTask.dueDate,
            firstTask.targetDate,
            firstTask.priority,
            firstTask.projectId,
            firstTask.parentId,
            firstTask.assigneeId,
            firstTask.completeDate,
        )

        // when
        val actual = sut.copyTask(userId, firstTaskId)

        // then
        actual shouldBeEqualTo secondTask
    }

    @Test
    fun `copyTask() throw exception when parentId is incorrect`() {
        // given
        val userId = 0
        val firstTaskId = 1
        val incorrectId = 2

        val user = mockk<User>()
        every { user.id } returns userId

        givenTaskIsAdded(taskId = firstTaskId, userId = userId)

        // when
        val actual = { sut.copyTask(userId, incorrectId) }

        // then
        actual shouldThrow Exception::class withMessage "Task with id: $incorrectId doesn't exist"
    }

    @Test
    fun `setParentTask() throw exception when parentTask doesn't exist`() {
        // given
        val userId = 1
        val parentId = 2
        val taskId = 3

        givenTaskIsAdded(taskId = taskId, userId = userId)

        // when
        val actual = { sut.setParentTask(userId, parentId, taskId) }

        // then
        actual shouldThrow Exception::class withMessage "Incorrect parentId $parentId"
    }
}
