package com.lemon.mango.domain.project

import com.lemon.mango.domain.project.model.Project
import com.lemon.mango.domain.project.model.ProjectId

interface ProjectRepository {
    val projects: List<Project>

    fun getProject(projectId: ProjectId): Project?
    fun saveProject(project: Project): Project
    fun deleteProject(project: Project)
    fun containsProject(projectId: ProjectId): Boolean
}
