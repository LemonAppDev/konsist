package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForKoSourceAndAliasTypeProviderTest {
    @Test
    fun `type`() {
        // given
        val sut = getSnippetFile("type")
            .properties()
            .first()
            .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.baseSourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo null
            it?.isAlias shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-type`() {
        // given
        val sut = getSnippetFile("nullable-type")
            .properties()
            .first()
            .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.baseSourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo null
            it?.isAlias shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type`() {
        // given
        val sut = getSnippetFile("generic-type")
            .properties()
            .first()
            .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "List<SampleType>"
            it?.baseSourceType shouldBeEqualTo "List"
            it?.aliasType shouldBeEqualTo null
            it?.isAlias shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-with-nullable-type-argument`() {
        // given
        val sut = getSnippetFile("generic-with-nullable-type-argument")
            .properties()
            .first()
            .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "List<SampleType?>"
            it?.baseSourceType shouldBeEqualTo "List"
            it?.aliasType shouldBeEqualTo null
            it?.isAlias shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-generic-with-nullable-type-argument`() {
        // given
        val sut = getSnippetFile("nullable-generic-with-nullable-type-argument")
            .properties()
            .first()
            .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "List<SampleType?>"
            it?.baseSourceType shouldBeEqualTo "List"
            it?.aliasType shouldBeEqualTo null
            it?.isAlias shouldBeEqualTo false
        }
    }

    @Test
    fun `nullable-generic-type`() {
        // given
        val sut = getSnippetFile("nullable-generic-type")
            .properties()
            .first()
            .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "List<SampleType>"
            it?.baseSourceType shouldBeEqualTo "List"
            it?.aliasType shouldBeEqualTo null
            it?.isAlias shouldBeEqualTo false
        }
    }

    @Test
    fun `import-alias`() {
        // given
        val sut = getSnippetFile("import-alias")
            .properties()
            .first()
            .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.baseSourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo "ImportAlias"
            it?.isAlias shouldBeEqualTo true
        }
    }

    @Test
    fun `nullable-import-alias`() {
        // given
        val sut = getSnippetFile("nullable-import-alias")
            .properties()
            .first()
            .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.baseSourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo "ImportAlias"
            it?.isAlias shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotype/snippet/forkosourceandaliastypeprovider/",
            fileName,
        )
}
