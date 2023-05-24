package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal class KoPropertyDeclarationImpl private constructor(private val ktProperty: KtProperty, parentDeclaration: KoBaseDeclaration?) :
    KoDeclarationImpl(ktProperty, parentDeclaration),
    KoPropertyDeclaration {
    override val isVar by lazy { ktProperty.isVar }

    override val isVal by lazy { !ktProperty.isVar }

    override val delegateName by lazy {
        ktProperty
            .delegate
            ?.text
            ?.replace("\n", " ")
            ?.substringAfter("by ")
            ?.substringBefore("{")
            ?.removeSuffix(" ")
    }

    override val type by lazy {
        val type = ktProperty
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoTypeDeclarationImpl.getInstance(it, this) }
    }

    override fun hasLateinitModifier() = hasModifiers(KoModifier.LATEINIT)

    override fun hasOverrideModifier() = hasModifiers(KoModifier.OVERRIDE)

    override fun hasAbstractModifier() = hasModifiers(KoModifier.ABSTRACT)

    override fun hasOpenModifier() = hasModifiers(KoModifier.OPEN)

    override fun hasFinalModifier() = hasModifiers(KoModifier.FINAL)

    override fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    override fun hasConstModifier() = hasModifiers(KoModifier.CONST)

    override fun isExtension() = ktProperty.isExtensionDeclaration()

    override fun hasDelegate(name: String?): Boolean = when (name) {
        null -> ktProperty.hasDelegateExpression()
        else -> delegateName == name
    }

    override fun hasType(type: String?) = when (type) {
        null -> this.type != null
        else -> this.type?.name == type
    }

    internal companion object {
        private val cache = KoDeclarationCache<KoPropertyDeclarationImpl>()

        internal fun getInstance(ktProperty: KtProperty, parentDeclaration: KoBaseDeclaration?) =
            cache.getOrCreateInstance(ktProperty, parentDeclaration) {
                KoPropertyDeclarationImpl(ktProperty, parentDeclaration)
            }
    }
}
