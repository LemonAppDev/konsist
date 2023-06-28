package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoPropertyDeclarationImpl private constructor(private val ktProperty: KtProperty, parentDeclaration: KoBaseDeclaration?) :
    KoDeclarationImpl(ktProperty, parentDeclaration),
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

    override val type: KoTypeDeclaration? by lazy { ReceiverUtil.setType(getTypeReferences(), isExtension(), this) }

    override val receiverType: KoTypeDeclaration? by lazy { ReceiverUtil.setReceiverType(getTypeReferences(), isExtension(), this) }

    private fun getTypeReferences() = ktProperty
        .children
        .filterIsInstance<KtTypeReference>()

    override fun hasLateinitModifier(): Boolean = hasModifiers(KoModifier.LATEINIT)

    override fun hasOverrideModifier(): Boolean = hasModifiers(KoModifier.OVERRIDE)

    override fun hasAbstractModifier(): Boolean = hasModifiers(KoModifier.ABSTRACT)

    override fun hasOpenModifier(): Boolean = hasModifiers(KoModifier.OPEN)

    override fun hasFinalModifier(): Boolean = hasModifiers(KoModifier.FINAL)

    override fun hasActualModifier(): Boolean = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier(): Boolean = hasModifiers(KoModifier.EXPECT)

    override fun hasConstModifier(): Boolean = hasModifiers(KoModifier.CONST)

    override fun isExtension(): Boolean = ktProperty.isExtensionDeclaration()

    override fun hasReceiverType(name: String?): Boolean = ReceiverUtil.hasReceiverType(receiverType, name)

    override fun hasDelegate(name: String?): Boolean = when (name) {
        null -> ktProperty.hasDelegateExpression()
        else -> delegateName == name
    }

    override fun hasType(type: String?): Boolean = when (type) {
        null -> this.type != null
        else -> this.type?.name == type
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPropertyDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktProperty: KtProperty, parentDeclaration: KoBaseDeclaration?): KoPropertyDeclaration =
            cache.getOrCreateInstance(ktProperty, parentDeclaration) {
                KoPropertyDeclarationImpl(ktProperty, parentDeclaration)
            }
    }
}
