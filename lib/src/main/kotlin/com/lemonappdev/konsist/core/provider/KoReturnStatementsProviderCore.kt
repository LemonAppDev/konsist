package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoReturnStatementsProvider
import org.jetbrains.kotlin.psi.KtFunction

internal interface KoReturnStatementsProviderCore : KoReturnStatementsProvider {
    val ktFunction: KtFunction

    override val numReturnStatements: Int
        get() {
            val regex = Regex("return")

            return regex.findAll(ktFunction.text).count()
        }
}
