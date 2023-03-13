package com.mango.domain.activity

import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.TaskActivity

interface ActivityRepository {
    val taskActivities: List<TaskActivity>
    val projectActivities: List<ProjectActivity>
    val commentActivities: List<CommentActivity>
    fun addTaskActivity(activity: TaskActivity)
    fun addProjectActivity(activity: ProjectActivity): ProjectActivity
    fun addCommentActivity(activity: CommentActivity): CommentActivity
}
