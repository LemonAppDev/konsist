package com.mango.business.model.activity.task

import com.mango.business.model.activity.Activity
import com.mango.business.model.value.TaskId
import java.time.LocalDateTime

sealed class TaskActivity(val taskId: TaskId, val date: LocalDateTime) : Activity
