package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import org.jetbrains.kotlin.psi.KtUserType

internal interface KoSourceAndAliasTypeProviderCore :
    KoSourceAndAliasTypeProvider,
    KoNameProviderCore,
    KoBaseProviderCore {
    val ktUserType: KtUserType

    private val file: KoFileDeclaration
        get() = KoFileDeclarationCore(ktUserType.containingKtFile)

    override val isAlias: Boolean
        get() = (this as? KoTypeDeclaration)?.declaration is KoImportAliasDeclaration

    override val sourceType: String
        get() = if (isAlias) {
            file
                .imports
                .first { it.alias?.name == ktUserType.text.removeSuffix("?") }
                .name
                .split(".")
                .toMutableList()
                .last { it.isNotBlank() }
        } else {
            name
        }

    override val bareSourceType: String
        get() = sourceType
            .removeGenericTypeArguments()
            .removeNullability()
            .removePackage()

    /*
     * Removes generic type arguments from the type.
     * For `MyClass<String>` value will be "MyClass"
     */
    private fun String.removeGenericTypeArguments(): String {
        return substringBefore("<")
    }

    /*
     * Removes nullability from the type.
     * For `MyClass?` value will be "MyClass"
     */
    private fun String.removeNullability(): String {
        return replace("?", "")
    }

    /*
     * Removes package from the type.
     * For `com.app.MyClass` value will be "MyClass"
     */
    private fun String.removePackage(): String {
        return substringAfterLast(".")
    }
}
