package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoEnumConstDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoConstantProvider
import com.lemonappdev.konsist.core.declaration.KoEnumConstDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationCore
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias

internal interface KoConstantProviderCore : KoConstantProvider, KoBaseProviderCore, KoContainingDeclarationProviderCore {
    val ktClass: KtClass

    override val constants: List<KoEnumConstDeclaration>
        get() = ktClass
            .children
            .filterIsInstance<KtEnumEntry>()
            .map { KoEnumConstDeclarationCore.getInstance(it, this) }

    override val numConstants: Int
        get() = constants.size

    override fun hasConstants(vararg names: String): Boolean = when {
        names.isEmpty() -> constants.isNotEmpty()
        else -> names.all {
            constants.any { constant -> constant.name == it }
        }
    }
}
