package com.mango.archunit.data

import com.mango.archunit.utils.PackageIdentifier
import com.mango.archunit.utils.ProjectClassesProvider.allClasses
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test
import org.springframework.stereotype.Repository

class RepositoryClassRulesTest {
    @Test
    fun `classes with Repository annotation should reside in data package`() {
        classes()
            .that().areAnnotatedWith(Repository::class.java)
            .should().resideInAPackage(PackageIdentifier.ANY_DATA_ANY)
            .check(allClasses)
    }

    @Test
    fun `classes with Repository annotation should have Repository suffix`() {
        classes()
            .that()
            .areAnnotatedWith(Repository::class.java)
            .should().haveSimpleNameEndingWith("Repository")
            .check(allClasses)
    }
}
