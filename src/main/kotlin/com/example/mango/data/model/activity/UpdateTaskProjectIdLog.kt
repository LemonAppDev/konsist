package com.example.mango.data.model.activity

import java.time.LocalDateTime

class UpdateTaskProjectIdLog(
    userId: Int,
    date: LocalDateTime,
    val oldValue: Int?,
    val newValue: Int,
) : TaskActivityItem(userId, date)
