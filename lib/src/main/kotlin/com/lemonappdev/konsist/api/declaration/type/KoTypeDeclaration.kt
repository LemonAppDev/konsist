package com.lemonappdev.konsist.api.declaration.type

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoIsFunctionTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsGenericProvider
import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider
import com.lemonappdev.konsist.api.provider.KoIsNullableProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoSourceTypeProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider

/**
 * Represents a Kotlin type declaration.
 */
@Suppress("detekt.TooManyFunctions")
interface KoTypeDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoTextProvider,
    KoPathProvider,
    KoLocationProvider,
    KoIsNullableProvider,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoIsGenericProvider,
    KoIsFunctionTypeProvider,
    KoSourceTypeProvider,
    KoPackageProvider,
    KoResideInPackageProvider,
    KoAnnotationProvider,
    KoSourceDeclarationProvider,
    KoIsMutableTypeProvider,
    KoTypeArgumentProvider,
    KoFunctionTypeDeclarationProvider {
    /**
     * Package of the file where the type is referenced (the use site), not the package where the type is declared,
     * e.g. for `fun Foo.bar()` declared in `com.app`, `receiverType.packagee` is `com.app` even if `Foo` is declared
     * in `com.lib`. [residesInPackage] and [residesOutsidePackage] check this package too.
     *
     * To get the package where the type is declared use [sourceDeclaration], e.g.
     * `type.sourceDeclaration?.asClassDeclaration()?.packagee`.
     */
    override val packagee: KoPackageDeclaration?
}
