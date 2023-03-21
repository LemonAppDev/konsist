package com.test.mangotest.data

import com.konsistcore.core.assertion.check.check
import com.test.mangotest.mangoScope
import org.junit.jupiter.api.Test
import org.springframework.stereotype.Repository

class JpaRepositoryInterfaceKonsistTest {
    private val sut = mangoScope
        .interfaces()
        .filter { it.name.endsWith("JpaRepository") }

    @Test
    fun `JpaRepository interface should reside in data package`() {
        sut.check { it.resideInAPackages("..data..") }
    }

    @Test
    fun `JpaRepository interface should be annotated with 'Repository' annotation`() {
        sut.check { it.hasAnnotation(Repository::class) }
    }
}
