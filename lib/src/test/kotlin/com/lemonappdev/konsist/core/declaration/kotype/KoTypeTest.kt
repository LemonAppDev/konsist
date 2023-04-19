package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
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
            importAliasName shouldBeEqualTo "ImportAlias"
            name shouldBeEqualTo "ImportAlias"
            isImportAlias() shouldBeEqualTo true
            fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
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
            importAliasName shouldBeEqualTo ""
            name shouldBeEqualTo "SampleType"
            isImportAlias() shouldBeEqualTo false
            fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotype/snippet/", fileName)
}
