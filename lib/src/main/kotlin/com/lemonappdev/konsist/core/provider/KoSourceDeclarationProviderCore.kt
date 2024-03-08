package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
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

    override val sourceClass: KoClassDeclaration?
        get() = sourceDeclaration as? KoClassDeclaration

    override val sourceObject: KoObjectDeclaration?
        get() = sourceDeclaration as? KoObjectDeclaration

    override val sourceInterface: KoInterfaceDeclaration?
        get() = sourceDeclaration as? KoInterfaceDeclaration

    override val sourceTypeAlias: KoTypeAliasDeclaration?
        get() = sourceDeclaration as? KoTypeAliasDeclaration

    override val sourceImportAlias: KoImportAliasDeclaration?
        get() = sourceDeclaration as? KoImportAliasDeclaration

    override val sourceKotlinType: KoKotlinTypeDeclaration?
        get() = sourceDeclaration as? KoKotlinTypeDeclaration

    override val sourceFunctionType: KoFunctionTypeDeclaration?
        get() = sourceDeclaration as? KoFunctionTypeDeclaration

    override val sourceExternalType: KoExternalDeclaration?
        get() = sourceDeclaration as? KoExternalDeclaration
}
