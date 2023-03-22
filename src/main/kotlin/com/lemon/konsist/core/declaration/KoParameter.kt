package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtTypeReference

class KoParameter(private val ktParameter: KtParameter) : KoDeclaration(ktParameter) {
    val type by lazy {
        ktParameter
            .children
            .filterIsInstance<KtTypeReference>()
            .first()
            .nameForReceiverLabel()
    }

    val hasDefaultValue by lazy { ktParameter.hasDefaultValue() }
}
