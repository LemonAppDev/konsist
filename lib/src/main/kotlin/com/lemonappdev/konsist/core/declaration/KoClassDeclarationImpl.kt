package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoClassDeclarationImpl private constructor(private val ktClass: KtClass, parent: KoBaseDeclaration) :
    KoComplexDeclarationImpl(ktClass, parent),
    KoClassDeclaration {
    override val parents by lazy {
        ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParentDeclarationImpl.getInstance(it, this) } ?: emptyList()
    }

    override val parentInterfaces by lazy {
        val interfaces = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeEntry>() ?: emptyList()

        val delegations = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtDelegatedSuperTypeEntry>() ?: emptyList()

        val all = interfaces + delegations
        all.map { KoParentDeclarationImpl.getInstance(it, this) }
    }

    override val parentClass by lazy {
        val parentClass = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeCallEntry>()
            ?.first()

        parentClass?.let { KoParentDeclarationImpl.getInstance(it, this) }
    }

    override val primaryConstructor by lazy {
        val localPrimaryConstructor = ktClass.primaryConstructor ?: return@lazy null

        KoPrimaryConstructorDeclarationImpl.getInstance(localPrimaryConstructor, this)
    }

    override val secondaryConstructors by lazy {
        ktClass
            .secondaryConstructors
            .map { KoSecondaryConstructorDeclarationImpl.getInstance(it, this) }
    }

    override val allConstructors = listOfNotNull(primaryConstructor) + secondaryConstructors

    override fun hasEnumModifier() = hasModifiers(KoModifier.ENUM)

    override fun hasSealedModifier() = hasModifiers(KoModifier.SEALED)

    override fun hasInnerModifier() = hasModifiers(KoModifier.INNER)

    override fun hasValueModifier() = hasModifiers(KoModifier.VALUE)

    override fun hasAnnotationModifier() = hasModifiers(KoModifier.ANNOTATION)

    override fun hasDataModifier() = hasModifiers(KoModifier.DATA)

    override fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    override fun hasAbstractModifier() = hasModifiers(KoModifier.ABSTRACT)

    override fun hasOpenModifier() = hasModifiers(KoModifier.OPEN)

    override fun hasFinalModifier() = hasModifiers(KoModifier.FINAL)

    override fun hasPrimaryConstructor() = ktClass.hasExplicitPrimaryConstructor()

    override fun hasSecondaryConstructors() = ktClass.hasSecondaryConstructors()

    override fun hasParentClass(name: String?) = when (name) {
        null -> parentClass != null
        else -> parentClass?.name == name
    }

    override fun hasParentInterfaces(vararg names: String) = when {
        names.isEmpty() -> parentInterfaces.isNotEmpty()
        else -> names.all {
            parentInterfaces.any { koParent -> it == koParent.name }
        }
    }

    override fun hasParents(vararg names: String) = when {
        names.isEmpty() -> hasParentClass() || hasParentInterfaces()
        else -> names.all { hasParentClass(it) || hasParentInterfaces(it) }
    }

    internal companion object {
        private val cache = KoDeclarationCache<KoClassDeclarationImpl>()

        internal fun getInstance(ktClass: KtClass, parent: KoBaseDeclaration) = cache.getOrCreateInstance(ktClass, parent) {
            KoClassDeclarationImpl(ktClass, parent)
        }
    }
}
