package com.mango.data.activity.project

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProjectActivityJpaRepository : JpaRepository<ProjectActivityJpaEntity, UUID>
