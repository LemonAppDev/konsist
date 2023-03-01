package com.mango.persistence.repository

import com.mango.business.model.Task
import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Repository

@Repository
class TaskRepository {
    private val _tasks = mutableListOf<Task>()
    val tasks get() = _tasks.toList()
    fun getTask(taskId: TaskId) = _tasks.firstOrNull { it.id == taskId }

    fun addTask(task: Task) {
        _tasks.add(task)
    }

    fun deleteTask(task: Task) {
        _tasks.remove(task)
    }

    fun updateTask(task: Task) {
        require(containsTask(task.id)) { "Task not found, taskId: ${task.id}" }
        _tasks.removeIf { it.id == task.id }
        _tasks.add(task)
    }

    fun containsTask(parentTaskId: TaskId) = getTask(parentTaskId) != null
}
