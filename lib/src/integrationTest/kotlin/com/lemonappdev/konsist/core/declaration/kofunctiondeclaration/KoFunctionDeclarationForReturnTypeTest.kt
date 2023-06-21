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
            hasReturnType() shouldBeEqualTo true
            returnType?.sourceType shouldBeEqualTo "SampleType"
            returnType?.importAliasName shouldBeEqualTo ""
            returnType?.name shouldBeEqualTo "SampleType"
            returnType?.isImportAlias() shouldBeEqualTo false
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
            hasReturnType() shouldBeEqualTo true
            returnType?.sourceType shouldBeEqualTo "SampleType"
            returnType?.importAliasName shouldBeEqualTo "ImportAlias"
            returnType?.name shouldBeEqualTo "ImportAlias"
            returnType?.isImportAlias() shouldBeEqualTo true
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
            hasReturnType() shouldBeEqualTo true
            returnType?.sourceType shouldBeEqualTo "SampleType"
            returnType?.importAliasName shouldBeEqualTo ""
            returnType?.name shouldBeEqualTo "SampleType"
            returnType?.isImportAlias() shouldBeEqualTo false
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
            hasReturnType() shouldBeEqualTo false
            returnType?.sourceType shouldBeEqualTo null
            returnType?.importAliasName shouldBeEqualTo null
            returnType?.name shouldBeEqualTo null
            returnType?.isImportAlias() shouldBeEqualTo null
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forreturntype/", fileName)
}
