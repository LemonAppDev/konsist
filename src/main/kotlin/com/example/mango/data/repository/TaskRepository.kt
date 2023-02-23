package com.example.mango.data.repository

import com.example.mango.data.factory.LocalDateTimeFactory
import com.example.mango.data.factory.TaskActivityItemFactory
import com.example.mango.data.model.Priority
import com.example.mango.data.model.Task
import com.example.mango.ext.isCompleted
import com.example.mango.ext.markAsComplete
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

@Suppress("detekt.TooManyFunctions")
@Repository
class TaskRepository(
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val taskActivityItemFactory: TaskActivityItemFactory,
    private val userRepository: UserRepository,
) {
    private val counterService = AtomicLong()
    private val tasks = mutableListOf<Task>()

    fun getNewTaskId() = counterService.incrementAndGet().toInt()

    fun getTask(taskId: Int): Task {
        val task = tasks.firstOrNull {
            it.id == taskId
        }

        requireNotNull(task) { "Task with id: $taskId doesn't exist" }
        return task
    }

    fun getTasks(userId: Int) = tasks.filter { it.creator.id == userId }

    fun getAllTasks() = tasks.toList()

    @Suppress("detekt.LongParameterList")
    fun createTask(
        userId: Int,
        name: String,
        description: String? = null,
        dueDate: LocalDateTime? = null,
        targetDate: LocalDateTime? = null,
        priorityId: Int? = null,
        projectId: Int? = null,
        parentTaskId: Int? = null,
        assigneeId: Int? = null,
        completeDate: LocalDateTime? = null,
    ) {
        val taskId = getNewTaskId()
        val user = userRepository.getUser(userId)
        val date = localDateTimeFactory.create()

        if (parentTaskId != null) {
            setParentTask(user.id, parentTaskId, taskId)
        }

        val task = Task(
            id = taskId,
            creator = user,
            creationDate = date,
            name = name,
            description = description,
            dueDate = dueDate,
            targetDate = targetDate,
            priority = Priority.getByValue(priorityId),
            projectId = projectId,
            parentTaskId = parentTaskId,
            assigneeId = assigneeId,
            completeDate = completeDate,
        )

        val log = taskActivityItemFactory.createCreateTaskLog(userId, date)

        task.addLog(log)
        tasks.add(task)
    }

    fun deleteTask(userId: Int, taskId: Int) {
        require(tasks.all { it.id == taskId }) { "Task with id: $taskId doesn't exist" }

        val userTasks = getTasks(userId)

        if (userTasks.any { it.id == taskId }) {
            tasks.removeIf {
                it.id == taskId
            }
        }
    }

    private fun updateTaskName(
        task: Task,
        userId: Int,
        date: LocalDateTime,
        name: String,
    ) {
        if (task.name != name) {
            val log = taskActivityItemFactory.createUpdateTaskNameLog(
                userId,
                date,
                task.name,
                name,
            )
            task.addLog(log)
            task.name = name
        }
    }

    private fun updateTaskDescription(
        task: Task,
        userId: Int,
        date: LocalDateTime,
        description: String,
    ) {
        if (task.description != description) {
            val log = taskActivityItemFactory.createUpdateTaskDescriptionLog(
                userId,
                date,
                task.description,
                description,
            )
            task.addLog(log)
            task.description = description
        }
    }

    private fun updateTaskDueData(
        task: Task,
        userId: Int,
        date: LocalDateTime,
        dueDate: LocalDateTime,
    ) {
        if (task.dueDate != dueDate) {
            val log = taskActivityItemFactory.createUpdateTaskDueDateLog(
                userId,
                date,
                task.dueDate,
                dueDate,
            )
            task.addLog(log)
            task.dueDate = dueDate
        }
    }

    private fun updateTaskTargetDate(
        task: Task,
        userId: Int,
        date: LocalDateTime,
        targetDate: LocalDateTime,
    ) {
        if (task.targetDate != targetDate) {
            val log = taskActivityItemFactory.createUpdateTaskTargetDateLog(
                userId,
                date,
                task.targetDate,
                targetDate,
            )
            task.addLog(log)
            task.targetDate = targetDate
        }
    }

    private fun updateTaskPriority(
        task: Task,
        userId: Int,
        date: LocalDateTime,
        priorityId: Int,
    ) {
        val priority = Priority.getByValue(priorityId)

        if (task.priority != priority) {
            val log = taskActivityItemFactory.createUpdateTaskPriorityLog(
                userId,
                date,
                task.priority,
                priority,
            )
            task.addLog(log)
            task.priority = priority
        }
    }

    private fun updateTaskProjectId(
        task: Task,
        userId: Int,
        date: LocalDateTime,
        projectId: Int,
    ) {
        if (task.projectId != projectId) {
            val log = taskActivityItemFactory.createUpdateTaskProjectIdLog(
                userId,
                date,
                task.projectId,
                projectId,
            )
            task.addLog(log)
            task.projectId = projectId
        }
    }

    private fun updateTaskParentId(
        task: Task,
        userId: Int,
        date: LocalDateTime,
        parentTaskId: Int,
    ) {
        if (task.parentTaskId != parentTaskId) {
            val log = taskActivityItemFactory.createUpdateTaskParentIdLog(
                userId,
                date,
                task.parentTaskId,
                parentTaskId,
            )
            task.addLog(log)
            task.parentTaskId = parentTaskId
        }
    }

    private fun updateTaskAssigneeId(
        task: Task,
        userId: Int,
        date: LocalDateTime,
        assigneeId: Int,
    ) {
        if (task.assigneeId != assigneeId) {
            val log = taskActivityItemFactory.createUpdateTaskAssigneeIdLog(
                userId,
                date,
                task.assigneeId,
                assigneeId,
            )
            task.addLog(log)
            task.assigneeId = assigneeId
        }
    }

    private fun markTaskAsCompleted(
        task: Task,
        userId: Int,
        date: LocalDateTime,
    ) {
        if (!task.isCompleted) {
            val log = taskActivityItemFactory.createCompleteTaskLog(
                userId,
                date,
            )
            task.addLog(log)
            task.markAsComplete(localDateTimeFactory)
        }
    }

    @Suppress("detekt.LongParameterList")
    fun updateTask(
        userId: Int,
        taskId: Int,
        name: String? = null,
        description: String? = null,
        dueDate: LocalDateTime? = null,
        targetDate: LocalDateTime? = null,
        priorityId: Int? = null,
        projectId: Int? = null,
        parentTaskId: Int? = null,
        assigneeId: Int? = null,
        isCompleted: Boolean = false,
    ) {
        val userTasks = getTasks(userId)

        val task = requireNotNull(
            userTasks.firstOrNull { it.id == taskId },
        ) { "Task not found, taskId: $taskId" }

        val date = localDateTimeFactory.create()

        name?.let { updateTaskName(task, userId, date, it) }
        description?.let { updateTaskDescription(task, userId, date, it) }
        dueDate?.let { updateTaskDueData(task, userId, date, it) }
        targetDate?.let { updateTaskTargetDate(task, userId, date, it) }
        priorityId?.let { updateTaskPriority(task, userId, date, it) }
        projectId?.let { updateTaskProjectId(task, userId, date, it) }
        parentTaskId?.let { updateTaskParentId(task, userId, date, it) }
        assigneeId?.let { updateTaskAssigneeId(task, userId, date, it) }
        if (isCompleted) {
            markTaskAsCompleted(task, userId, date)
        }
    }

    fun completeTask(userId: Int, taskId: Int) {
        val userTasks = getTasks(userId)

        val task = userTasks.firstOrNull { it.id == taskId }

        require(task != null) { "Task with id: $taskId doesn't exist" }

        task.markAsComplete(localDateTimeFactory)
    }

    fun copyTask(userId: Int, taskId: Int): Task {
        val newTaskId = getNewTaskId()

        val task = getTasks(userId)
            .firstOrNull { it.id == taskId }

        require(task != null) { "Task with id: $taskId doesn't exist" }

        val newTask = task.copy(id = newTaskId)

        tasks.add(newTask)

        return newTask
    }

    fun setParentTask(userId: Int, parentTaskId: Int?, taskId: Int) {
        val tasks = getTasks(userId)

        val parentTask = tasks.firstOrNull { it.id == parentTaskId }

        requireNotNull(parentTask) { "Incorrect parentTaskId $parentTaskId" }

        parentTask.addChild(taskId)
    }
}
