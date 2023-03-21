package com.lemon.mango.domain.task

import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId

interface TaskRepository {
    val tasks: List<Task>
    fun saveTask(task: Task): Task
    fun getTask(taskId: TaskId): Task?
    fun deleteTask(task: Task)
    fun containsTask(taskId: TaskId): Boolean
}
