package com.lemon.konsist.core.declaration.kotype

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeTest {

    @Test
    fun `import-alias`() {
        // given
        val sut = getSnippetFile("import-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.run {
            sourceType shouldBeEqualTo "SampleType"
            name shouldBeEqualTo "ImportAlias"
            isImportAlias() shouldBeEqualTo true
            fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `simple-type`() {
        // given
        val sut = getSnippetFile("simple-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.run {
            sourceType shouldBeEqualTo "SampleType"
            name shouldBeEqualTo ""
            isImportAlias() shouldBeEqualTo false
            fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/kotype/snippet/", fileName)
}
