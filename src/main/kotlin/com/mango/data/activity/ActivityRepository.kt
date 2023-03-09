package com.mango.data.activity

import com.mango.domain.project.activity.ProjectActivity
import org.springframework.stereotype.Service

@Service
class ActivityRepository {
    private val _taskActivities = mutableListOf<com.mango.domain.task.activity.TaskActivity>()
    private val _projectActivities = mutableListOf<ProjectActivity>()

    val taskActivities: List<com.mango.domain.task.activity.TaskActivity> get() = _taskActivities
    val projectActivities: List<ProjectActivity> get() = _projectActivities

    fun addActivity(activity: com.mango.domain.task.activity.TaskActivity) {
        _taskActivities.add(activity)
    }

    fun addActivity(activity: ProjectActivity) {
        _projectActivities.add(activity)
    }
}
