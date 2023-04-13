package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.psiUtil.getSuperNames
import org.jetbrains.kotlin.psi.psiUtil.isAbstract
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

@Suppress("detekt.TooManyFunctions")
class KoClass private constructor(private val ktClass: KtClass) : KoComplexDeclaration(ktClass) {
    val parents by lazy { ktClass.getSuperNames() }

    val parentInterfaces by lazy {
        val imports = ktClass
            .children

        if (imports.isNotEmpty()) {
            imports
                .first()
                .children
                .map { it.text }
                .filter { !it.contains('(') }
        } else {
            listOf()
        }
    }

    val parentClass: String? by lazy {
        (parents - parentInterfaces.toSet())
            .firstOrNull()
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

    fun hasParentClass() = parentClass != null

    fun hasParentClass(name: String) = parentClass == name

    fun hasParentInterface() = parentInterfaces.isNotEmpty()

    fun hasParentInterface(name: String) = parentInterfaces.any { it == name }

    fun hasParent() = hasParentClass() || hasParentInterface()

    companion object {
        private val cache = KoDeclarationCache<KoClass>()

        fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoClass(ktClass) }
    }
}
