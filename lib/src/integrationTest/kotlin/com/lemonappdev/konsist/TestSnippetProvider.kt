package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.core.ext.toOsSeparator

object TestSnippetProvider {
    private val testSourceSetPath = "lib/src/integrationTest/kotlin/com/lemonappdev/konsist/".toOsSeparator()

    fun getSnippetKoScope(snippetRelativePath: String, fileName: String): KoScope {
        val snippetPath = "$testSourceSetPath${snippetRelativePath.toOsSeparator()}$fileName.kttxt"
        return Konsist.scopeFromFile(snippetPath)
    }
}
