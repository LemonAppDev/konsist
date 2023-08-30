package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoReturnStatementsProvider
import org.jetbrains.kotlin.psi.KtElement

internal interface KoReturnStatementsProviderCore : KoReturnStatementsProvider {
    val ktElement: KtElement

    override val numReturnStatements: Int
        get() {
            val regex = Regex("return")

            return regex.findAll(ktElement.text).count()
        }
}
