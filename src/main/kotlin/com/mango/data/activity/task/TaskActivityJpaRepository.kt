package com.mango.data.activity.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskActivityJpaRepository : JpaRepository<TaskActivityJpaEntity, UUID>
