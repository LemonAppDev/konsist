package com.lemon.mango.data.project

import com.lemon.mango.domain.project.ProjectRepository
import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.project.model.ProjectId
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ProjectRepositoryImpl(
    private val projectJpaEntityToProjectMapper: ProjectJpaEntityToProjectMapper,
    private val projectJpaRepository: ProjectJpaRepository,
    private val projectToProjectJpaEntityMapper: ProjectToProjectJpaEntityMapper,
) : ProjectRepository {
    override val projects
        get() = projectJpaRepository
            .findAll()
            .map { projectJpaEntityToProjectMapper(it) }

    override fun getProject(projectId: ProjectId) = projectJpaRepository
        .findById(projectId.value)
        .getOrNull()
        ?.let { projectJpaEntityToProjectMapper(it) }

    override fun saveProject(project: Project) = projectJpaRepository
        .save(projectToProjectJpaEntityMapper(project))
        .let { projectJpaEntityToProjectMapper(it) }

    override fun deleteProject(project: Project) = projectJpaRepository
        .delete(projectToProjectJpaEntityMapper(project))

    override fun containsProject(projectId: ProjectId) = projectJpaRepository
        .existsById(projectId.value)
}
