package com.mango.persistence.repository

import com.mango.business.model.Project
import org.springframework.stereotype.Repository

@Repository
class ProjectRepository {
    private val _projects = mutableListOf<Project>()

    val projects get() = _projects.toList()

    fun addProject(project: Project) {
        _projects.add(project)
    }
}
