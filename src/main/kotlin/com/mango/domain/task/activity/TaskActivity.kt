package com.mango.domain.task.activity

import com.mango.domain.common.Activity
import com.mango.domain.task.model.TaskId
import java.time.LocalDateTime

sealed class TaskActivity(val taskId: TaskId, val date: LocalDateTime) : Activity
