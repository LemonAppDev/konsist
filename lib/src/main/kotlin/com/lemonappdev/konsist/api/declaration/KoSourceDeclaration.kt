package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoStarProjectionProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTypeProvider

/**
 * Represents a source declaration in the Kotlin codebase.
 *
 * A source declaration refers to the underlying object or entity being referenced in the source code.
 * For example, in an import statement like `import com.samplepackage.SampleClass`, the source declaration
 * would be the `SampleClass` instance itself, providing access to its properties, name, type, and other
 * relevant information.
 */
interface KoSourceDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoTextProvider,
    KoTypeDeclarationProvider,
    KoTypeProvider,
    KoStarProjectionProvider
