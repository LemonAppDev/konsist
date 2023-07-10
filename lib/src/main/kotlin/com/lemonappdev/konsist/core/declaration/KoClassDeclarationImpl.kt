package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationCoreProviderUtil
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoConstructorsProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoHasTestProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoParentClassProviderCore
import com.lemonappdev.konsist.core.provider.KoParentInterfaceProviderCore
import com.lemonappdev.konsist.core.provider.KoPrimaryConstructorProviderCore
import com.lemonappdev.konsist.core.provider.KoRepresentsTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoSecondaryConstructorsProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoClassDeclarationImpl private constructor(override val ktClass: KtClass, parentDeclaration: KoParentProvider?) :
    KoClassDeclaration,
    KoBaseDeclarationImpl(ktClass),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoRepresentsTypeProviderCore,
    KoPrimaryConstructorProviderCore,
    KoParentClassProviderCore,
    KoParentInterfaceProviderCore,
    KoSecondaryConstructorsProviderCore,
    KoConstructorsProviderCore,
    KoHasTestProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktClass

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, primaryConstructor?.parameters, kDoc)

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoBaseDeclaration> = KoDeclarationCoreProviderUtil
        .getKoDeclarations(ktClass, includeNested, includeLocal, this)

    internal companion object {
        private val cache: KoDeclarationCache<KoClassDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktClass: KtClass, parentDeclaration: KoParentProvider?): KoClassDeclaration =
            cache.getOrCreateInstance(ktClass, parentDeclaration) {
                KoClassDeclarationImpl(ktClass, parentDeclaration)
            }
    }
}
