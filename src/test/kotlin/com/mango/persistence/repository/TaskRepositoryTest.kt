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
        every { task.id } returns TaskId("taskId")
        sut.addTask(task)

        // when
        sut.deleteTask(task)

        // then
        sut.tasks shouldNotContain task
    }

    @Test
    fun `updateTask() adds updated task to tasks`() {
        // given
        val creationDate: LocalDateTime = mockk()
        val oldDueDateDate: LocalDateTime = mockk()
        val newDueDate: LocalDateTime = mockk()
        val oldTargetDateDate: LocalDateTime = mockk()
        val newTargetDate: LocalDateTime = mockk()
        val completeDate: LocalDateTime = mockk()

        val oldTask = Task(
            TaskId("id"),
            "old name",
            UserId("id"),
            creationDate,
            ProjectId("old project id"),
            "old description",
            oldDueDateDate,
            oldTargetDateDate,
            Priority.PRIORITY_1,
            TaskId("old parent task id"),
            UserId("old assignee id"),
        )

        sut.addTask(oldTask)

        val newTask = Task(
            TaskId("id"),
            "new name",
            UserId("id"),
            creationDate,
            ProjectId("new project id"),
            "new description",
            newDueDate,
            newTargetDate,
            Priority.PRIORITY_5,
            TaskId("new parent task id"),
            UserId("new assignee id"),
            completeDate,
        )

        // when
        sut.updateTask(newTask)

        // then
        sut.tasks shouldContain newTask
    }

    @Test
    fun `updateTask() replace old task with new task `() {
        // given
        val creationDate: LocalDateTime = mockk()
        val oldDueDateDate: LocalDateTime = mockk()
        val newDueDate: LocalDateTime = mockk()
        val oldTargetDateDate: LocalDateTime = mockk()
        val newTargetDate: LocalDateTime = mockk()
        val completeDate: LocalDateTime = mockk()

        val oldTask = Task(
            TaskId("id"),
            "old name",
            UserId("id"),
            creationDate,
            ProjectId("old project id"),
            "old description",
            oldDueDateDate,
            oldTargetDateDate,
            Priority.PRIORITY_1,
            TaskId("old parent task id"),
            UserId("old assignee id"),
        )

        sut.addTask(oldTask)

        val newTask = Task(
            TaskId("id"),
            "new name",
            UserId("id"),
            creationDate,
            ProjectId("new project id"),
            "new description",
            newDueDate,
            newTargetDate,
            Priority.PRIORITY_5,
            TaskId("new parent task id"),
            UserId("new assignee id"),
            completeDate,
        )

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
