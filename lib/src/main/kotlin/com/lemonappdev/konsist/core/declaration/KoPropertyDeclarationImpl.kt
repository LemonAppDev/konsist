package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal class KoPropertyDeclarationImpl private constructor(private val ktProperty: KtProperty, parent: KoBaseDeclaration?) :
    KoDeclarationImpl(ktProperty, parent),
    KoPropertyDeclaration {
    override val isVar: Boolean by lazy { ktProperty.isVar }

    override val isVal: Boolean by lazy { !ktProperty.isVar }

    override val delegateName: String? by lazy {
        ktProperty
            .delegate
            ?.text
            ?.replace("\n", " ")
            ?.substringAfter("by ")
            ?.substringBefore("{")
            ?.removeSuffix(" ")
    }

    override val type: KoTypeDeclaration? by lazy {
        val type = ktProperty
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoTypeDeclarationImpl.getInstance(it, this) }
    }

    override fun hasLateinitModifier(): Boolean = hasModifiers(KoModifier.LATEINIT)

    override fun hasOverrideModifier(): Boolean = hasModifiers(KoModifier.OVERRIDE)

    override fun hasAbstractModifier(): Boolean = hasModifiers(KoModifier.ABSTRACT)

    override fun hasOpenModifier(): Boolean = hasModifiers(KoModifier.OPEN)

    override fun hasFinalModifier(): Boolean = hasModifiers(KoModifier.FINAL)

    override fun hasActualModifier(): Boolean = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier(): Boolean = hasModifiers(KoModifier.EXPECT)

    override fun hasConstModifier(): Boolean = hasModifiers(KoModifier.CONST)

    override fun isExtension(): Boolean = ktProperty.isExtensionDeclaration()

    override fun hasDelegate(name: String?): Boolean = when (name) {
        null -> ktProperty.hasDelegateExpression()
        else -> delegateName == name
    }

    override fun hasType(type: String?): Boolean = when (type) {
        null -> this.type != null
        else -> this.type?.name == type
    }

    internal companion object {
        private val cache = KoDeclarationCache<KoPropertyDeclarationImpl>()

        internal fun getInstance(ktProperty: KtProperty, parent: KoBaseDeclaration): KoPropertyDeclaration = cache.getOrCreateInstance(ktProperty, parent) {
            KoPropertyDeclarationImpl(ktProperty, parent)
        }
    }
}
