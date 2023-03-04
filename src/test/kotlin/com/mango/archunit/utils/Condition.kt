package com.mango.archunit.utils

import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaModifier
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.ConditionEvent.createMessage
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.SimpleConditionEvent.violated
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest

fun haveOnePublicMethodWithName(methodName: String) =
    object : ArchCondition<JavaClass>("have exactly one public method named '$methodName'") {
        override fun check(javaClass: JavaClass, conditionEvents: ConditionEvents) {
            val publicMethods = javaClass
                .methods
                .filter { it.modifiers.contains(JavaModifier.PUBLIC) }

            if (publicMethods.size == 1) {
                val method = publicMethods[0]

                // When method accepts Kotlin value class then Kotlin will generate
                // a random suffix to the method name e.g. methodName-suffix
                val methodExists = method.name == methodName || method.name.startsWith("$methodName-")

                if (!methodExists) {
                    val message: String = createMessage(
                        javaClass,
                        "contains does not have method named '${method.name}' ${method.sourceCodeLocation}",
                    )
                    conditionEvents.add(violated(javaClass, message))
                }
            } else {
                val message: String = createMessage(
                    javaClass,
                    "contains multiple public methods",
                )
                conditionEvents.add(violated(javaClass, message))
            }
        }
    }

fun haveOnlyTestsMarkedAsPublic() = object : ArchCondition<JavaClass>("have only tests marked as public") {
    override fun check(javaClass: JavaClass, conditionEvents: ConditionEvents) {
        val publicMethods = javaClass
            .methods
            .filter { it.modifiers.contains(JavaModifier.PUBLIC) }
            .filterNot { it.modifiers.contains(JavaModifier.STATIC) }
            .filterNot { it.fullName.contains(".access$") } // filter kotlin generated methods

        val allPublicMethodsAreTests = publicMethods
            .map { it.annotations }
            .all { annotations ->
                annotations.any {
                    it.type.name == Test::class.qualifiedName || it.type.name == ParameterizedTest::class.qualifiedName
                }
            }

        if (!allPublicMethodsAreTests) {
            val message: String = createMessage(
                javaClass,
                "contains public non-test members",
            )
            conditionEvents.add(violated(javaClass, message))
        }
    }
}

fun haveTestSubjectNamed(testSubjectFieldName: String) =
    object : ArchCondition<JavaClass>("have test subject field named '$testSubjectFieldName'") {
        override fun check(javaClass: JavaClass, conditionEvents: ConditionEvents) {
            val sutClassQualifiedName = javaClass
                .name
                .removeSuffix("Test")

            val field = javaClass
                .fields
                .firstOrNull { it.type.name == sutClassQualifiedName }

            if (field != null) {
                if (field.name != testSubjectFieldName) {
                    val message: String = createMessage(
                        javaClass,
                        "contains test subject field with incorrect name '${field.name}'",
                    )
                    conditionEvents.add(violated(javaClass, message))
                }
            }
        }
    }

fun haveTestClass(testClassSuffix: String) =
    object : ArchCondition<JavaClass>("have test class with '$testClassSuffix' suffix") {
        lateinit var testClassesByFullName: List<String>

        override fun init(allClasses: Collection<JavaClass?>) {
            testClassesByFullName = allClasses
                .filterNotNull()
                .filter { it.simpleName.endsWith(testClassSuffix) }
                .map { it.fullName }
        }

        override fun check(javaClass: JavaClass, conditionEvents: ConditionEvents) {
            if (javaClass.simpleName.isBlank() ||
                javaClass.simpleName.endsWith(testClassSuffix) ||
                javaClass.simpleName == "Companion" ||
                javaClass.simpleName.endsWith("Kt")
            ) {
                return
            }

            val classFullName = javaClass.fullName
            val testClassFullName = "$classFullName$testClassSuffix"
            val testExists = testClassesByFullName.any { it == testClassFullName }

            if (!testExists) {
                val message: String = createMessage(javaClass, "does not have test class")
                conditionEvents.add(violated(javaClass, message))
            }
        }
    }
