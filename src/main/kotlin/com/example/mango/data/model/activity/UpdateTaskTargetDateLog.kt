package com.example.mango.data.model.activity

import java.time.LocalDateTime

class UpdateTaskTargetDateLog(
    userId: Int,
    date: LocalDateTime,
    val oldValue: LocalDateTime?,
    val newValue: LocalDateTime,
) : TaskActivityItem(userId, date)
