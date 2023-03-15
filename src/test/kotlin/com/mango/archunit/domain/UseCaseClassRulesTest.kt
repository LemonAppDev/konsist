package com.mango.archunit.domain

import com.mango.archunit.utils.PackageIdentifier
import com.mango.archunit.utils.ProjectClassesProvider.allClasses
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test

class UseCaseClassRulesTest {
    @Test
    fun `classes with use case suffix should resist in domain and usecase packages`() {
        classes()
            .that().haveSimpleNameEndingWith("UseCase")
            .should()
            .resideInAPackage(PackageIdentifier.ANY_DOMAIN_ANY_USECASE_ANY).check(allClasses)
    }
}
