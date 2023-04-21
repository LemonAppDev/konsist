package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoProperty private constructor(private val ktProperty: KtProperty) : KoDeclaration(ktProperty) {
    val isVar by lazy { ktProperty.isVar }

    val isVal by lazy { !ktProperty.isVar }

    val delegateName by lazy {
        ktProperty
            .delegate
            ?.text
            ?.replace("\n", " ")
            ?.substringAfter("by ")
            ?.substringBefore("{")
            ?.removeSuffix(" ")
    }

    val explicitType by lazy {
        val type = ktProperty
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoType.getInstance(it) }
    }

    fun hasLateinitModifier() = ktProperty.modifierList?.hasModifier(KtTokens.LATEINIT_KEYWORD) ?: false

    fun hasOverrideModifier() = ktProperty.modifierList?.hasModifier(KtTokens.OVERRIDE_KEYWORD) ?: false

    fun hasAbstractModifier() = ktProperty.modifierList?.hasModifier(KtTokens.ABSTRACT_KEYWORD) ?: false

    fun hasOpenModifier() = ktProperty.modifierList?.hasModifier(KtTokens.OPEN_KEYWORD) ?: false

    fun hasFinalModifier() = ktProperty.modifierList?.hasModifier(KtTokens.FINAL_KEYWORD) ?: false

    fun hasActualModifier() = ktProperty.modifierList?.hasModifier(KtTokens.ACTUAL_KEYWORD) ?: false

    fun hasExpectModifier() = ktProperty.modifierList?.hasModifier(KtTokens.EXPECT_KEYWORD) ?: false

    fun hasConstModifier() = ktProperty
        .children
        .firstIsInstanceOrNull<KtModifierList>()
        ?.hasModifier(KtTokens.CONST_KEYWORD)
        ?: false

    fun isExtension() = ktProperty.isExtensionDeclaration()

    fun hasDelegate(name: String? = null): Boolean = when (name) {
        null -> ktProperty.hasDelegateExpression()
        else -> delegateName == name
    }

    fun hasExplicitType(type: String? = null) = when (type) {
        null -> explicitType != null
        else -> explicitType?.name == type
    }

    companion object {
        private val cache = KoDeclarationCache<KoProperty>()

        fun getInstance(ktProperty: KtProperty) = cache.getOrCreateInstance(ktProperty) { KoProperty(ktProperty) }
    }
}
