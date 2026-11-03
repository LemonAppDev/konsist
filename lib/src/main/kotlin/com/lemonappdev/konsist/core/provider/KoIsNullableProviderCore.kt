package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoIsNullableProvider
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoIsNullableProviderCore :
    KoIsNullableProvider,
    KoBaseProviderCore {
    val ktTypeReference: KtTypeReference?

    // Only the outermost type element decides nullability, so `() -> Unit?` (nullable return type) is not nullable
    override val isNullable: Boolean
        get() = ktTypeReference?.typeElement is KtNullableType
}
