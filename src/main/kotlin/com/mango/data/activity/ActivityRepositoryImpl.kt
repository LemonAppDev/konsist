package com.mango.data.activity

import com.mango.data.activity.comment.CommentActivityJpaEntityToCommentActivityMapper
import com.mango.data.activity.comment.CommentActivityJpaRepository
import com.mango.data.activity.comment.CommentActivityToCommentActivityJpaEntityMapper
import com.mango.data.activity.project.ProjectActivityJpaEntityToProjectActivityMapper
import com.mango.data.activity.project.ProjectActivityJpaRepository
import com.mango.data.activity.project.ProjectActivityToProjectActivityJpaEntityMapper
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
    private val projectActivityJpaRepository: ProjectActivityJpaRepository,
    private val projectActivityJpaEntityToProjectActivityMapper: ProjectActivityJpaEntityToProjectActivityMapper,
    private val projectActivityToProjectActivityJpaEntityMapper: ProjectActivityToProjectActivityJpaEntityMapper,
) : ActivityRepository {
    private val _taskActivities = mutableListOf<TaskActivity>()
    override val taskActivities: List<TaskActivity> get() = _taskActivities

    override val commentActivities: List<CommentActivity>
        get() = commentActivityJpaRepository
            .findAll()
            .map { commentActivityJpaEntityToCommentActivityMapper(it) }

    override fun addCommentActivity(activity: CommentActivity) = commentActivityJpaRepository
        .save(commentActivityToCommentActivityJpaEntityMapper(activity))
        .let { commentActivityJpaEntityToCommentActivityMapper(it) }

    override val projectActivities: List<ProjectActivity>
        get() = projectActivityJpaRepository
            .findAll()
            .map { projectActivityJpaEntityToProjectActivityMapper(it) }

    override fun addProjectActivity(activity: ProjectActivity) = projectActivityJpaRepository
        .save(projectActivityToProjectActivityJpaEntityMapper(activity))
        .let { projectActivityJpaEntityToProjectActivityMapper(it) }

    override fun addTaskActivity(activity: TaskActivity) {
        _taskActivities.add(activity)
    }
}
