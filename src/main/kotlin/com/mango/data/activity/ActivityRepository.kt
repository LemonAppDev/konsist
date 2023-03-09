package com.mango.data.activity

import com.mango.domain.project.activity.ProjectActivity
import com.mango.domain.task.activity.TaskActivity
import org.springframework.stereotype.Service

@Service
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
