package com.mango.domain.activity

interface ActivityRepository {
    val taskActivities: List<TaskActivity>
    val projectActivities: List<ProjectActivity>
    val commentActivities: List<CommentActivity>
    fun addTaskActivity(activity: TaskActivity)
    fun addProjectActivity(activity: ProjectActivity)
    fun addCommentActivity(activity: CommentActivity)
}
