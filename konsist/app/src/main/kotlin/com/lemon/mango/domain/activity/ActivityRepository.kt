package com.lemon.mango.domain.activity

import com.lemon.mango.domain.activity.model.CommentActivity
import com.lemon.mango.domain.activity.model.ProjectActivity
import com.lemon.mango.domain.activity.model.TaskActivity

interface ActivityRepository {
    val taskActivities: List<TaskActivity>
    val projectActivities: List<ProjectActivity>
    val commentActivities: List<CommentActivity>
    fun addTaskActivity(activity: TaskActivity): TaskActivity
    fun addProjectActivity(activity: ProjectActivity): ProjectActivity
    fun addCommentActivity(activity: CommentActivity): CommentActivity
}
