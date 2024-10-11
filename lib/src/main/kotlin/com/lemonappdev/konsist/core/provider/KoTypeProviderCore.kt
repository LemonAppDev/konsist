package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoFunctionTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoGenericTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeProvider
import com.lemonappdev.konsist.core.util.TypeUtil

internal interface KoTypeProviderCore :
    KoTypeProvider,
    KoNameProviderCore,
    KoBaseProviderCore {
    val koTypeProviderDeclaration: KoSourceDeclaration?
        get() = null

    override val isKotlinBasicType: Boolean
        get() = isKotlinType && TypeUtil.isKotlinBasicType(name)

    override val isKotlinCollectionType: Boolean
        get() = isKotlinType && TypeUtil.isKotlinCollectionTypes(name)

    override val isClass: Boolean
        get() = koTypeProviderDeclaration is KoClassDeclaration

    override val isObject: Boolean
        get() = koTypeProviderDeclaration is KoObjectDeclaration

    override val isInterface: Boolean
        get() = koTypeProviderDeclaration is KoInterfaceDeclaration

    override val isTypeAlias: Boolean
        get() = koTypeProviderDeclaration is KoTypeAliasDeclaration

    override val isImportAlias: Boolean
        get() = koTypeProviderDeclaration is KoImportAliasDeclaration

    override val isKotlinType: Boolean
        get() = koTypeProviderDeclaration is KoKotlinTypeDeclaration

    override val isFunctionType: Boolean
        get() = koTypeProviderDeclaration is KoFunctionTypeDeclaration

    override val isGenericType: Boolean
        get() = koTypeProviderDeclaration is KoGenericTypeDeclaration

    override val isTypeParameter: Boolean
        get() = koTypeProviderDeclaration is KoTypeParameterDeclaration

    override val isExternalType: Boolean
        get() = koTypeProviderDeclaration is KoExternalDeclaration
}
