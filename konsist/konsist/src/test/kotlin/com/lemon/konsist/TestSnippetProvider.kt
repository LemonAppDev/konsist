package com.lemon.konsist

import com.lemon.konsist.core.declaration.KoScope
import java.io.File

object TestSnippetProvider {
    private val projectPath = File("").absoluteFile.path
    val testSourceSetPath = "$projectPath/src/test/kotlin/com/lemon/konsist/"

    fun getSnippetKoScope(snippetRelativePath: String, fileName: String): KoScope {
        val snippetPath = "$testSourceSetPath$snippetRelativePath$fileName.kttxt"
        return KoScope.fromFile(snippetPath)
    }
}
