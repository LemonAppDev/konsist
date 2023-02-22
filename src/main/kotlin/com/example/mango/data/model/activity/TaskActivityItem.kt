package com.example.mango.data.model.activity

import java.time.LocalDateTime

sealed class TaskActivityItem(val userId: Int, val date: LocalDateTime)
