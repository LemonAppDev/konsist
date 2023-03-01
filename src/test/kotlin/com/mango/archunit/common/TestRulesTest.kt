package com.mango.archunit.common

import com.mango.archunit.utils.CustomArchCondition.haveExactlyOneField
import com.mango.archunit.utils.ProjectClassesProvider.allClasses
import com.tngtech.archunit.core.domain.JavaModifier
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.library.GeneralCodingRules.testClassesShouldResideInTheSamePackageAsImplementation
import org.junit.jupiter.api.Test

class TestRulesTest {
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
            .should(haveExactlyOneField("sut", JavaModifier.PRIVATE))
            .check(allClasses)
    }

    @Test
    fun `test classes should have sut property 2`() {
        classes()
            .that().resideInAPackage("..archunit..")
            .and()
            .haveSimpleNameEndingWith("Test")
            .should()
            .haveSimpleNameEndingWith("RulesTest")
            .check(allClasses)
    }
}
