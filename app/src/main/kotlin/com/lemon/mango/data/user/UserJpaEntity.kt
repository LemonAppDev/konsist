package com.lemon.mango.data.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "appUser")
class UserJpaEntity(
    @Id
    val id: UUID,

    @Column(name = "name", nullable = false)
    val name: String,
)
