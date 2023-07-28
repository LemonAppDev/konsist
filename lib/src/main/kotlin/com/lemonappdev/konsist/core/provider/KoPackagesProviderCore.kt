package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration
import com.lemonappdev.konsist.api.provider.KoPackagesProvider
import com.lemonappdev.konsist.core.util.LocationUtil

internal interface KoPackagesProviderCore : KoPackagesProvider, KoBaseProviderCore {
    val koFiles: List<KoFile>

    override val packages: List<KoPackageDeclaration>
        get() = koFiles.mapNotNull { it.packagee }

    override fun hasPackages(vararg names: String): Boolean = when {
        names.isEmpty() -> packages.toList().isNotEmpty()
        else -> names.all {
            packages.any { packagee ->
                LocationUtil.resideInLocation(it, packagee.fullyQualifiedName) || LocationUtil.resideInLocation(
                    it,
                    packagee.name,
                )
            }
        }
    }
}
