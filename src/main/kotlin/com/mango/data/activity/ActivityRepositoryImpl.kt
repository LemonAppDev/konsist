package com.mango.data.activity

import com.mango.data.activity.comment.CommentActivityJpaEntityToCommentActivityMapper
import com.mango.data.activity.comment.CommentActivityJpaRepository
import com.mango.data.activity.comment.CommentActivityToCommentActivityJpaEntityMapper
import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.TaskActivity
import org.springframework.stereotype.Service

@Service
class ActivityRepositoryImpl(
    private val commentActivityJpaRepository: CommentActivityJpaRepository,
    private val commentActivityJpaEntityToCommentActivityMapper: CommentActivityJpaEntityToCommentActivityMapper,
    private val commentActivityToCommentActivityJpaEntityMapper: CommentActivityToCommentActivityJpaEntityMapper,
) : ActivityRepository {
    private val _taskActivities = mutableListOf<TaskActivity>()
    private val _projectActivities = mutableListOf<ProjectActivity>()
    override val taskActivities: List<TaskActivity> get() = _taskActivities
    override val projectActivities: List<ProjectActivity> get() = _projectActivities

    override val commentActivities: List<CommentActivity>
        get() = commentActivityJpaRepository
            .findAll()
            .map { commentActivityJpaEntityToCommentActivityMapper(it) }

    override fun addCommentActivity(activity: CommentActivity) = commentActivityJpaRepository
        .save(commentActivityToCommentActivityJpaEntityMapper(activity))
        .let { commentActivityJpaEntityToCommentActivityMapper(it) }

    override fun addTaskActivity(activity: TaskActivity) {
        _taskActivities.add(activity)
    }

    override fun addProjectActivity(activity: ProjectActivity) {
        _projectActivities.add(activity)
    }
}
