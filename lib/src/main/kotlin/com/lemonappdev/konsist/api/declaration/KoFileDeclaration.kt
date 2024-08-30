package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFileExtensionProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoHasPackageProvider
import com.lemonappdev.konsist.api.provider.KoImportAliasProvider
import com.lemonappdev.konsist.api.provider.KoImportProvider
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
    KoTypeAliasProvider {
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
