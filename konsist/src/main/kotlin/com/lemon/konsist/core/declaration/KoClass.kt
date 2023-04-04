package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.psiUtil.isAbstract
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoClass private constructor(private val ktClass: KtClass) : KoComplexDeclaration(ktClass) {
    val isEnum by lazy { ktClass.isEnum() }

    val isSealed by lazy { ktClass.isSealed() }

    val isInner by lazy { ktClass.isInner() }

    val isValue by lazy { ktClass.isValue() }

    val isData by lazy { ktClass.isData() }

    val isAnnotation by lazy { ktClass.isAnnotation() }

    val isAbstract by lazy { ktClass.isAbstract() }

    val isOpen by lazy {
        ktClass
            .children
            .firstIsInstanceOrNull<KtModifierList>()
            ?.hasModifier(KtTokens.OPEN_KEYWORD)
            ?: false
    }

    val isFinal by lazy {
        ktClass
            .children
            .firstIsInstanceOrNull<KtModifierList>()
            ?.hasModifier(KtTokens.FINAL_KEYWORD)
            ?: false
    }

    val primaryConstructor by lazy {
        val localPrimaryConstructor = ktClass.primaryConstructor ?: return@lazy null

        KoPrimaryConstructor.getInstance(localPrimaryConstructor)
    }

    val hasExplicitPrimaryConstructor = ktClass.hasExplicitPrimaryConstructor()

    val secondaryConstructors by lazy {
        ktClass
            .secondaryConstructors
            .map { KoSecondaryConstructor.getInstance(it) }
    }

    val hasSecondaryConstructors = ktClass.hasSecondaryConstructors()

    val allConstructors = listOfNotNull(primaryConstructor) + secondaryConstructors

    companion object {
        private val cache = KoDeclarationCache<KoClass>()
        fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoClass(ktClass) }
    }
}
