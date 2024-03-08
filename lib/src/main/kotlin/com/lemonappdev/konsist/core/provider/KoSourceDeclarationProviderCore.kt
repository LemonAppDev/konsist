package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoAliasProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoSourceDeclarationProviderCore :
    KoSourceDeclarationProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore {
    val ktTypeReference: KtTypeReference
    override val sourceDeclaration: KoBaseTypeDeclaration
        get() = TypeUtil.getBasicType(
            listOf(ktTypeReference),
            ktTypeReference.isExtensionDeclaration(),
            this.castToKoBaseDeclaration(),
            containingFile,
        ) ?: throw IllegalArgumentException("Declaration cannot be a null")
}
