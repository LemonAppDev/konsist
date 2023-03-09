package com.mango.domain.common

import com.mango.domain.comment.model.CommentId
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UUIDFactory {
    fun createUserId() = UserId(UUID.randomUUID())
    fun createTaskId() = TaskId(UUID.randomUUID())
    fun createProjectId() = ProjectId(UUID.randomUUID())
    fun createCommentId() = CommentId(UUID.randomUUID())
}
