package com.lemonappdev.konsist.core.provider

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.LocationUtil

internal interface KoPathProviderCore : KoPathProvider {
    val psiElement: PsiElement

    override val filePath: String
        get() = psiElement
            .containingFile
            .name
            .toOsSeparator()

    override val projectFilePath: String
        get() {
            val rootPathProvider = PathProvider
                .getInstance()
                .rootProjectPath
                .toOsSeparator()

            return filePath.removePrefix(rootPathProvider)
        }

    override fun resideInFilePath(path: String, absolutePath: Boolean): Boolean = if (absolutePath) {
        LocationUtil.resideInLocation(path, filePath)
    } else {
        LocationUtil.resideInLocation(path, projectFilePath)
    }
}
