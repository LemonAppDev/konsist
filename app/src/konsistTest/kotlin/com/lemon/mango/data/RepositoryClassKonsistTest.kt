package com.lemon.mango.data

import com.lemon.konsist.core.assertion.check.check
import com.lemon.mango.mangoScope
import org.junit.jupiter.api.Test

class RepositoryClassKonsistTest {
    private val sut = mangoScope
        .classes()
        .filter { it.name.endsWith("RepositoryImpl") }

    @Test
    fun `classes having name ending with RepositoryImpl should reside in data package`() {
        sut.check { it.resideInAPackages("..data..") }
    }
}
