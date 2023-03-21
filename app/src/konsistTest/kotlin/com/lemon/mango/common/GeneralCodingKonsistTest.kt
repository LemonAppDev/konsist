package com.lemon.mango.common

import com.lemon.konsist.core.assertion.check.check
import com.lemon.konsist.core.assertion.check.checkNot
import com.lemon.mango.mangoScope
import jakarta.persistence.Entity
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import javax.inject.Inject

class GeneralCodingKonsistTest {
    @Test
    fun `no field should have 'm' prefix`() {
        mangoScope.classes()
            .flatMap { it.properties() }
            .checkNot {
                val secondCharacterIsUppercase = it.name.getOrNull(1)?.isUpperCase() ?: false
                it.name.startsWith('m') && secondCharacterIsUppercase
            }
    }

    @Test
    fun `no classes should use field injection`() {
        mangoScope
            .classes()
            .flatMap { it.properties() }
            .check { !it.hasAnnotation(Inject::class) }
    }

    @Test
    fun `every class has test class`() {
        mangoScope
            .classes()
            .filterNot { it.resideInAPackages("..config..", "..model..") }
            .filterNot { it.hasAnnotation(SpringBootApplication::class) }
            .filterNot { it.hasAnnotation(Entity::class) }
            .filterNot { it.name.endsWith("Test") }
            .filterNot { it.name.endsWith("JpaRepository") }
            .filterNot { it.name.endsWith("Factory") }
            .check {
                mangoScope.classes().any { testClass -> testClass.fullyQualifiedName == "${it.fullyQualifiedName}Test" }
            }
    }
}
