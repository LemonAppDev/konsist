package com.mango.data.activity

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.project.activity.ProjectActivity
import org.springframework.stereotype.Service

@Service
class ActivityRepositoryImpl : ActivityRepository {
    private val _taskActivities = mutableListOf<com.mango.domain.task.activity.TaskActivity>()
    private val _projectActivities = mutableListOf<ProjectActivity>()

    override val taskActivities: List<com.mango.domain.task.activity.TaskActivity> get() = _taskActivities
    override val projectActivities: List<ProjectActivity> get() = _projectActivities

    override fun addActivity(activity: com.mango.domain.task.activity.TaskActivity) {
        _taskActivities.add(activity)
    }

    override fun addActivity(activity: ProjectActivity) {
        _projectActivities.add(activity)
    }
}
