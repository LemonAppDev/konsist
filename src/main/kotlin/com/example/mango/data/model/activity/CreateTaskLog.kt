package com.example.mango.data.model.activity

import java.time.LocalDateTime

class CreateTaskLog(
    userId: Int,
    date: LocalDateTime,
) : TaskActivityItem(userId, date)
