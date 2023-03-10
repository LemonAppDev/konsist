package com.mango.archunit.common

import com.mango.archunit.utils.ProjectClassesProvider.allClasses
import com.mango.archunit.utils.haveOnlyTestsMarkedAsPublic
import com.mango.archunit.utils.haveTestSubjectNamed
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.library.GeneralCodingRules.testClassesShouldResideInTheSamePackageAsImplementation
import org.junit.jupiter.api.Test

class GeneralTestCodingRulesTest {
    @Test
    fun `test classes should reside in the same package as implementation`() {
        testClassesShouldResideInTheSamePackageAsImplementation().check(allClasses)
    }

    @Test
    fun `test classes should have sut property`() {
        classes()
            .that().haveSimpleNameEndingWith("Test")
            .and()
            .resideOutsideOfPackages("..archunit..")
            .should(haveTestSubjectNamed("sut"))
            .check(allClasses)
    }

    @Test
    fun `test classes should have only public tests`() {
        classes()
            .that().haveSimpleNameEndingWith("Test")
            .and()
            .resideOutsideOfPackages("..archunit..")
            .should(haveOnlyTestsMarkedAsPublic())
            .check(allClasses)
    }
}
