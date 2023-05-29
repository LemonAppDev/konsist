package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import java.io.File

object TestSnippetProvider {
    private val modulePath = File("").absoluteFile.path

    private val testSourceSetPath = "$modulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/".toNormalizedPath()

    fun getSnippetKoScope(snippetRelativePath: String, fileName: String): KoScope {
        val snippetPath = "$testSourceSetPath${snippetRelativePath.toNormalizedPath()}$fileName.kttxt"
        return Konsist.scopeFromFile(snippetPath, absolutePath = true)
    }
}
