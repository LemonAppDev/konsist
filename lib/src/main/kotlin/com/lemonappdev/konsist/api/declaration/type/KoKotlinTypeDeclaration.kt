package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider

interface KoKotlinTypeDeclaration :
    KoBaseTypeDeclaration,
    KoFullyQualifiedNameProvider,
    KoSourceAndAliasTypeProvider // Todo: add tests
