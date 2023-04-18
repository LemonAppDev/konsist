package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.psiUtil.isAbstract
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

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

    fun hasEnumModifier() = ktClass.isEnum()

    fun hasSealedModifier() = ktClass.isSealed()

    fun hasInnerModifier() = ktClass.isInner()

    fun hasValueModifier() = ktClass.isValue()

    fun hasAnnotationModifier() = ktClass.isAnnotation()

    fun hasDataModifier() = ktClass.isData()

    fun hasActualModifier() = ktClass.modifierList?.hasModifier(KtTokens.ACTUAL_KEYWORD) ?: false

    fun hasExpectModifier() = ktClass.modifierList?.hasModifier(KtTokens.EXPECT_KEYWORD) ?: false

    fun hasAbstractModifier() = ktClass.isAbstract()

    fun hasOpenModifier() = ktClass
        .children
        .firstIsInstanceOrNull<KtModifierList>()
        ?.hasModifier(KtTokens.OPEN_KEYWORD)
        ?: false

    fun hasFinalModifier() = ktClass
        .children
        .firstIsInstanceOrNull<KtModifierList>()
        ?.hasModifier(KtTokens.FINAL_KEYWORD)
        ?: false

    fun hasExplicitPrimaryConstructor() = ktClass.hasExplicitPrimaryConstructor()

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
