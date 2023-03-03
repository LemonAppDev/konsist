package com.mango.persistence.repository

import com.mango.business.model.activity.project.ProjectActivity
import com.mango.business.model.activity.task.TaskActivity
import org.springframework.stereotype.Repository

@Repository
class ActivityRepository {
    private val _taskActivities = mutableListOf<TaskActivity>()
    private val _projectActivities = mutableListOf<ProjectActivity>()

    val taskActivities: List<TaskActivity> get() = _taskActivities
    val projectActivities: List<ProjectActivity> get() = _projectActivities

    fun addActivity(activity: TaskActivity) {
        _taskActivities.add(activity)
    }

    fun addActivity(activity: ProjectActivity) {
        _projectActivities.add(activity)
    }
}
