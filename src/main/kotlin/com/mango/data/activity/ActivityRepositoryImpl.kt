package com.mango.data.activity

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.CommentActivity
import com.mango.domain.activity.ProjectActivity
import com.mango.domain.activity.TaskActivity
import org.springframework.stereotype.Service

@Service
class ActivityRepositoryImpl : ActivityRepository {
    private val _taskActivities = mutableListOf<TaskActivity>()
    private val _projectActivities = mutableListOf<ProjectActivity>()
    private val _commentActivities = mutableListOf<CommentActivity>()

    override val taskActivities: List<TaskActivity> get() = _taskActivities
    override val projectActivities: List<ProjectActivity> get() = _projectActivities
    override val commentActivities: List<CommentActivity> get() = _commentActivities

    override fun addTaskActivity(activity: TaskActivity) {
        _taskActivities.add(activity)
    }

    override fun addProjectActivity(activity: ProjectActivity) {
        _projectActivities.add(activity)
    }

    override fun addCommentActivity(activity: CommentActivity) {
        _commentActivities.add(activity)
    }
}
