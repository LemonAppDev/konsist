package com.mango.archunit.common

import com.mango.archunit.utils.ProjectClassesProvider.allClasses
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields
import com.tngtech.archunit.library.GeneralCodingRules
import org.junit.jupiter.api.Test

class GeneralCodingRulesTest {
    @Test
    fun `no one should ever name fields using 'm' prefix`() {
        noFields().should().haveNameMatching("m[A-Z]+.*").check(allClasses)
    }

    @Test
    fun `no classes should use field injection`() {
        GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION.check(allClasses)
    }

    @Test
    fun `no classes should use Java util logging`() {
        GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING.check(allClasses)
    }
}
