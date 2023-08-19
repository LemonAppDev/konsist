package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoReturnTypeProviderTest {
    @Test
    fun `function-return-type`() {
        // given
        val sut = getSnippetFile("function-return-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReturnType shouldBeEqualTo true
            returnType?.sourceType shouldBeEqualTo "SampleType"
            returnType?.aliasType shouldBeEqualTo null
            returnType?.name shouldBeEqualTo "SampleType"
            returnType?.isAlias shouldBeEqualTo false
            returnType?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `function-return-import-alias`() {
        // given
        val sut = getSnippetFile("function-return-import-alias")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReturnType shouldBeEqualTo true
            returnType?.sourceType shouldBeEqualTo "SampleType"
            returnType?.aliasType shouldBeEqualTo "ImportAlias"
            returnType?.name shouldBeEqualTo "ImportAlias"
            returnType?.isAlias shouldBeEqualTo true
        }
    }

    @Test
    fun `extension-function-return-type`() {
        // given
        val sut = getSnippetFile("extension-function-return-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReturnType shouldBeEqualTo true
            returnType?.sourceType shouldBeEqualTo "SampleType"
            returnType?.aliasType shouldBeEqualTo null
            returnType?.name shouldBeEqualTo "SampleType"
            returnType?.isAlias shouldBeEqualTo false
            returnType?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `function-not-return-type`() {
        // given
        val sut = getSnippetFile("function-not-return-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReturnType shouldBeEqualTo false
            returnType?.sourceType shouldBeEqualTo null
            returnType?.aliasType shouldBeEqualTo null
            returnType?.name shouldBeEqualTo null
            returnType?.isAlias shouldBeEqualTo null
        }
    }

    @Test
    fun `extension-function-not-return-type`() {
        // given
        val sut = getSnippetFile("extension-function-not-return-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasReturnType shouldBeEqualTo false
            returnType?.sourceType shouldBeEqualTo null
            returnType?.aliasType shouldBeEqualTo null
            returnType?.name shouldBeEqualTo null
            returnType?.isAlias shouldBeEqualTo null
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/snippet/forkoreturntypeprovider/", fileName)
}
