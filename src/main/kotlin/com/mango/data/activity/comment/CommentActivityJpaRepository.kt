package com.mango.data.activity.comment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CommentActivityJpaRepository : JpaRepository<CommentActivityJpaEntity, UUID>
