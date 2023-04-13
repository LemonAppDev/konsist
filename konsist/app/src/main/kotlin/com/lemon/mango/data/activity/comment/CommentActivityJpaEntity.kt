package com.lemon.mango.data.activity.comment

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "comment_activity")
class CommentActivityJpaEntity(
    @Id
    val id: UUID,

    @Column(name = "creatorId", nullable = false)
    val ownerId: UUID,

    @Column(name = "type", nullable = false)
    val type: String,

    @Column(name = "commentId", nullable = false)
    val commentId: UUID,

    @Column(name = "taskId", nullable = false)
    val taskId: UUID,

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP(9)")
    val date: LocalDateTime,

    @Column(name = "newValue", nullable = true)
    val newValue: String?,

    @Column(name = "oldValue", nullable = true)
    val oldValue: String?,
)
