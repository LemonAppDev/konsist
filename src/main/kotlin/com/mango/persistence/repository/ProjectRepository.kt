package com.mango.persistence.repository

import com.mango.business.model.Project
import com.mango.business.model.value.ProjectId
import com.mango.persistence.datasource.ProjectJpaRepository
import com.mango.persistence.entity.mapper.ProjectJpaEntityToProjectMapper
import com.mango.persistence.entity.mapper.ProjectToProjectJpaEntityMapper
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ProjectRepository(
    private val projectJpaRepository: ProjectJpaRepository,
    private val projectToProjectJpaEntityMapper: ProjectToProjectJpaEntityMapper,
    private val projectJpaEntityToProjectMapper: ProjectJpaEntityToProjectMapper,
) {
    val projects
        get() = projectJpaRepository
            .findAll()
            .map { projectJpaEntityToProjectMapper(it) }

    fun getProject(projectId: ProjectId) = projectJpaRepository
        .findById(projectId.value)
        .getOrNull()
        ?.let { projectJpaEntityToProjectMapper(it) }

    fun saveProject(project: Project) = projectJpaRepository
        .save(projectToProjectJpaEntityMapper(project))
        .let { projectJpaEntityToProjectMapper(it) }

    fun deleteProject(project: Project) = projectJpaRepository
        .delete(projectToProjectJpaEntityMapper(project))

    fun containsProject(projectId: ProjectId) = projectJpaRepository
        .existsById(projectId.value)
}
