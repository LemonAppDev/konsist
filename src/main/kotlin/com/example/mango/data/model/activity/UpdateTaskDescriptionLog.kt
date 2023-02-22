package com.example.mango.data.model.activity

import java.time.LocalDateTime

class UpdateTaskDescriptionLog(
    userId: Int,
    date: LocalDateTime,
    val oldValue: String?,
    val newValue: String,
) : TaskActivityItem(userId, date)
