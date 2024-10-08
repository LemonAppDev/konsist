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

    override fun hasEnumConstants(): Boolean = enumConstants.isNotEmpty()

    override fun hasEnumConstantWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasEnumConstantWithName(listOf(name, *names))

    override fun hasEnumConstantWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasEnumConstants()
            else ->
                names.any {
                    enumConstants.any { enumConstant -> it == enumConstant.name }
                }
        }

    override fun hasEnumConstantsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasEnumConstantsWithAllNames(listOf(name, *names))

    override fun hasEnumConstantsWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasEnumConstants()
            else ->
                names.all {
                    enumConstants.any { enumConstant -> it == enumConstant.name }
                }
        }

    override fun hasEnumConstant(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean = enumConstants.any(predicate)

    override fun hasAllEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Boolean = enumConstants.all(predicate)
}
