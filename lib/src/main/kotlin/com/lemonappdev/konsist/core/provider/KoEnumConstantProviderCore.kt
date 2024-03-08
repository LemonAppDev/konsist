package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.provider.KoEnumConstantProvider
import com.lemonappdev.konsist.core.declaration.KoEnumConstantDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
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
        get() =
            ktClass
                .children
                .firstIsInstanceOrNull<KtClassBody>()
                ?.children
                ?.filterIsInstance<KtEnumEntry>()
                ?.map { KoEnumConstantDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }
                .orEmpty()

    override val numEnumConstants: Int
        get() = enumConstants.size

    override fun countEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Int = enumConstants.count { predicate(it) }

    @Deprecated(
        """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `hasEnumConstantWithName`, otherwise with `hasEnumConstantsWithAllNames`.
            """,
    )
    override fun hasEnumConstants(vararg names: String): Boolean =
        when {
            names.isEmpty() -> enumConstants.isNotEmpty()
            else ->
                names.all {
                    enumConstants.any { constant -> constant.name == it }
                }
        }

    override fun hasEnumConstants(): Boolean = enumConstants.isNotEmpty()

    override fun hasEnumConstantWithName(
        name: String,
        vararg names: String,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            enumConstants.any { enumConstant -> it == enumConstant.name }
        }
    }

    override fun hasEnumConstantsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            enumConstants.any { enumConstant -> it == enumConstant.name }
        }
    }

    override fun hasEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean = enumConstants.any(predicate)

    override fun hasAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean = enumConstants.all(predicate)
}
