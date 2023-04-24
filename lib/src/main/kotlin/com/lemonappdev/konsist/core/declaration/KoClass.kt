package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.const.KoModifier
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

@Suppress("detekt.TooManyFunctions")
class KoClass private constructor(private val ktClass: KtClass) : KoComplexDeclaration(ktClass) {
    val parents by lazy {
        ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParent.getInstance(it) } ?: emptyList()
    }

    val parentInterfaces by lazy {
        val interfaces = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeEntry>() ?: emptyList()

        val delegations = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtDelegatedSuperTypeEntry>() ?: emptyList()

        val all = interfaces + delegations
        all.map { KoParent.getInstance(it) }
    }

    val parentClass by lazy {
        val parentClass = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeCallEntry>()
            ?.first()

        parentClass?.let { KoParent.getInstance(it) }
    }

    val primaryConstructor by lazy {
        val localPrimaryConstructor = ktClass.primaryConstructor ?: return@lazy null

        KoPrimaryConstructor.getInstance(localPrimaryConstructor)
    }

    val secondaryConstructors by lazy {
        ktClass
            .secondaryConstructors
            .map { KoSecondaryConstructor.getInstance(it) }
    }

    val allConstructors = listOfNotNull(primaryConstructor) + secondaryConstructors

    fun hasEnumModifier() = hasModifiers(KoModifier.ENUM)

    fun hasSealedModifier() = hasModifiers(KoModifier.SEALED)

    fun hasInnerModifier() = hasModifiers(KoModifier.INNER)

    fun hasValueModifier() = hasModifiers(KoModifier.VALUE)

    fun hasAnnotationModifier() = hasModifiers(KoModifier.ANNOTATION)

    fun hasDataModifier() = hasModifiers(KoModifier.DATA)

    fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    fun hasAbstractModifier() = hasModifiers(KoModifier.ABSTRACT)

    fun hasOpenModifier() = hasModifiers(KoModifier.OPEN)

    fun hasFinalModifier() = hasModifiers(KoModifier.FINAL)

    fun hasPrimaryConstructor() = ktClass.hasExplicitPrimaryConstructor()

    fun hasSecondaryConstructors() = ktClass.hasSecondaryConstructors()

    fun hasParentClass(name: String? = null) = when (name) {
        null -> parentClass != null
        else -> parentClass?.name == name
    }

    fun hasParentInterface(name: String? = null) = when (name) {
        null -> parentInterfaces.isNotEmpty()
        else -> parentInterfaces.any { it.name == name }
    }

    fun hasParent(name: String? = null) = hasParentClass(name) || hasParentInterface(name)

    companion object {
        private val cache = KoDeclarationCache<KoClass>()

        fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoClass(ktClass) }
    }
}
