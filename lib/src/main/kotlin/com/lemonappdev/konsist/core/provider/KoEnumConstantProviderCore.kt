package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider
import com.lemonappdev.konsist.core.declaration.KoEnumConstantDeclarationCore
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal interface KoEnumConstantProviderCore : KoEnumConstantProvider, KoBaseProviderCore, KoContainingDeclarationProviderCore {
    val ktClass: KtClass

    override val enumConstants: List<KoEnumConstantDeclaration>
        get() = ktClass
            .children
            .firstIsInstanceOrNull<KtClassBody>()
            ?.children
            ?.filterIsInstance<KtEnumEntry>()
            ?.map { KoEnumConstantDeclarationCore.getInstance(it, this) } ?: emptyList()

    override val numEnumConstants: Int
        get() = enumConstants.size

    override fun hasEnumConstants(vararg names: String): Boolean = when {
        names.isEmpty() -> enumConstants.isNotEmpty()
        else -> names.all {
            enumConstants.any { constant -> constant.name == it }
        }
    }
}
