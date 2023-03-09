package com.mango.persistence.datasource

import com.mango.persistence.entity.TaskJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskJpaRepository : JpaRepository<TaskJpaEntity, UUID>
