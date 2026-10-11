package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a declaration that provides information about whether it is a mutable type.
 */
interface KoIsMutableTypeProvider : KoBaseProvider {
    /**
     * Determines whether the declaration is a mutable type.
     *
     * Type aliases and import aliases are resolved to the type they point to, so for
     * `typealias Items = MutableList<String>` value will be `true` and for `typealias MutableCallback = () -> Unit`
     * value will be `false`.
     *
     * A type is considered mutable when it is:
     * - a Kotlin or external type whose name starts with "Mutable", such as `MutableList`, `MutableStateFlow`,
     * `MutableLiveData`, etc.
     * - a known mutable Kotlin or Java type, such as `ArrayList`, `HashMap`, `Array`, `IntArray`, `StringBuilder`,
     * `AtomicInteger`, `ConcurrentHashMap`, etc.
     * - a class, interface or object declared in the project whose name starts with "Mutable", that declares
     * a `var` property (including `var` constructor parameters and private properties), or whose parent is mutable,
     * such as `class Foo(var bar: Int)` or `class Foo : ArrayList<String>()`. Properties declared in nested
     * declarations (including companion objects) are not checked.
     *
     * Only the type itself is checked, so for `List<MutableList<String>>` value will be `false`.
     *
     * @see hasMutableTypeArgument
     */
    val isMutableType: Boolean

    /**
     * Determines whether any type argument of the declaration, at any nesting level, is a mutable type.
     *
     * For `List<MutableList<String>>` and `Map<String, List<ArrayList<Int>>>` value will be `true`.
     * For `MutableList<String>` value will be `false`.
     *
     * @see isMutableType
     */
    val hasMutableTypeArgument: Boolean
}
