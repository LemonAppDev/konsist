package com.mango.data.comment

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "comment")
class CommentJpaEntity(
    @Id
    val id: UUID,

    @Column(name = "creationDate", nullable = false)
    val creationDate: LocalDateTime,

    @Column(name = "creatorId", nullable = false)
    val ownerId: UUID,

    @Column(name = "name", nullable = false)
    val text: String,

    @Column(name = "taskId", nullable = false)
    val taskId: UUID,
)
