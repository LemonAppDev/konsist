package com.lemonappdev.konsist

import com.lemonappdev.konsist.core.scope.KoScope
import java.io.File

object TestSnippetProvider {
    val modulePath = File("").absoluteFile.path
    private val testSourceSetPath = "$modulePath/src/test/kotlin/com/lemonappdev/konsist/"

    fun getSnippetKoScope(snippetRelativePath: String, fileName: String): KoScope {
        val snippetPath = "$testSourceSetPath$snippetRelativePath$fileName.kttxt"
        return KoScope.fromFile(snippetPath)
    }
}
