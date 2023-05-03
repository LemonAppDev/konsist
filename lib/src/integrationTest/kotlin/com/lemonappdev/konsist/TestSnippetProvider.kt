package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Ko
import com.lemonappdev.konsist.api.KoScope
import java.io.File

object TestSnippetProvider {
    private val modulePath = File("").absoluteFile.path

    private val testSourceSetPath = "$modulePath/src/integrationTest/kotlin/com/lemonappdev/konsist/"

    fun getSnippetKoScope(snippetRelativePath: String, fileName: String): KoScope {
        val snippetPath = "$testSourceSetPath$snippetRelativePath$fileName.kttxt"
        return Ko.scopeFromFile(snippetPath)
    }
}
