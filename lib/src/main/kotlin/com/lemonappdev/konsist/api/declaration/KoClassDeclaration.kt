package com.lemonappdev.konsist.api.declaration

interface KoClassDeclaration : KoComplexDeclaration {
    val parents: List<KoParentDeclaration>

    val parentInterfaces: List<KoParentDeclaration>

    val parentClass: KoParentDeclaration?

    val primaryConstructor: KoPrimaryConstructorDeclaration?

    val secondaryConstructors: List<KoSecondaryConstructorDeclaration>

    val allConstructors: List<KoConstructorDeclaration>

    fun hasEnumModifier(): Boolean

    fun hasSealedModifier(): Boolean

    fun hasInnerModifier(): Boolean

    fun hasValueModifier(): Boolean

    fun hasAnnotationModifier(): Boolean

    fun hasDataModifier(): Boolean

    fun hasActualModifier(): Boolean

    fun hasExpectModifier(): Boolean

    fun hasAbstractModifier(): Boolean

    fun hasOpenModifier(): Boolean

    fun hasFinalModifier(): Boolean

    fun hasPrimaryConstructor(): Boolean

    fun hasSecondaryConstructors(): Boolean

    fun hasParentClass(name: String? = null): Boolean

    fun hasParentInterfaces(vararg names: String): Boolean

    fun hasParents(vararg names: String): Boolean
}
