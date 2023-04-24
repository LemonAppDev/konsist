package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.const.KoModifier
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.hasActualModifier
import org.jetbrains.kotlin.psi.psiUtil.hasExpectModifier
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

    fun hasLateinitModifier() = modifiers.contains(KoModifier.LATEINIT)

    fun hasOverrideModifier() = modifiers.contains(KoModifier.OVERRIDE)

    fun hasAbstractModifier() =modifiers.contains(KoModifier.ABSTRACT)

    fun hasOpenModifier() = modifiers.contains(KoModifier.OPEN)

    fun hasFinalModifier() = modifiers.contains(KoModifier.FINAL)

    fun hasActualModifier() = ktProperty.hasActualModifier()

    fun hasExpectModifier() = ktProperty.hasExpectModifier()

    fun hasConstModifier() = modifiers.contains(KoModifier.CONST)

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
