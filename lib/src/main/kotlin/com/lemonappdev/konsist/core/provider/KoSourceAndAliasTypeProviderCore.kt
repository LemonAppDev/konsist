package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoSourceAndAliasTypeProviderCore :
    KoSourceAndAliasTypeProvider,
    KoNameProviderCore,
    KoBaseProviderCore {
    val ktTypeReference: KtTypeReference

    private val file: KoFileDeclaration
        get() = KoFileDeclarationCore(ktTypeReference.containingKtFile)

    override val aliasType: String?
        get() = file
            .imports
            .firstOrNull { it.alias == ktTypeReference.text.removeSuffix("?") }
            ?.alias

    override val isAlias: Boolean
        get() = aliasType != null

    override val sourceType: String
        get() = if (isAlias) {
            file
                .imports
                .first { it.alias == ktTypeReference.text.removeSuffix("?") }
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
