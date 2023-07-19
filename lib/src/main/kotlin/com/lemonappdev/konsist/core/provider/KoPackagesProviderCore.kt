package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackagesProvider

internal interface KoPackagesProviderCore : KoPackagesProvider {
    val koFiles: Sequence<KoFile>

    override val packages: Sequence<KoPackageDeclaration>
        get() = koFiles.mapNotNull { it.packagee }
}
