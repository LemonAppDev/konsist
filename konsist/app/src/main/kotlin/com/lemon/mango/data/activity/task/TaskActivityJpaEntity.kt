package com.lemon.mango.data.activity.task

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "task_activity")
class TaskActivityJpaEntity(
    @Id
    val id: UUID,

    @Column(name = "creatorId", nullable = false)
    val userId: UUID,

    @Column(name = "type", nullable = false)
    val type: String,

    @Column(name = "taskId", nullable = false)
    val taskId: UUID,

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP(9)")
    val date: LocalDateTime,

    @Column(name = "newValue", nullable = true)
    val newValue: String?,

    @Column(name = "oldValue", nullable = true)
    val oldValue: String?,
)
