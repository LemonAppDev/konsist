package com.mango.data.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskJpaRepository : JpaRepository<TaskJpaEntity, UUID>
