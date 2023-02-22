package com.example.mango.data.model.activity

import com.example.mango.data.model.Priority
import java.time.LocalDateTime

class UpdateTaskPriorityLog(
    userId: Int,
    date: LocalDateTime,
    val oldValue: Priority?,
    val newValue: Priority,
) : TaskActivityItem(userId, date)
