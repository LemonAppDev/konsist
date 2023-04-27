package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyTest {
    @Test
    fun `property-is-val`() {
        // given
        val sut = getSnippetFile("property-is-val")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            isVal shouldBeEqualTo true
            isVar shouldBeEqualTo false
        }
    }

    @Test
    fun `property-is-var`() {
        // given
        val sut = getSnippetFile("property-is-var")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            isVal shouldBeEqualTo false
            isVar shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-lateinit-modifier`() {
        // given
        val sut = getSnippetFile("property-has-lateinit-modifier")
            .properties()
            .first()

        // then
        sut.hasLateinitModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-override-modifier`() {
        // given
        val sut = getSnippetFile("property-has-override-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasOverrideModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-abstract-modifier`() {
        // given
        val sut = getSnippetFile("property-has-abstract-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasAbstractModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-open-modifier`() {
        // given
        val sut = getSnippetFile("property-has-open-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasOpenModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-final-modifier`() {
        // given
        val sut = getSnippetFile("property-has-final-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasFinalModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-is-const`() {
        // given
        val sut = getSnippetFile("property-is-const")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasConstModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("property-has-actual-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-expect-modifier`() {
        // given
        val sut = getSnippetFile("property-has-expect-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("property-has-no-modifiers")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasLateinitModifier() shouldBeEqualTo false
            hasOverrideModifier() shouldBeEqualTo false
            hasAbstractModifier() shouldBeEqualTo false
            hasOpenModifier() shouldBeEqualTo false
            hasFinalModifier() shouldBeEqualTo false
            hasConstModifier() shouldBeEqualTo false
            hasActualModifier() shouldBeEqualTo false
            hasExpectModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `property-is-extension`() {
        // given
        val sut = getSnippetFile("property-is-extension")
            .properties()
            .first()

        // then
        sut.isExtension() shouldBeEqualTo true
    }

    @Test
    fun `property-is-not-extension`() {
        // given
        val sut = getSnippetFile("property-is-not-extension")
            .properties()
            .first()

        // then
        sut.isExtension() shouldBeEqualTo false
    }

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
    fun `property-has-lazy-delegate`() {
        // given
        val sut = getSnippetFile("property-has-lazy-delegate")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo "lazy"
            hasDelegate() shouldBeEqualTo true
            hasDelegate("lazy") shouldBeEqualTo true
            hasDelegate("Delegates.observable()") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-no-delegate`() {
        // given
        val sut = getSnippetFile("property-has-no-delegate")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
            hasDelegate("lazy") shouldBeEqualTo false
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/", fileName)
}
