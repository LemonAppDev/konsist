package com.mango.archunit.presentation

import com.mango.archunit.utils.PackageIdentifier
import com.mango.archunit.utils.ProjectClassesProvider.allClasses
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test
import org.springframework.web.bind.annotation.RestController

class ControllerClassRulesTest {
    @Test
    fun `classes with RestController annotation should reside in presentation and controller packages`() {
        classes()
            .that().areAnnotatedWith(RestController::class.java)
            .should().resideInAPackage(PackageIdentifier.ANY_PRESENTATION_ANY_CONTROLLER_ANY)
            .check(allClasses)
    }

    @Test
    fun `classes with RestController annotation should have Controller suffix`() {
        classes()
            .that().areAnnotatedWith(RestController::class.java)
            .should().haveSimpleNameEndingWith("Controller")
            .check(allClasses)
    }
}
