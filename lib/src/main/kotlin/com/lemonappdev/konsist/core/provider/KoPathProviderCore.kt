package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.LocationUtil
import com.lemonappdev.konsist.core.util.PathUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement

internal interface KoPathProviderCore :
    KoPathProvider,
    KoBaseProviderCore {
    val psiElement: PsiElement

    override val path: String
        get() =
            psiElement
                .containingFile
                ?.name
                ?.toOsSeparator()
                ?: ""

    override val projectPath: String
        get() = PathUtil.getProjectPath(path, PathProvider.rootProjectPath)

    override fun resideInPath(
        path: String,
        absolutePath: Boolean,
    ): Boolean =
        if (absolutePath) {
            LocationUtil.resideInLocation(path, this.path)
        } else {
            LocationUtil.resideInLocation(path, projectPath)
        }
}
