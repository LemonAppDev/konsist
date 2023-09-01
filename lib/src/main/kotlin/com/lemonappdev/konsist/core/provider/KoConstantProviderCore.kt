package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoEnumConstDeclaration
import com.lemonappdev.konsist.api.provider.KoConstantProvider
import com.lemonappdev.konsist.core.declaration.KoEnumConstDeclarationCore
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal interface KoConstantProviderCore : KoConstantProvider, KoBaseProviderCore, KoContainingDeclarationProviderCore {
    val ktClass: KtClass

    override val constants: List<KoEnumConstDeclaration>
        get() = ktClass
            .children
            .firstIsInstanceOrNull<KtClassBody>()
            ?.children
            ?.filterIsInstance<KtEnumEntry>()
            ?.map { KoEnumConstDeclarationCore.getInstance(it, this) } ?: emptyList()

    override val numConstants: Int
        get() = constants.size

    override fun hasConstants(vararg names: String): Boolean = when {
        names.isEmpty() -> constants.isNotEmpty()
        else -> names.all {
            constants.any { constant -> constant.name == it }
        }
    }
}
