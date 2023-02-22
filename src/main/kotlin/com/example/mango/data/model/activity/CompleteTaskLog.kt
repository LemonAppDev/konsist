package com.example.mango.data.model.activity

import java.time.LocalDateTime

class CompleteTaskLog(
    userId: Int,
    date: LocalDateTime,
) : TaskActivityItem(userId, date)
