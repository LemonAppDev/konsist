package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoTypeAliasProviderTest {
    @Test
    fun `file-has-no-typealias`() {
        // given
        val sut = getSnippetFile("file-has-no-typealias")
            .files
            .first()

        // then
        assertSoftly(sut) {
            typeAliases shouldBeEqualTo emptyList()
            numTypeAliases shouldBeEqualTo 0
            countTypeAliases { it.hasPrivateModifier } shouldBeEqualTo 0
            hasTypeAliases() shouldBeEqualTo false
            hasTypeAliases("SampleTypeAlias") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-typealias`() {
        // given
        val sut = getSnippetFile("file-contains-typealias")
            .files
            .first()

        // then
        assertSoftly(sut.typeAliases.first()) {
            name shouldBeEqualTo "SampleTypeAlias"
            type.sourceType shouldBeEqualTo "() -> Int"
        }
    }

    @Test
    fun `file-has-typealiases`() {
        // given
        val sut = getSnippetFile("file-has-typealiases")
            .files
            .first()

        // then
        assertSoftly(sut) {
            numTypeAliases shouldBeEqualTo 2
            countTypeAliases { it.hasNameStartingWith("Sample") } shouldBeEqualTo 2
            countTypeAliases { it.name == "SampleTypeAlias1" } shouldBeEqualTo 1
            hasTypeAliases() shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1") shouldBeEqualTo true
            hasTypeAliases("SampleTypeAlias1", "SampleTypeAlias2") shouldBeEqualTo true
            hasTypeAliases("OtherTypeAlias") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofile/snippet/forkotypealiasprovider/",
        fileName,
    )
}
