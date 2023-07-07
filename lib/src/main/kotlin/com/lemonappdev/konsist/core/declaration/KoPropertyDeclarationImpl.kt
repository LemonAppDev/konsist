package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal class KoPropertyDeclarationImpl private constructor(private val ktProperty: KtProperty, parentDeclaration: KoParentProvider?) :
    KoPropertyDeclaration,
    KoBaseDeclarationImpl(ktProperty),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktProperty

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

    override val type: KoTypeDeclaration? by lazy { ReceiverUtil.getType(getTypeReferences(), isExtension(), this) }

    override val receiverType: KoTypeDeclaration? by lazy { ReceiverUtil.getReceiverType(getTypeReferences(), isExtension(), this) }

    private fun getTypeReferences(): List<KtTypeReference> = ktProperty
        .children
        .filterIsInstance<KtTypeReference>()

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

        internal fun getInstance(ktProperty: KtProperty, parentDeclaration: KoParentProvider?): KoPropertyDeclaration =
            cache.getOrCreateInstance(ktProperty, parentDeclaration) {
                KoPropertyDeclarationImpl(ktProperty, parentDeclaration)
            }
    }
}
