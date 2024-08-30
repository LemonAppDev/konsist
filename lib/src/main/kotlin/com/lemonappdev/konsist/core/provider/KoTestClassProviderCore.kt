package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoTestClassProvider
import com.lemonappdev.konsist.api.provider.KoValueProvider

internal interface KoTestClassProviderCore :
    KoTestClassProvider,
    KoNameProviderCore,
    KoBaseProviderCore {
    override fun testClasses(
        testPropertyName: String,
        moduleName: String?,
        sourceSetName: String?,
    ): List<KoClassDeclaration> =
        Konsist
            .scopeFromTest(moduleName, sourceSetName)
            .classes()
            .filter {
                it.hasProperty { property -> property.isInstanceCreatedInProperty(testPropertyName, name) } ||
                    it.hasFunction { function -> function.isInstanceCreatedInMethodBody(testPropertyName, name) }
            }

    override fun testClasses(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): List<KoClassDeclaration> =
        Konsist
            .scopeFromTest(moduleName, sourceSetName)
            .classes()
            .filter { predicate(it) }

    override fun numTestClasses(
        testPropertyName: String,
        moduleName: String?,
        sourceSetName: String?,
    ): Int = testClasses(testPropertyName, moduleName, sourceSetName).size

    override fun countTestClasses(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int = testClasses(moduleName, sourceSetName, predicate).size

    override fun hasTestClasses(
        testPropertyName: String,
        moduleName: String?,
        sourceSetName: String?,
    ): Boolean = testClasses(testPropertyName, moduleName, sourceSetName).isNotEmpty()

    override fun hasTestClass(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean = testClasses(moduleName, sourceSetName, predicate).isNotEmpty()
}

/*
 Checks whether a type is defined explicitly or implicitly, e.g.
 val x1 = SampleClass()                 // hasTacitType returns true
 val x2: SampleClass = getInstance()    // hasTacitType returns true

 but
 val x3 = getInstance()                 // hasTacitType returns false
 */
private fun <T> T.hasTacitType(
    type: String,
): Boolean where
                 T : KoNullableTypeProvider,
                 T : KoNameProvider,
                 T : KoValueProvider =
    hasType { it.name == type } || value?.startsWith("$type(") == true

/*
 Checks whether a test item is created in a property (property has item tacit type or is created in getter body), e.g.
 val x1 = SampleClass()                 // isInstanceCreatedInProperty = true
 val x2: SampleClass = getInstance()    // isInstanceCreatedInProperty = true
 val x3
    get() = SampleClass()               // isInstanceCreatedInProperty = true

 but
 val x4 = getInstance()                 // isInstanceCreatedInProperty = false
 */
private fun KoPropertyDeclaration.isInstanceCreatedInProperty(
    name: String,
    type: String,
): Boolean = this.name == name && (hasTacitType(type) || getter?.text?.contains(" $type(") == true)

/*
 Checks whether a test item is created in a method body (function return type matches to item type or item is created as variable), e.g.
 fun sampleFunction1() {
    val x1 = SampleClass()
 }                                     // isInstanceCreatedInMethodBody = true

 fun sampleFunction2(): SampleClass {
    ...
 }                                     // isInstanceCreatedInMethodBody = true

 but
 fun sampleFunction3() {
    val x3 = getInstance()
 }                                     // isInstanceCreatedInMethodBody = false
 */
private fun KoFunctionDeclaration.isInstanceCreatedInMethodBody(
    name: String,
    type: String,
): Boolean = hasReturnType { it.name == type } || hasVariable { it.name == name && it.hasTacitType(type) }
