package com.example.mango.data.model

import com.example.mango.data.model.activity.TaskActivityItem
import java.time.LocalDateTime

data class Task(
    val id: Int,
    val creator: User,
    val creationDate: LocalDateTime? = null,
    var name: String,
    var description: String? = null,
    var dueDate: LocalDateTime? = null,
    var targetDate: LocalDateTime? = null,
    var priority: Priority? = null,
    var projectId: Int?,
    var parentTaskId: Int? = null,
    var assigneeId: Int? = null,
    var completeDate: LocalDateTime? = null,
) {
    private val subtasks = mutableListOf<Int>()
    private val logs: MutableList<TaskActivityItem> = mutableListOf()

    val allSubtasks get() = subtasks.toList()
    val allLogs get() = logs.toList()

    fun addSubtask(subtaskId: Int) {
        subtasks.add(subtaskId)
    }

    fun addLog(log: TaskActivityItem) {
        logs.add(log)
    }
}
