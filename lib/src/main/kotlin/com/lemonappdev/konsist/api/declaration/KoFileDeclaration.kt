package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceAndObjectProvider
import com.lemonappdev.konsist.api.provider.KoClassAndInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoClassAndObjectProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoCompanionObjectProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFileExtensionProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.api.provider.KoImportAliasProvider
import com.lemonappdev.konsist.api.provider.KoImportProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceAndObjectProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider

/**
 * Represents a file declaration.
 */
interface KoFileDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoClassProvider,
    KoClassAndInterfaceAndObjectProvider,
    KoClassAndInterfaceProvider,
    KoClassAndObjectProvider,
    KoInterfaceAndObjectProvider,
    KoDeclarationProvider,
    KoFileExtensionProvider,
    KoFunctionProvider,
    KoHasPackageProvider,
    KoImportProvider,
    KoImportAliasProvider,
    KoInterfaceProvider,
    KoModuleProvider,
    KoNameProvider,
    KoObjectProvider,
    KoPackageProvider,
    KoPathProvider,
    KoPropertyProvider,
    KoSourceSetProvider,
    KoTextProvider,
    KoTypeAliasProvider,
    KoCompanionObjectProvider {
    /**
     * Name of the file without extension e.g. `SampleClass` for `SampleClass.kt` file.
     * Use [nameWithExtension] to get the name with extension.
     */
    override val name: String

    /**
     * Checks whether the file name is equal to the specified text.
     * The text can contain the file extension e.g. both `SampleClass` and `SampleClass.kt` match the `SampleClass.kt` file.
     *
     * @param text The text to compare with. Can be the file name with or without extension.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if the file name (with or without extension) equals the specified text, `false` otherwise.
     */
    override fun hasName(
        text: String,
        ignoreCase: Boolean,
    ): Boolean

    /**
     * Indicates whether some other element is "equal to" this one.
     *
     * @param other the element to compare.
     * @return `true` if the elements are equal, `false` otherwise.
     */
    override fun equals(other: Any?): Boolean

    /**
     * Returns a hash code value.
     *
     * @return the hash code value.
     */
    override fun hashCode(): Int
}
