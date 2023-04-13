package com.lemon.mango.data.activity

import com.lemon.mango.data.activity.comment.CommentActivityJpaEntityToCommentActivityMapper
import com.lemon.mango.data.activity.comment.CommentActivityJpaRepository
import com.lemon.mango.data.activity.comment.CommentActivityToCommentActivityJpaEntityMapper
import com.lemon.mango.data.activity.project.ProjectActivityJpaEntityToProjectActivityMapper
import com.lemon.mango.data.activity.project.ProjectActivityJpaRepository
import com.lemon.mango.data.activity.project.ProjectActivityToProjectActivityJpaEntityMapper
import com.lemon.mango.data.activity.task.TaskActivityJpaEntityToTaskActivityMapper
import com.lemon.mango.data.activity.task.TaskActivityJpaRepository
import com.lemon.mango.data.activity.task.TaskActivityToTaskActivityJpaEntityMapper
import com.lemon.mango.domain.activity.ActivityRepository
import com.lemon.mango.domain.activity.model.CommentActivity
import com.lemon.mango.domain.activity.model.ProjectActivity
import com.lemon.mango.domain.activity.model.TaskActivity
import org.springframework.stereotype.Service

@Service
class ActivityRepositoryImpl(
    private val commentActivityJpaEntityToCommentActivityMapper: CommentActivityJpaEntityToCommentActivityMapper,
    private val commentActivityJpaRepository: CommentActivityJpaRepository,
    private val commentActivityToCommentActivityJpaEntityMapper: CommentActivityToCommentActivityJpaEntityMapper,
    private val projectActivityJpaEntityToProjectActivityMapper: ProjectActivityJpaEntityToProjectActivityMapper,
    private val projectActivityJpaRepository: ProjectActivityJpaRepository,
    private val projectActivityToProjectActivityJpaEntityMapper: ProjectActivityToProjectActivityJpaEntityMapper,
    private val taskActivityJpaEntityToTaskActivityMapper: TaskActivityJpaEntityToTaskActivityMapper,
    private val taskActivityJpaRepository: TaskActivityJpaRepository,
    private val taskActivityToTaskActivityJpaEntityMapper: TaskActivityToTaskActivityJpaEntityMapper,
) : ActivityRepository {
    override val commentActivities: List<CommentActivity>
        get() = commentActivityJpaRepository
            .findAll()
            .map { commentActivityJpaEntityToCommentActivityMapper(it) }

    override val projectActivities: List<ProjectActivity>
        get() = projectActivityJpaRepository
            .findAll()
            .map { projectActivityJpaEntityToProjectActivityMapper(it) }

    override val taskActivities: List<TaskActivity>
        get() = taskActivityJpaRepository
            .findAll()
            .map { taskActivityJpaEntityToTaskActivityMapper(it) }

    override fun addCommentActivity(activity: CommentActivity) = commentActivityJpaRepository
        .save(commentActivityToCommentActivityJpaEntityMapper(activity))
        .let { commentActivityJpaEntityToCommentActivityMapper(it) }

    override fun addProjectActivity(activity: ProjectActivity) = projectActivityJpaRepository
        .save(projectActivityToProjectActivityJpaEntityMapper(activity))
        .let { projectActivityJpaEntityToProjectActivityMapper(it) }

    override fun addTaskActivity(activity: TaskActivity) = taskActivityJpaRepository
        .save(taskActivityToTaskActivityJpaEntityMapper(activity))
        .let { taskActivityJpaEntityToTaskActivityMapper(it) }
}
