package com.example.mango.api

import com.example.mango.data.model.Task
import com.example.mango.data.model.User
import com.example.mango.data.repository.TaskRepository
import com.example.mango.data.repository.UserRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

internal class TaskControllerTest {
    private val userRepository = mockk<UserRepository>()
    private val taskRepository = mockk<TaskRepository>()

    private val sut = TaskController(taskRepository, userRepository)

    @Test
    fun `createTask() work with required date`() {
        // given
        val userId = 2
        val name = "name"
        val description = "description"
        val dueDate = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val targetDate = LocalDateTime.of(2022, Month.APRIL, 17, 10, 55)
        val priorityId = 3
        val projectId = 4
        val parentId = 5
        val assigneeId = 6
        val authToken = "authToken"

        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(authToken) } returns user

        justRun {
            taskRepository.createTask(
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
        }

        // when
        sut.createTask(
            name = name,
            description = description,
            dueDate = dueDate,
            targetDate = targetDate,
            priorityId = priorityId,
            projectId = projectId,
            parentTaskId = parentId,
            assigneeId = assigneeId,
            authToken = authToken,
        )

        // then
        verify {
            taskRepository.createTask(
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
        }
    }

    @Test
    fun `deleteTask() work with required date`() {
        // given
        val taskId = 1
        val userId = 2
        val authToken = "authToken"
        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(authToken) } returns user
        justRun { taskRepository.deleteTask(userId, taskId) }

        // when
        sut.deleteTask(taskId, authToken)

        // then
        verify { taskRepository.deleteTask(userId, taskId) }
    }

    @Test
    fun `getAllTasks() return list of tasks`() {
        // given
        val authToken = "authToken"
        val userId = 1
        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(authToken) } returns user
        val tasks = listOf<Task>()
        every { taskRepository.getTasks(userId) } returns tasks

        // when
        val actual = sut.getAllTasks(authToken)

        // then
        actual shouldBeEqualTo tasks
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `updateTask() update task`() {
        // given
        val taskId = 1
        val userId = 2
        val name = "name"
        val description = "description"
        val dueDate = LocalDateTime.of(2022, Month.APRIL, 12, 10, 55)
        val targetDate = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val priorityId = 5
        val projectId = 1
        val parentId = 2
        val assigneeId = 3
        val isCompleted = true
        val authToken = "authToken"

        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(authToken) } returns user

        justRun {
            taskRepository.updateTask(
                userId,
                taskId,
                name,
                description,
                dueDate,
                targetDate,
                priorityId,
                projectId,
                parentId,
                assigneeId,
                isCompleted,
            )
        }

        // when
        sut.updateTask(
            taskId,
            name,
            description,
            dueDate,
            targetDate,
            priorityId,
            projectId,
            parentId,
            assigneeId,
            isCompleted,
            authToken,
        )

        // then
        verify {
            taskRepository.updateTask(
                userId,
                taskId,
                name,
                description,
                dueDate,
                targetDate,
                priorityId,
                projectId,
                parentId,
                assigneeId,
                isCompleted,
            )
        }
    }

    @Test
    fun `markAsComplete() calls taskRepository completeTask() method`() {
        // given
        val taskId = 1
        val userId = 2
        val authToken = "authToken"

        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(authToken) } returns user
        justRun { taskRepository.completeTask(userId, taskId) }

        // when
        sut.markAsComplete(taskId, authToken)

        // then
        verify { taskRepository.completeTask(userId, taskId) }
    }

    @Test
    fun `markAsComplete() do nothing when task doesn't exist`() {
        // given
        val taskId = 1
        val userId = 2
        val authToken = "authToken"

        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(authToken) } returns user
        justRun { taskRepository.completeTask(userId, taskId) }

        // when
        sut.markAsComplete(taskId, authToken)

        // then
        verify { taskRepository.completeTask(userId, taskId) }
    }

    @Test
    fun `copyTask() calls taskRepository copyTask() method`() {
        // given
        val authToken = "authToken"
        val userId = 1
        val taskId = 0

        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(authToken) } returns user
        every { taskRepository.copyTask(userId, taskId) } returns mockk()

        // when
        sut.copyTask(taskId, authToken)

        // then
        verify { taskRepository.copyTask(userId, taskId) }
    }
}
