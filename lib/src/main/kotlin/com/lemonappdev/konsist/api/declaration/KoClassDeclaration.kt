package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin class declaration.
 */
interface KoClassDeclaration : KoComplexDeclaration {
    /**
     * The parents of the class.
     */
    val parents: List<KoParentDeclaration>

    /**
     * The parent interfaces of the class.
     */
    val parentInterfaces: List<KoParentDeclaration>

    /**
     * The parent class of the class.
     */
    val parentClass: KoParentDeclaration?

    /**
     * The parent interfaces of the class.
     */
    val primaryConstructor: KoPrimaryConstructorDeclaration?

    /**
     * The secondary constructors of the class.
     */
    val secondaryConstructors: List<KoSecondaryConstructorDeclaration>

    /**
     * The all primary and secondary constructors of the class.
     */
    val allConstructors: List<KoConstructorDeclaration>

    /**
     * Whatever class has 'enum' modifier.
     *
     * @return `true` if the declaration has the 'enum' modifier, `false` otherwise.
     */
    fun hasEnumModifier(): Boolean

    /**
     * Whatever class has 'sealed' modifier.
     *
     * @return `true` if the declaration has the 'sealed' modifier, `false` otherwise.
     */
    fun hasSealedModifier(): Boolean

    /**
     * Whatever class has 'inner' modifier.
     *
     * @return `true` if the declaration has the 'inner' modifier, `false` otherwise.
     */
    fun hasInnerModifier(): Boolean

    /**
     * Whatever class has 'value' modifier.
     *
     * @return `true` if the declaration has the 'value' modifier, `false` otherwise.
     */
    fun hasValueModifier(): Boolean

    /**
     * Whatever class has 'annotation' modifier.
     *
     * @return `true` if the declaration has the 'annotation' modifier, `false` otherwise.
     */
    fun hasAnnotationModifier(): Boolean

    /**
     * Whatever class has 'data' modifier.
     *
     * @return `true` if the declaration has the 'data' modifier, `false` otherwise.
     */
    fun hasDataModifier(): Boolean

    /**
     * Whatever class has 'actual' modifier.
     *
     * @return `true` if the declaration has the 'actual' modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whatever class has 'expect' modifier.
     *
     * @return `true` if the declaration has the 'expect' modifier, `false` otherwise.
     */
    fun hasExpectModifier(): Boolean

    /**
     * Whatever class has 'abstract' modifier.
     *
     * @return `true` if the declaration has the 'abstract' modifier, `false` otherwise.
     */
    fun hasAbstractModifier(): Boolean

    /**
     * Whatever class has 'open' modifier.
     *
     * @return `true` if the declaration has the 'open' modifier, `false` otherwise.
     */
    fun hasOpenModifier(): Boolean

    /**
     * Whatever class has 'final' modifier.
     *
     * @return `true` if the declaration has the 'final' modifier, `false` otherwise.
     */
    fun hasFinalModifier(): Boolean

    /**
     * Whatever class has primary constructor.
     *
     * @return `true` if the declaration has primary constructor, `false` otherwise.
     */
    fun hasPrimaryConstructor(): Boolean

    /**
     * Whatever class has secondary constructors.
     *
     * @return `true` if the declaration has secondary constructors, `false` otherwise.
     */
    fun hasSecondaryConstructors(): Boolean

    /**
     * Whatever class has parent class.
     *
     * @param name the name of the parent class to check (optional).
     * @return `true` if the class has the specified parent class (or any parent class if `name` is `null`), `false` otherwise.
     */
    fun hasParentClass(name: String? = null): Boolean

    /**
     * Whatever class has parent interfaces.
     *
     * @param names the names of the parent interfaces to check.
     * @return `true` if the class has parent interfaces with the specified names (or any parent interface if `names` is empty),
     * `false` otherwise.
     */
    fun hasParentInterfaces(vararg names: String): Boolean

    /**
     * Whatever class has parents.
     *
     * @param names the names of the parents to check.
     * @return `true` if the class has parents with the specified names (or any parent if `names` is empty), `false` otherwise.
     */
    fun hasParents(vararg names: String): Boolean

    /**
     * Whatever class has a Test.
     *
     * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return `true` if the class has a test, `false` otherwise.
     */
    fun hasTest(testFileNameSuffix: String = "Test", moduleName: String? = null, sourceSetName: String? = null): Boolean
}
