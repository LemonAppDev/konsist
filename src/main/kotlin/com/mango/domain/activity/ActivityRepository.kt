package com.mango.domain.activity

import com.mango.domain.project.activity.ProjectActivity
import com.mango.domain.task.activity.TaskActivity

interface ActivityRepository {
    val taskActivities: List<TaskActivity>
    val projectActivities: List<ProjectActivity>
    fun addActivity(activity: TaskActivity)
    fun addActivity(activity: ProjectActivity)
}
