package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

interface KoKotlinTypeDeclaration :
    KoBaseTypeDeclaration,
    KoFullyQualifiedNameProvider
