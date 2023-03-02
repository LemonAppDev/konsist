package com.mango.business.factory

import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UUIDFactory {
    fun createTaskId() = TaskId(UUID.randomUUID().toString())

    fun createCommentId() = CommentId(UUID.randomUUID().toString())
}
