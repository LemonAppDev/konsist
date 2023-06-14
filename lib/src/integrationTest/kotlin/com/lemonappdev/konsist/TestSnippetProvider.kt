package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.core.ext.toCanonicalPaths

object TestSnippetProvider {
    private val testSourceSetPath = "lib/src/integrationTest/kotlin/com/lemonappdev/konsist/".toCanonicalPaths()

    fun getSnippetKoScope(snippetRelativePath: String, fileName: String): KoScope {
        val snippetPath = "$testSourceSetPath${snippetRelativePath.toCanonicalPaths()}$fileName.kttxt"
        return Konsist.scopeFromFile(snippetPath)
    }
}
