package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoArgumentProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoInterfaceDelegateProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider

/**
 * Represents a Kotlin parent declaration.
 */
interface KoParentDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoNameProvider,
    KoPackageProvider,
    KoResideInPackageProvider,
    KoSourceDeclarationProvider,
    KoTypeArgumentProvider,
    KoTextProvider,
    KoContainingFileProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoLocationProvider,
    KoAnnotationProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoArgumentProvider,
    KoInterfaceDelegateProvider {
    /**
     * Package of the file where the parent is referenced (the use site), not the package where the parent is declared.
     * [residesInPackage] and [residesOutsidePackage] check this package too.
     *
     * To get the package where the parent is declared use [sourceDeclaration], e.g.
     * `parent.sourceDeclaration?.asInterfaceDeclaration()?.packagee`.
     */
    override val packagee: KoPackageDeclaration?
}
