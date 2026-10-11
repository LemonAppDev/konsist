package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * Interface for providing tacit type information.
 *
 * The tacit type is the explicitly declared type or, when the type is omitted, the type inferred from
 * the constructor call that initializes the declaration.
 */
interface KoTacitTypeProvider : KoBaseProvider {
    /**
     * The explicitly declared type or, when the type is omitted, the type of the constructor call that
     * initializes the declaration. A constructor call is a call whose name starts with an uppercase letter.
     * It can be qualified (`com.app.Foo()`, `Outer.Inner()`) and wrapped in `apply` or `also`.
     *
     * e.g.
     *
     * ```kotlin
     * val sampleProperty: Foo = someCollection.first() // tacitType.name == "Foo"
     * val sampleProperty = Foo("some text") // tacitType.name == "Foo"
     * val sampleProperty = Foo<Bar>().apply { init() } // tacitType.name == "Foo<Bar>"
     * val sampleProperty = listOf(Foo()) // tacitType == null
     * val sampleProperty = someCollection.first() // tacitType == null
     * ```
     */
    val tacitType: KoTypeDeclaration?

    /**
     * Determines whether the declaration has a tacit type with the given name.
     * The name is compared with and without type arguments.
     *
     * e.g.
     *
     * ```kotlin
     * val sampleProperty: Foo = someCollection.first() // hasTacitType("Foo") == true
     * val sampleProperty = Foo("some text") // hasTacitType("Foo") == true
     * val sampleProperty = Foo<Bar>() // hasTacitType("Foo") == true, hasTacitType("Foo<Bar>") == true
     * val sampleProperty = FooBar() // hasTacitType("Foo") == false
     * val sampleProperty = listOf(Foo()) // hasTacitType("Foo") == false
     * ```
     *
     * @param type The type to check.
     * @return `true` if the tacit type has the given name; `false` otherwise.
     */
    fun hasTacitType(type: String): Boolean

    /**
     * Determines whether the declaration has a tacit type of the specified Kotlin class.
     * The type is compared by its fully qualified name.
     *
     * @param kClass The Kotlin class to check.
     * @return `true` if the tacit type is the given class; `false` otherwise.
     */
    fun hasTacitTypeOf(kClass: KClass<*>): Boolean
}
