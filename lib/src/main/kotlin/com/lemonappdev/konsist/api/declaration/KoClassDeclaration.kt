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
     */
    fun hasEnumModifier(): Boolean

    /**
     * Whatever class has 'sealed' modifier.
     */
    fun hasSealedModifier(): Boolean

    /**
     * Whatever class has 'inner' modifier.
     */
    fun hasInnerModifier(): Boolean

    /**
     * Whatever class has 'value' modifier.
     */
    fun hasValueModifier(): Boolean

    /**
     * Whatever class has 'annotation' modifier.
     */
    fun hasAnnotationModifier(): Boolean

    /**
     * Whatever class has 'data' modifier.
     */
    fun hasDataModifier(): Boolean

    /**
     * Whatever class has 'actual' modifier.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whatever class has 'expect' modifier.
     */
    fun hasExpectModifier(): Boolean

    /**
     * Whatever class has 'abstract' modifier.
     */
    fun hasAbstractModifier(): Boolean

    /**
     * Whatever class has 'open' modifier.
     */
    fun hasOpenModifier(): Boolean

    /**
     * Whatever class has 'final' modifier.
     */
    fun hasFinalModifier(): Boolean

    /**
     * Whatever class has promary constructor.
     */
    fun hasPrimaryConstructor(): Boolean

    /**
     * Whatever class has secondary constructors.
     */
    fun hasSecondaryConstructors(): Boolean

    /**
     * Whatever class has parent class.
     */
    fun hasParentClass(name: String? = null): Boolean

    /**
     * Whatever class has parent interfaces.
     */
    fun hasParentInterfaces(vararg names: String): Boolean

    /**
     * Whatever class has parents.
     */
    fun hasParents(vararg names: String): Boolean

    /**
     * Whatever class has test.
     *
     * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return `true` if the class has a test, `false` otherwise.
     */
    fun hasTest(testFileNameSuffix: String = "Test", moduleName: String? = null, sourceSetName: String? = null): Boolean
}
