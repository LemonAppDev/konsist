package com.example.mango.data.repository

import com.example.mango.data.factory.LocalDateTimeFactory
import com.example.mango.data.factory.TaskActivityItemFactory
import com.example.mango.data.model.Priority
import com.example.mango.data.model.Task
import com.example.mango.data.model.User
import com.example.mango.data.model.activity.CompleteTaskLog
import com.example.mango.data.model.activity.UpdateTaskAssigneeIdLog
import com.example.mango.data.model.activity.UpdateTaskDescriptionLog
import com.example.mango.data.model.activity.UpdateTaskDueDateLog
import com.example.mango.data.model.activity.UpdateTaskNameLog
import com.example.mango.data.model.activity.UpdateTaskParentIdLog
import com.example.mango.data.model.activity.UpdateTaskPriorityLog
import com.example.mango.data.model.activity.UpdateTaskProjectIdLog
import com.example.mango.data.model.activity.UpdateTaskTargetDateLog
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
    private val taskActivityItemFactory = mockk<TaskActivityItemFactory>()
    private val userRepository = mockk<UserRepository>()

    private val sut = TaskRepository(
        localDateTimeFactory,
        taskActivityItemFactory,
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
        parentTaskId: Int? = null,
        assigneeId: Int? = 6,
        completeDate: LocalDateTime? = null,
    ): Task {
        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(userId) } returns user

        val creationDate = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        every { localDateTimeFactory.create() } returns creationDate
        every { taskActivityItemFactory.createCreateTaskLog(userId, creationDate) } returns mockk()

        sut.createTask(
            userId,
            name,
            description,
            dueDate,
            targetDate,
            priorityId,
            projectId,
            parentTaskId,
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
            parentTaskId,
            assigneeId,
            completeDate,
        )
    }

    @Test
    fun `createTask() add new task to tasks`() {
        // given
        val taskId = 1
        val userId = 2
        val name = "name"
        val description = "description"
        val dueDate = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val targetDate = LocalDateTime.of(2022, Month.APRIL, 17, 10, 55)
        val priorityId = 3
        val projectId = 4
        val parentTaskId = null
        val assigneeId = 6

        val user = mockk<User>()
        every { user.id } returns userId
        every { userRepository.getUser(userId) } returns user

        val creationDate = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        every { localDateTimeFactory.create() } returns creationDate
        every { taskActivityItemFactory.createCreateTaskLog(userId, creationDate) } returns mockk()

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
            parentTaskId,
            assigneeId,
        )

        // when
        sut.createTask(
            userId,
            name,
            description,
            dueDate,
            targetDate,
            priorityId,
            projectId,
            parentTaskId,
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

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        every { taskActivityItemFactory.createUpdateTaskNameLog(userId, date, oldName, updName) } returns mockk()

        // when
        sut.updateTask(userId = userId, taskId = taskId, name = updName)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .name
        actual shouldBeEqualTo updName
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

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        every {
            taskActivityItemFactory.createUpdateTaskProjectIdLog(userId, date, firstProjectId, updProjectId)
        } returns mockk()

        // when
        sut.updateTask(userId = userId, taskId = taskId, projectId = updProjectId)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .projectId
        actual shouldBeEqualTo updProjectId
    }

    @Test
    fun `updateTask() add log when name is updated`() {
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

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val updateTaskNameLog = UpdateTaskNameLog(userId, date, oldName, updName)
        every {
            taskActivityItemFactory.createUpdateTaskNameLog(userId, date, oldName, updName)
        } returns updateTaskNameLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, name = updName)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain updateTaskNameLog
    }

    @Test
    fun `updateTask() add log when description is updated`() {
        // given
        val oldDescription = "old description"
        val updDescription = "new description"
        val taskId = 1
        val userId = 2

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            description = oldDescription,
        )

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val updateTaskDescriptionLog = UpdateTaskDescriptionLog(userId, date, oldDescription, updDescription)
        every {
            taskActivityItemFactory.createUpdateTaskDescriptionLog(userId, date, oldDescription, updDescription)
        } returns updateTaskDescriptionLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, description = updDescription)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain updateTaskDescriptionLog
    }

    @Test
    fun `updateTask() add log when dueData is updated`() {
        // given
        val oldDueDate = LocalDateTime.of(2022, Month.APRIL, 14, 10, 55)
        val updDueDate = LocalDateTime.of(2022, Month.APRIL, 20, 10, 55)
        val taskId = 1
        val userId = 2

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            dueDate = oldDueDate,
        )

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)

        val updateTaskDueDateLog = UpdateTaskDueDateLog(userId, oldDueDate, oldDueDate, updDueDate)
        every {
            taskActivityItemFactory.createUpdateTaskDueDateLog(userId, date, oldDueDate, updDueDate)
        } returns updateTaskDueDateLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, dueDate = updDueDate)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain updateTaskDueDateLog
    }

    @Test
    fun `updateTask() add log when targetData is updated`() {
        // given
        val oldTargetDate = LocalDateTime.of(2022, Month.APRIL, 14, 10, 55)
        val updTargetDate = LocalDateTime.of(2022, Month.APRIL, 20, 10, 55)
        val taskId = 1
        val userId = 2

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            targetDate = oldTargetDate,
        )

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)

        val updateTaskTargetDateLog = UpdateTaskTargetDateLog(userId, oldTargetDate, oldTargetDate, updTargetDate)
        every {
            taskActivityItemFactory.createUpdateTaskTargetDateLog(userId, date, oldTargetDate, updTargetDate)
        } returns updateTaskTargetDateLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, targetDate = updTargetDate)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain updateTaskTargetDateLog
    }

    @Test
    fun `updateTask() add log when priority is updated`() {
        // given
        val oldPriorityId = 1
        val oldPriority = Priority.getByValue(oldPriorityId)
        val updPriorityId = 2
        val updPriority = Priority.getByValue(updPriorityId)
        val taskId = 1
        val userId = 2

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            priorityId = oldPriorityId,
        )

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val updateTaskPriorityLog = UpdateTaskPriorityLog(userId, date, oldPriority, updPriority)
        every {
            taskActivityItemFactory.createUpdateTaskPriorityLog(userId, date, oldPriority, updPriority)
        } returns updateTaskPriorityLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, priorityId = updPriorityId)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain updateTaskPriorityLog
    }

    @Test
    fun `updateTask() add log when projectId is updated`() {
        // given
        val oldProjectId = 1
        val updProjectId = 2
        val taskId = 1
        val userId = 2

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            projectId = oldProjectId,
        )

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val updateTaskProjectIdLog = UpdateTaskProjectIdLog(userId, date, oldProjectId, updProjectId)
        every {
            taskActivityItemFactory.createUpdateTaskProjectIdLog(userId, date, oldProjectId, updProjectId)
        } returns updateTaskProjectIdLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, projectId = updProjectId)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain updateTaskProjectIdLog
    }

    @Test
    fun `updateTask() add log when parentTaskId is updated`() {
        // given
        val oldParentTaskId = null
        val updParentTaskId = 1
        val taskId = 1
        val userId = 2

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            parentTaskId = oldParentTaskId,
        )

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val updateTaskParentIdLog = UpdateTaskParentIdLog(userId, date, oldParentTaskId, updParentTaskId)
        every {
            taskActivityItemFactory.createUpdateTaskParentIdLog(userId, date, oldParentTaskId, updParentTaskId)
        } returns updateTaskParentIdLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, parentTaskId = updParentTaskId)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain updateTaskParentIdLog
    }

    @Test
    fun `updateTask() add log when assigneeId is updated`() {
        // given
        val oldAssigneeId = null
        val updAssigneeId = 1
        val taskId = 1
        val userId = 2

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            assigneeId = oldAssigneeId,
        )

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val updateTaskAssigneeIdLog = UpdateTaskAssigneeIdLog(userId, date, oldAssigneeId, updAssigneeId)
        every {
            taskActivityItemFactory.createUpdateTaskAssigneeIdLog(userId, date, oldAssigneeId, updAssigneeId)
        } returns updateTaskAssigneeIdLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, assigneeId = updAssigneeId)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain updateTaskAssigneeIdLog
    }

    @Test
    fun `updateTask() add log when task is marked as complete`() {
        // given
        val isCompleted = true
        val taskId = 1
        val userId = 2
        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            completeDate = date,
        )

        val completeTaskLog = CompleteTaskLog(userId, date)
        every {
            taskActivityItemFactory.createCompleteTaskLog(userId, date)
        } returns completeTaskLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, isCompleted = isCompleted)

        // then
        val actual = sut.getTasks(userId)
            .first { it.id == taskId }
            .allLogs
        actual shouldContain completeTaskLog
    }

    @Test
    fun `updateTask() add two logs when name and project are updated`() {
        // given
        val oldName = "old name"
        val updName = "new name"
        val taskId = 1
        val userId = 2
        val firstProjectId = 3
        val updProjectId = 4

        givenTaskIsAdded(
            taskId = taskId,
            userId = userId,
            name = oldName,
            projectId = firstProjectId,
        )

        val date = LocalDateTime.of(2022, Month.APRIL, 16, 10, 55)
        val updateTaskNameLog = UpdateTaskNameLog(userId, date, oldName, updName)
        every {
            taskActivityItemFactory.createUpdateTaskNameLog(userId, date, oldName, updName)
        } returns updateTaskNameLog
        val updateTaskProjectIdLog = UpdateTaskProjectIdLog(userId, date, firstProjectId, updProjectId)
        every {
            taskActivityItemFactory.createUpdateTaskProjectIdLog(userId, date, firstProjectId, updProjectId)
        } returns updateTaskProjectIdLog

        // when
        sut.updateTask(userId = userId, taskId = taskId, name = updName, projectId = updProjectId)

        // then
        val actual = sut.getTasks(userId).first { it.id == taskId }.allLogs
        actual shouldContain updateTaskNameLog shouldContain updateTaskProjectIdLog
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
        actual shouldThrow Exception::class withMessage "Task not found, taskId: $taskId"
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
            firstTask.parentTaskId,
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
            firstTask.parentTaskId,
            firstTask.assigneeId,
            firstTask.completeDate,
        )

        // when
        val actual = sut.copyTask(userId, firstTaskId)

        // then
        actual shouldBeEqualTo secondTask
    }

    @Test
    fun `copyTask() throw exception when parentTaskId is incorrect`() {
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
        val parentTaskId = 2
        val taskId = 3

        givenTaskIsAdded(taskId = taskId, userId = userId)

        // when
        val actual = { sut.setParentTask(userId, parentTaskId, taskId) }

        // then
        actual shouldThrow Exception::class withMessage "Incorrect parentTaskId $parentTaskId"
    }
}
