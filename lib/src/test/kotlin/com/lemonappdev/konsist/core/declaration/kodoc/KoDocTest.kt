package com.lemonappdev.konsist.core.declaration.kodoc

import com.lemonappdev.konsist.TestSnippetProvider

class KoDocTest {


    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodoc/snippet/", fileName)
}
