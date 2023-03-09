package com.mango.persistence.datasource

import com.mango.persistence.entity.CommentJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CommentJpaRepository : JpaRepository<CommentJpaEntity, UUID>
