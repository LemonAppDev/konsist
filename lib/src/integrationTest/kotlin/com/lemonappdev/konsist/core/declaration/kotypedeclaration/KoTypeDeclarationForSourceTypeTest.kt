package com.lemonappdev.konsist.core.declaration.kotypedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForSourceTypeTest {
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
        sut?.sourceType shouldBeEqualTo "SampleType"
    }

    @Test
    fun `simple-list-type`() {
        // given
        val sut = getSnippetFile("simple-list-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.sourceType shouldBeEqualTo "List<SampleType?>"
    }

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
        sut?.sourceType shouldBeEqualTo "SampleType"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forsourcetype/", fileName)
}
