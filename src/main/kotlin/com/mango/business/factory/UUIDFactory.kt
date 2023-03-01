package com.mango.business.factory

import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UUIDFactory {
    fun createTaskId(): TaskId = TaskId(UUID.randomUUID().toString())
}
