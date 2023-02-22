package com.example.mango.data.model

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
    var parentId: Int? = null,
    var assigneeId: Int? = null,
    var completeDate: LocalDateTime? = null,
) {
    private val subtasks = mutableListOf<Int>()

    val allSubtasks get() = subtasks.toList()

    fun addSubtask(subtaskId: Int) {
        subtasks.add(subtaskId)
    }

    fun removeSubtask(subtaskId: Int) {
        subtasks.remove(subtaskId)
    }
}
