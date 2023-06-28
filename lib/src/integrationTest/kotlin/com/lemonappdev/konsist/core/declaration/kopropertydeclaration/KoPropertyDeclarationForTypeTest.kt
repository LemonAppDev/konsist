package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForTypeTest {
    @Test
    fun `property-has-simple-type`() {
        // given
        val sut = getSnippetFile("property-has-simple-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut.type) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.importAliasName shouldBeEqualTo ""
            it?.name shouldBeEqualTo "SampleType"
            it?.isImportAlias() shouldBeEqualTo false
            it?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `property-has-import-alias`() {
        // given
        val sut = getSnippetFile("property-has-import-alias")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut.type) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.importAliasName shouldBeEqualTo "ImportAlias"
            it?.name shouldBeEqualTo "ImportAlias"
            it?.isImportAlias() shouldBeEqualTo true
            it?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `property-with-extension-has-type`() {
        // given
        val sut = getSnippetFile("property-with-extension-has-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut.type) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.importAliasName shouldBeEqualTo ""
            it?.name shouldBeEqualTo "SampleType"
            it?.isImportAlias() shouldBeEqualTo false
            it?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `property-has-no-type`() {
        // given
        val sut = getSnippetFile("property-has-no-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut.type) {
            it?.sourceType shouldBeEqualTo null
            it?.name shouldBeEqualTo null
            it?.isImportAlias() shouldBeEqualTo null
            it?.fullyQualifiedName shouldBeEqualTo null
        }
    }

    @Test
    fun `property-with-extension-has-no-type`() {
        // given
        val sut = getSnippetFile("property-with-extension-has-no-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut.type) {
            it?.sourceType shouldBeEqualTo null
            it?.name shouldBeEqualTo null
            it?.isImportAlias() shouldBeEqualTo null
            it?.fullyQualifiedName shouldBeEqualTo null
        }
    }

    @Test
    fun `property-has-type`() {
        // given
        val sut = getSnippetFile("property-has-type")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasType() shouldBeEqualTo true
            hasType("SampleType") shouldBeEqualTo true
            hasType("OtherType") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-no-type`() {
        // given
        val sut = getSnippetFile("property-with-no-type")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasType() shouldBeEqualTo false
            hasType("SampleType") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/fortype/", fileName)
}
