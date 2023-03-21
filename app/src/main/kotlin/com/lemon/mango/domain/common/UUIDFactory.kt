package com.lemon.mango.domain.common

import com.lemon.mango.domain.activity.model.CommentActivityId
import com.lemon.mango.domain.activity.model.ProjectActivityId
import com.lemon.mango.domain.activity.model.TaskActivityId
import com.lemon.mango.domain.comment.model.CommentId
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UUIDFactory {
    fun createUserId() = UserId(UUID.randomUUID())
    fun createTaskId() = TaskId(UUID.randomUUID())
    fun createProjectId() = ProjectId(UUID.randomUUID())
    fun createCommentId() = CommentId(UUID.randomUUID())
    fun createCommentActivityId() = CommentActivityId(UUID.randomUUID())
    fun createProjectActivityId() = ProjectActivityId(UUID.randomUUID())
    fun createTaskActivityId() = TaskActivityId(UUID.randomUUID())
}
