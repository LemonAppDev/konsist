package com.mango.domain.project.activity

import com.mango.domain.project.model.ProjectId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class DeleteProjectActivity(
    projectId: ProjectId,
    date: LocalDateTime,
) : ProjectActivity(projectId, date)

@Service
class DeleteProjectActivityFactory {
    operator fun invoke(
        projectId: ProjectId,
        date: LocalDateTime,
    ) = DeleteProjectActivity(projectId, date)
}
