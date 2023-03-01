package com.mango.persistence.repository

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
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

class TaskRepositoryTest {
    private val userRepository: UserRepository = mockk()

    private val sut = TaskRepository()

    @Test
    fun `addTask() add task to tasks`() {
        // given
        val task: Task = mockk()

        // when
        sut.addTask(task)

        // then
        sut.tasks shouldContain task
    }

    @Test
    fun `deleteTask() delete task from tasks`() {
        // given
        val task: Task = mockk()
        sut.addTask(task)

        // when
        sut.deleteTask(task)

        // then
        sut.tasks shouldNotContain task
    }

    @Test
    fun `updateTask() adds updated task to tasks`() {
        // given
        val oldTask: Task = mockk()
        every { oldTask.id } returns TaskId("id")
        every { oldTask.name } returns "old name"
        every { oldTask.projectId } returns ProjectId("old project id")
        every { oldTask.description } returns "old description"
        every { oldTask.dueDate } returns mockk()
        every { oldTask.targetDate } returns mockk()
        every { oldTask.priority } returns Priority.PRIORITY_1
        every { oldTask.parentTaskId } returns TaskId("old parent task id")
        every { oldTask.assigneeId } returns UserId("old assignee id")
        every { oldTask.completeDate } returns null

        sut.addTask(oldTask)

        val newTask: Task = mockk()
        every { newTask.id } returns TaskId("id")
        every { oldTask.name } returns "new name"
        every { newTask.projectId } returns ProjectId("new project id")
        every { newTask.description } returns "new description"
        every { newTask.dueDate } returns mockk()
        every { newTask.targetDate } returns mockk()
        every { newTask.priority } returns Priority.PRIORITY_5
        every { newTask.parentTaskId } returns TaskId("new parent task id")
        every { newTask.assigneeId } returns UserId("new assignee id")
        val date = LocalDateTime.of(2023, Month.JUNE, 14, 15, 23)
        every { newTask.completeDate } returns date

        // when
        sut.updateTask(newTask)

        // then
        sut.tasks shouldContain newTask
    }

    @Test
    fun `updateTask() replace old task with new task `() {
        // given
        val oldTask: Task = mockk()
        every { oldTask.id } returns TaskId("id")
        every { oldTask.name } returns "old name"
        every { oldTask.projectId } returns ProjectId("old project id")
        every { oldTask.description } returns "old description"
        every { oldTask.dueDate } returns mockk()
        every { oldTask.targetDate } returns mockk()
        every { oldTask.priority } returns Priority.PRIORITY_1
        every { oldTask.parentTaskId } returns TaskId("old parent task id")
        every { oldTask.assigneeId } returns UserId("old assignee id")
        every { oldTask.completeDate } returns null

        sut.addTask(oldTask)

        val newTask: Task = mockk()
        every { newTask.id } returns TaskId("id")
        every { oldTask.name } returns "new name"
        every { newTask.projectId } returns ProjectId("new project id")
        every { newTask.description } returns "new description"
        every { newTask.dueDate } returns mockk()
        every { newTask.targetDate } returns mockk()
        every { newTask.priority } returns Priority.PRIORITY_5
        every { newTask.parentTaskId } returns TaskId("new parent task id")
        every { newTask.assigneeId } returns UserId("new assignee id")
        val date = LocalDateTime.of(2023, Month.JUNE, 14, 15, 23)
        every { newTask.completeDate } returns date

        // when
        sut.updateTask(newTask)

        // then
        sut.tasks shouldNotContain oldTask
    }

    @Test
    fun `updateTask() throws exception when task doesn't exist`() {
        // given
        val task: Task = mockk()
        every { task.id } returns TaskId("id")

        // when
        val actual = { sut.updateTask(task) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Task not found, taskId: ${task.id}"
    }

    @Test
    fun `getTask() return task when it exist`() {
        // given
        val taskId = TaskId("id")
        val task: Task = mockk()
        every { task.id } returns taskId
        sut.addTask(task)

        // when
        val actual = sut.getTask(taskId)

        // then
        actual shouldBeEqualTo task
    }

    @Test
    fun `getTask() return null when it doesn't exist`() {
        // given
        val taskId = TaskId("id")
        val task: Task = mockk()
        every { task.id } returns taskId

        // when
        val actual = sut.getTask(taskId)

        // then
        actual shouldBeEqualTo null
    }

    @Test
    fun `getTasks() return owners tasks`() {
        // given
        val ownerId = UserId("id")
        every { userRepository.getCurrentUser().id } returns ownerId
        val task1: Task = mockk()
        every { task1.ownerId } returns ownerId
        val task2: Task = mockk()
        every { task2.ownerId } returns ownerId

        sut.apply {
            addTask(task1)
            addTask(task2)
        }

        // when
        val actual = sut.tasks

        // then
        actual shouldBeEqualTo listOf(task1, task2)
    }

    @Test
    fun `containsTask() return true when task exist`() {
        // given
        val parentTaskId = TaskId("id")
        val task: Task = mockk()
        every { task.id } returns parentTaskId
        sut.addTask(task)

        // when
        val actual = sut.containsTask(parentTaskId)

        // then
        actual shouldBeEqualTo true
    }

    @Test
    fun `containsTask() return false when task doesn't exist`() {
        // given
        val parentTaskId = TaskId("id")

        // when
        val actual = sut.containsTask(parentTaskId)

        // then
        actual shouldBeEqualTo false
    }
}
