package com.test.konsisttest

import com.konsistcore.KoFileImporter
import com.konsistcore.core.declaration.KoScope
import java.io.File

object TestSnippetProvider {
    private val projectPath = File("").absoluteFile.path
    private val konsisttestPath = "${com.test.konsisttest.TestSnippetProvider.projectPath}/src/konsistTest/kotlin/com/test/konsisttest/"

    fun getSnippetKoScope(snippetRelativePath: String): KoScope {
        val snippetPath = "${com.test.konsisttest.TestSnippetProvider.konsisttestPath}/$snippetRelativePath"
        return KoFileImporter.importFile(snippetPath)
    }
}
