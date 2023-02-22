package com.example.mango.data.repository

import com.example.mango.data.factory.LocalDateTimeFactory
import com.example.mango.data.model.Priority
import com.example.mango.data.model.Task
import com.example.mango.ext.markAsComplete
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

@Repository
class TaskRepository(
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
) {
    private val counterService = AtomicLong()
    private val tasks = mutableListOf<Task>()

    fun getNewTaskId() = counterService.incrementAndGet().toInt()

    fun getTask(taskId: Int): Task {
        val task = tasks.firstOrNull {
            it.id == taskId
        }

        require(task != null) { "Task with id: $taskId doesn't exist" }

        return task
    }

    fun getTasks(userId: Int) = tasks.filter { it.creator.id == userId }

    fun getAllTasks() = tasks.toList()

    @Suppress("detekt.LongParameterList")
    fun addTask(
        userId: Int,
        name: String,
        description: String? = null,
        dueDate: LocalDateTime? = null,
        targetDate: LocalDateTime? = null,
        priorityId: Int? = null,
        projectId: Int? = null,
        parentId: Int? = null,
        assigneeId: Int? = null,
        completeDate: LocalDateTime? = null,
    ) {
        val taskId = getNewTaskId()
        val user = userRepository.getUser(userId)

        if (parentId != null) {
            setParentTask(user.id, parentId, taskId)
        }

        val task = Task(
            id = taskId,
            creator = user,
            name = name,
            description = description,
            dueDate = dueDate,
            targetDate = targetDate,
            priority = Priority.getByValue(priorityId),
            projectId = projectId,
            parentId = parentId,
            assigneeId = assigneeId,
            completeDate = completeDate,
        )

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
        parentId: Int? = null,
        assigneeId: Int? = null,
        completeDate: LocalDateTime? = null,
    ) {
        val userTasks = getTasks(userId)

        val task = userTasks.firstOrNull { it.id == taskId }

        require(task != null) { "Task with id: $taskId doesn't exist" }

        name?.let { task.name = it }

        description?.let { task.description = it }

        dueDate?.let { task.dueDate = it }

        targetDate?.let { task.targetDate = it }

        priorityId?.let { task.priority = Priority.getByValue(priorityId) }

        projectId?.let { task.projectId = projectId }

        parentId?.let {
            setParentTask(task.creator.id, parentId, taskId)
            task.parentId = parentId
        }

        assigneeId?.let { task.assigneeId = assigneeId }

        completeDate?.let { task.completeDate = completeDate }
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

        requireNotNull(parentTask) { "Incorrect parentId $parentTaskId" }

        parentTask.addSubtask(taskId)
    }
}
