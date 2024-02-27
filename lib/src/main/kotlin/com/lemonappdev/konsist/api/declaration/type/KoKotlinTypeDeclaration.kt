package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider
import com.lemonappdev.konsist.api.provider.KoNullableProvider
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider

interface KoKotlinTypeDeclaration :
    KoTypeDeclaration,
    KoGenericTypeProvider,
    KoNullableProvider,
    KoSourceAndAliasTypeProvider
