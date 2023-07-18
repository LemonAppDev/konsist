package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForReturnTypeTest {
    @Test
    fun `function-return-type`() {
        // given
        val sut = getSnippetFile("function-return-type")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasExplicitReturnType() shouldBeEqualTo true
            explicitReturnType?.sourceType shouldBeEqualTo "SampleType"
            explicitReturnType?.importAliasName shouldBeEqualTo ""
            explicitReturnType?.name shouldBeEqualTo "SampleType"
            explicitReturnType?.isImportAlias() shouldBeEqualTo false
            explicitReturnType?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
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
            hasExplicitReturnType() shouldBeEqualTo true
            explicitReturnType?.sourceType shouldBeEqualTo "SampleType"
            explicitReturnType?.importAliasName shouldBeEqualTo "ImportAlias"
            explicitReturnType?.name shouldBeEqualTo "ImportAlias"
            explicitReturnType?.isImportAlias() shouldBeEqualTo true
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
            hasExplicitReturnType() shouldBeEqualTo true
            explicitReturnType?.sourceType shouldBeEqualTo "SampleType"
            explicitReturnType?.importAliasName shouldBeEqualTo ""
            explicitReturnType?.name shouldBeEqualTo "SampleType"
            explicitReturnType?.isImportAlias() shouldBeEqualTo false
            explicitReturnType?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
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
            hasExplicitReturnType() shouldBeEqualTo false
            explicitReturnType?.sourceType shouldBeEqualTo null
            explicitReturnType?.importAliasName shouldBeEqualTo null
            explicitReturnType?.name shouldBeEqualTo null
            explicitReturnType?.isImportAlias() shouldBeEqualTo null
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
            hasExplicitReturnType() shouldBeEqualTo false
            explicitReturnType?.sourceType shouldBeEqualTo null
            explicitReturnType?.importAliasName shouldBeEqualTo null
            explicitReturnType?.name shouldBeEqualTo null
            explicitReturnType?.isImportAlias() shouldBeEqualTo null
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forreturntype/", fileName)
}
