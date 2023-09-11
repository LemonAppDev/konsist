package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider
import com.lemonappdev.konsist.core.declaration.KoEnumConstantDeclarationCore
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

internal interface KoEnumConstantProviderCore :
    KoEnumConstantProvider,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore {
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

    override fun countEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Int =
        enumConstants.count { predicate(it) }

    @Deprecated(
        "Will be removed in v1.0.0",
        replaceWith = ReplaceWith("hasEnumConstant { it.name == ... } && hasEnumConstant { it.name == ... } ...")
    )
    override fun hasEnumConstants(vararg names: String): Boolean = when {
        names.isEmpty() -> enumConstants.isNotEmpty()
        else -> names.all {
            enumConstants.any { constant -> constant.name == it }
        }
    }

    override fun hasEnumConstants(): Boolean = enumConstants.isNotEmpty()

    override fun hasEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean =
        enumConstants.any(predicate)

    override fun hasAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean =
        enumConstants.all(predicate)
}
