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
     * a `var` property (including `var` constructor parameters and private properties), that declares a `val`
     * property whose type is mutable or has a mutable type argument (checked recursively), or whose parent is mutable,
     * such as `class Foo(var bar: Int)`, `class Foo(val bar: MutableList<String>)`,
     * `class Foo(val bar: List<HashMap<String, Int>>)` or `class Foo : ArrayList<String>()`. Properties declared in
     * nested declarations (including companion objects) and properties with an inferred type are not checked.
     *
     * Only the type itself is checked, so for `List<MutableList<String>>` value will be `false`.
     *
     * @see hasMutableTypeArgument
     * @see isImmutableType
     */
    val isMutableType: Boolean

    /**
     * Determines whether any type argument of the declaration, at any nesting level, is a mutable type.
     *
     * For `List<MutableList<String>>` and `Map<String, List<ArrayList<Int>>>` value will be `true`.
     * For `MutableList<String>` value will be `false`.
     *
     * @see isMutableType
     * @see hasImmutableTypeArgument
     */
    val hasMutableTypeArgument: Boolean

    /**
     * Determines whether the declaration is an immutable type.
     *
     * A type is considered immutable when it is:
     * - a Kotlin type, or a class, interface or object declared in the project, that is not a mutable type
     * (see [isMutableType]). Read-only types, such as `List` or `Map`, are considered immutable.
     * - a known immutable external type, such as `java.lang.Integer`, `java.math.BigDecimal`, `java.util.UUID`,
     * `java.time.LocalDate` (any type from `java.time` or `kotlinx.datetime` package), `kotlin.time.Duration`,
     * `kotlinx.collections.immutable.ImmutableList`, etc.
     *
     * Type aliases and import aliases are resolved to the type they point to, so for `typealias Items = List<String>`
     * value will be `true` and for `typealias Items = MutableList<String>` value will be `false`.
     *
     * Mutability of types that cannot be determined, such as type parameters (`T`), function types (`() -> Unit`),
     * unresolved types and external types not known to be immutable (e.g. `java.util.Date`), is unknown, so for them
     * both `isMutableType` and `isImmutableType` are `false`.
     *
     * Only the type itself is checked, so for `List<MutableList<String>>` value will be `true`.
     *
     * @see hasImmutableTypeArgument
     * @see isMutableType
     */
    val isImmutableType: Boolean

    /**
     * Determines whether any type argument of the declaration, at any nesting level, is an immutable type.
     *
     * For `MutableList<String>` and `MutableList<MutableSet<String>>` value will be `true`.
     * For `MutableList<MutableSet<T>>`, `List<() -> Unit>` and `String` value will be `false`.
     *
     * @see isImmutableType
     * @see hasMutableTypeArgument
     */
    val hasImmutableTypeArgument: Boolean
}
