package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import org.jetbrains.kotlin.psi.KtElement

internal interface KoSourceAndAliasTypeProviderCore :
    KoSourceAndAliasTypeProvider,
    KoTextProviderCore,
    KoBaseProviderCore {
    val ktElement: KtElement

    private val file: KoFileDeclaration
        get() = KoFileDeclarationCore(ktElement.containingKtFile)

    override val isAlias: Boolean
        get() = (this as? KoTypeDeclaration)?.isImportAlias == true

    override val sourceType: String
        get() =
            if (isAlias) {
                file
                    .imports
                    .first { it.alias?.name == ktElement.text.removeSuffix("?") }
                    .name
                    .split(".")
                    .toMutableList()
                    .last { it.isNotBlank() }
            } else {
                text
            }

    override val bareSourceType: String
        get() =
            if ((this as? KoTypeDeclaration)?.isTypeAlias == true) {
                ((this as? KoTypeDeclaration)?.asTypeAliasDeclaration())?.type?.text ?: text
            } else {
                sourceType
                    .removeGenericTypeArguments()
                    .removeNullability()
                    .removePackage()
                    .removeBrackets()
            }

    /*
     * Removes generic type arguments from the type.
     * For `MyClass<String>` value will be "MyClass"
     */
    private fun String.removeGenericTypeArguments(): String = substringBefore("<")

    /*
     * Removes nullability from the type.
     * For `MyClass?` value will be "MyClass"
     */
    private fun String.removeNullability(): String = replace("?", "")

    /*
     * Removes package from the type.
     * For `com.app.MyClass` value will be "MyClass"
     */
    private fun String.removePackage(): String = substringAfterLast(".")

    /*
     * Removes brackets from the type.
     * For `((Int) -> Unit)` value will be "(Int) -> Unit)"
     */
    private fun String.removeBrackets(): String =
        if (startsWith("(") and endsWith(")")) {
            removePrefix("(")
                .removeSuffix(")")
        } else {
            this
        }
}
