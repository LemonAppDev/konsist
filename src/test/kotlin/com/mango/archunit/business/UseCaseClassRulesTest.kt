package com.mango.archunit.business

import com.mango.archunit.utils.CustomArchCondition.haveExactlyOneMethodWithPrefix
import com.mango.archunit.utils.PackageIdentifier
import com.mango.archunit.utils.ProjectClassesProvider.allClasses
import com.tngtech.archunit.core.domain.JavaModifier
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test

class UseCaseClassRulesTest {
    @Test
    fun `classes with use case suffix should resist in presentation and usecase packages`() {
        classes()
            .that().haveSimpleNameEndingWith("UseCase")
            .should()
            .resideInAPackage(PackageIdentifier.ANY_BUSINESS_ANY_USECASE_ANY).check(allClasses)
    }

    @Test
    fun `classes with UseCase should have single method name invoke`() {
        classes()
            .that().haveSimpleNameEndingWith("UseCase")
            .should(haveExactlyOneMethodWithPrefix("invoke", JavaModifier.PUBLIC))
            .check(allClasses)
    }
}
