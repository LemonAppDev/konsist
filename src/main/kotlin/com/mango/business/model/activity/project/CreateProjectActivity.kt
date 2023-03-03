package com.mango.business.model.activity.project

import com.mango.business.model.value.ProjectId
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
