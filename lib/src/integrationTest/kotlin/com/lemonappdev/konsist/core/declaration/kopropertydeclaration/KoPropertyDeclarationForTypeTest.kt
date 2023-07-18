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
        assertSoftly(sut.explicitType) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo null
            it?.name shouldBeEqualTo "SampleType"
            it?.isAlias() shouldBeEqualTo false
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
        assertSoftly(sut.explicitType) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo "ImportAlias"
            it?.name shouldBeEqualTo "ImportAlias"
            it?.isAlias() shouldBeEqualTo true
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
        assertSoftly(sut.explicitType) {
            it?.sourceType shouldBeEqualTo "Int"
            it?.aliasType shouldBeEqualTo null
            it?.name shouldBeEqualTo "Int"
            it?.isAlias() shouldBeEqualTo false
            it?.fullyQualifiedName shouldBeEqualTo ""
        }
    }

    @Test
    fun `property-has-no-type`() {
        // given
        val sut = getSnippetFile("property-has-no-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut.explicitType) {
            it?.sourceType shouldBeEqualTo null
            it?.name shouldBeEqualTo null
            it?.isAlias() shouldBeEqualTo null
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
            hasExplicitType() shouldBeEqualTo true
            hasExplicitType("SampleType") shouldBeEqualTo true
            hasExplicitType("OtherType") shouldBeEqualTo false
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
            hasExplicitType() shouldBeEqualTo false
            hasExplicitType("SampleType") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/fortype/", fileName)
}
