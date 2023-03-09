package com.mango.data.project

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProjectJpaRepository : JpaRepository<ProjectJpaEntity, UUID>
