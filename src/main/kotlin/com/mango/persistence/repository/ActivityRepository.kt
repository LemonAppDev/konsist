package com.mango.persistence.repository

import com.mango.business.model.activity.task.TaskActivity
import org.springframework.stereotype.Repository

@Repository
class ActivityRepository {
    private val _activities = mutableListOf<TaskActivity>()

    val activities: List<TaskActivity> get() = _activities

    fun addActivity(activity: TaskActivity) {
        _activities.add(activity)
    }
}
