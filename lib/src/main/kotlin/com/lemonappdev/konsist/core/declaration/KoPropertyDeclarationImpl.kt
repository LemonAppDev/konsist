package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.KoModifier
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoPropertyDeclarationImpl private constructor(private val ktProperty: KtProperty) : KoDeclarationImpl(ktProperty) {
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

    val type by lazy {
        val type = ktProperty
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoTypeDeclarationImpl.getInstance(it) }
    }

    fun hasLateinitModifier() = hasModifiers(KoModifier.LATEINIT)

    fun hasOverrideModifier() = hasModifiers(KoModifier.OVERRIDE)

    fun hasAbstractModifier() = hasModifiers(KoModifier.ABSTRACT)

    fun hasOpenModifier() = hasModifiers(KoModifier.OPEN)

    fun hasFinalModifier() = hasModifiers(KoModifier.FINAL)

    fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    fun hasConstModifier() = hasModifiers(KoModifier.CONST)

    fun isExtension() = ktProperty.isExtensionDeclaration()

    fun hasDelegate(name: String? = null): Boolean = when (name) {
        null -> ktProperty.hasDelegateExpression()
        else -> delegateName == name
    }

    fun hasType(type: String? = null) = when (type) {
        null -> this.type != null
        else -> this.type?.name == type
    }

    companion object {
        private val cache = KoDeclarationCache<KoPropertyDeclarationImpl>()

        fun getInstance(ktProperty: KtProperty) = cache.getOrCreateInstance(ktProperty) { KoPropertyDeclarationImpl(ktProperty) }
    }
}
