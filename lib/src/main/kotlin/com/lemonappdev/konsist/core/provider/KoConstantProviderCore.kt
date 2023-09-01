package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoConstantProvider
import com.lemonappdev.konsist.core.declaration.KoConstantDeclarationCore
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal interface KoConstantProviderCore : KoConstantProvider, KoBaseProviderCore, KoContainingDeclarationProviderCore {
    val ktClass: KtClass

    override val constants: List<KoConstantDeclaration>
        get() = ktClass
            .children
            .firstIsInstanceOrNull<KtClassBody>()
            ?.children
            ?.filterIsInstance<KtEnumEntry>()
            ?.map { KoConstantDeclarationCore.getInstance(it, this) } ?: emptyList()

    override val numConstants: Int
        get() = constants.size

    override fun hasConstants(vararg names: String): Boolean = when {
        names.isEmpty() -> constants.isNotEmpty()
        else -> names.all {
            constants.any { constant -> constant.name == it }
        }
    }
}
