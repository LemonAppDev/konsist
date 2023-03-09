package com.mango.business.factory

import com.mango.business.model.value.CommentId
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UUIDFactory {
    fun createUserId() = UserId(UUID.randomUUID())
    fun createTaskId() = TaskId(UUID.randomUUID())
    fun createProjectId() = ProjectId(UUID.randomUUID())
    fun createCommentId() = CommentId(UUID.randomUUID())
}
