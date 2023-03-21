package com.lemon.mango.data.activity.project

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "project_activity")
class ProjectActivityJpaEntity(
    @Id
    val id: UUID,

    @Column(name = "creatorId", nullable = false)
    val userId: UUID,

    @Column(name = "type", nullable = false)
    val type: String,

    @Column(name = "projectId", nullable = false)
    val projectId: UUID,

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP(9)")
    val date: LocalDateTime,

    @Column(name = "newValue", nullable = true)
    val newValue: String?,

    @Column(name = "oldValue", nullable = true)
    val oldValue: String?,
)
