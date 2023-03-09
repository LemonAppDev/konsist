package com.mango.domain.project.activity

import com.mango.domain.project.model.ProjectId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class CreateProjectActivity(
    projectId: ProjectId,
    date: LocalDateTime,
) : ProjectActivity(projectId, date)

@Service
class CreateProjectActivityFactory {
    operator fun invoke(
        projectId: ProjectId,
        date: LocalDateTime,
    ) = CreateProjectActivity(projectId, date)
}
