package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import com.lemonappdev.konsist.core.util.TypeUtil

internal interface KoTypeProviderCore :
    KoTypeProvider,
    KoNameProviderCore,
    KoBaseProviderCore,
    KoSourceDeclarationProviderCore {
    override val isKotlinBasicType: Boolean
        get() = isKotlinType && TypeUtil.isKotlinBasicType(name)

    override val isKotlinCollectionType: Boolean
        get() = isKotlinType && TypeUtil.isKotlinCollectionTypes(name)

    override val isClass: Boolean
        get() = sourceDeclaration is KoClassDeclaration

    override val isObject: Boolean
        get() = sourceDeclaration is KoObjectDeclaration

    override val isInterface: Boolean
        get() = sourceDeclaration is KoInterfaceDeclaration

    override val isTypeAlias: Boolean
        get() = sourceDeclaration is KoTypeAliasDeclaration

    override val isImportAlias: Boolean
        get() = sourceDeclaration is KoImportAliasDeclaration

    override val isKotlinType: Boolean
        get() = sourceDeclaration is KoKotlinTypeDeclaration

    override val isFunctionType: Boolean
        get() = sourceDeclaration is KoFunctionTypeDeclaration

    override val isGenericType: Boolean
        get() = sourceDeclaration is KoGenericTypeDeclaration

    override val isTypeParameter: Boolean
        get() = sourceDeclaration is KoTypeParameterDeclaration

    override val isExternalType: Boolean
        get() = sourceDeclaration is KoExternalDeclaration
}
