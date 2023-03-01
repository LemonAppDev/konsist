package com.mango.archunit.utils

import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaModifier
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.SimpleConditionEvent

object CustomArchCondition {
    fun haveExactlyOneMethodWithPrefix(methodNamePrefix: String, modifier: JavaModifier) =
        object : ArchCondition<JavaClass>("have exactly one public method named '$methodNamePrefix'") {
            override fun check(javaClass: JavaClass, events: ConditionEvents) {
                val publicMethods = javaClass
                    .methods
                    .filter { it.modifiers.contains(modifier) }
                    .toList()

                var satisfied = false
                var message = javaClass.name + " contains " + publicMethods.size + " ${modifier.name} method"
                val methodCount = 1

                if (publicMethods.size == methodCount) {
                    val method = publicMethods[0]
                    satisfied = method.name.startsWith(methodNamePrefix)
                    message += " named '${method.name}' ${method.sourceCodeLocation}"
                } else {
                    val publicMethodNames = publicMethods.joinToString { it.name }
                    message += "s ($publicMethodNames)" + javaClass.sourceCodeLocation
                }

                events.add(SimpleConditionEvent(javaClass, satisfied, message))
            }
        }

    fun haveExactlyOneField(fieldName: String, modifier: JavaModifier) =
        object : ArchCondition<JavaClass>("have exactly one member named '$fieldName'") {
            override fun check(javaClass: JavaClass, events: ConditionEvents) {
                val field = javaClass
                    .fields
                    .firstOrNull { it.modifiers.contains(modifier) }

                var satisfied = false
                var message = ""

                if (field != null) {
                    satisfied = true
                    message += javaClass.name + " contains '$fieldName' field " + field.sourceCodeLocation
                } else {
                    message += "javaClass.name does not contain '$fieldName' field " + javaClass.sourceCodeLocation
                }

                events.add(SimpleConditionEvent(javaClass, satisfied, message))
            }
        }
}
