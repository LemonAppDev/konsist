package com.mango.persistence.repository

import com.mango.business.model.Project
import com.mango.business.model.value.ProjectId
import org.springframework.stereotype.Repository

@Repository
class ProjectRepository {
    private val _projects = mutableListOf<Project>()

    val projects get() = _projects.toList()

    fun getProject(projectId: ProjectId) = _projects.firstOrNull { it.id == projectId }

    fun containsProject(projectId: ProjectId) = getProject(projectId) != null

    fun addProject(project: Project) {
        _projects.add(project)
    }

    fun deleteProject(project: Project) {
        project
        TODO("not implemented")
    }
}
