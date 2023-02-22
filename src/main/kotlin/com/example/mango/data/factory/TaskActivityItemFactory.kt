package com.example.mango.data.factory

import com.example.mango.data.model.Priority
import com.example.mango.data.model.activity.CompleteTaskLog
import com.example.mango.data.model.activity.CreateTaskLog
import com.example.mango.data.model.activity.UpdateTaskAssigneeIdLog
import com.example.mango.data.model.activity.UpdateTaskDescriptionLog
import com.example.mango.data.model.activity.UpdateTaskDueDateLog
import com.example.mango.data.model.activity.UpdateTaskNameLog
import com.example.mango.data.model.activity.UpdateTaskParentIdLog
import com.example.mango.data.model.activity.UpdateTaskPriorityLog
import com.example.mango.data.model.activity.UpdateTaskProjectIdLog
import com.example.mango.data.model.activity.UpdateTaskTargetDateLog
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TaskActivityItemFactory {
    fun createUpdateTaskAssigneeIdLog(
        userId: Int,
        date: LocalDateTime,
        oldValue: Int?,
        newValue: Int,
    ) = UpdateTaskAssigneeIdLog(userId, date, oldValue, newValue)

    fun createCompleteTaskLog(
        userId: Int,
        date: LocalDateTime,
    ) = CompleteTaskLog(userId, date)

    fun createUpdateTaskDescriptionLog(
        userId: Int,
        date: LocalDateTime,
        oldValue: String?,
        newValue: String,
    ) = UpdateTaskDescriptionLog(userId, date, oldValue, newValue)

    fun createUpdateTaskDueDateLog(
        userId: Int,
        date: LocalDateTime,
        oldValue: LocalDateTime?,
        newValue: LocalDateTime,
    ) = UpdateTaskDueDateLog(userId, date, oldValue, newValue)

    fun createUpdateTaskNameLog(
        userId: Int,
        date: LocalDateTime,
        oldValue: String,
        newValue: String,
    ) = UpdateTaskNameLog(userId, date, oldValue, newValue)

    fun createUpdateTaskParentIdLog(
        userId: Int,
        date: LocalDateTime,
        oldValue: Int?,
        newValue: Int,
    ) = UpdateTaskParentIdLog(userId, date, oldValue, newValue)

    fun createUpdateTaskPriorityLog(
        userId: Int,
        date: LocalDateTime,
        oldValue: Priority?,
        newValue: Priority,
    ) = UpdateTaskPriorityLog(userId, date, oldValue, newValue)

    fun createUpdateTaskProjectIdLog(
        userId: Int,
        date: LocalDateTime,
        oldValue: Int?,
        newValue: Int,
    ) = UpdateTaskProjectIdLog(userId, date, oldValue, newValue)

    fun createUpdateTaskTargetDateLog(
        userId: Int,
        date: LocalDateTime,
        oldValue: LocalDateTime?,
        newValue: LocalDateTime,
    ) = UpdateTaskTargetDateLog(userId, date, oldValue, newValue)

    fun createCreateTaskLog(
        userId: Int,
        date: LocalDateTime,
    ) = CreateTaskLog(userId, date)
}
