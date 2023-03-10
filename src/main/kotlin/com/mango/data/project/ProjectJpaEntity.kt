package com.mango.data.project

import com.mango.domain.common.model.Color
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "project")
class ProjectJpaEntity(
    @Id
    val id: UUID,

    @Column(name = "ownerId", nullable = false)
    val ownerId: UUID,

    @Column(name = "creationDate", nullable = false, columnDefinition = "TIMESTAMP(9)")
    val creationDate: LocalDateTime,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "color", nullable = false)
    val color: Color,

    @Column(name = "isFavourite", nullable = false)
    val isFavourite: Boolean,
)
