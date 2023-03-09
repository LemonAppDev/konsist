package com.mango.data.task

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "task")
class TaskJpaEntity(
    @Id
    val id: UUID,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "ownerId", nullable = false)
    val ownerId: UUID,

    @Column(name = "creationDate", nullable = false, columnDefinition = "TIMESTAMP")
    val creationDate: LocalDateTime,

    @Column(name = "projectId")
    val projectId: UUID? = null,

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "dueDate", columnDefinition = "TIMESTAMP")
    val dueDate: LocalDateTime? = null,

    @Column(name = "targetDate", columnDefinition = "TIMESTAMP")
    val targetDate: LocalDateTime? = null,

    @Column(name = "priority")
    val priority: Int? = null,

    @Column(name = "parentTaskId")
    val parentTaskId: UUID? = null,

    @Column(name = "assigneeId")
    val assigneeId: UUID? = null,

    @Column(name = "completeDate")
    val completeDate: LocalDateTime? = null,
)
