package com.lemonappdev.konsist.api.ext.list.korepresentstype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.withRepresentedTypeOf
import com.lemonappdev.konsist.api.ext.list.withoutRepresentedTypeOf
import com.lemonappdev.konsist.api.ext.provider.representsTypeOf
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.testdata.FixtureType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.reflect.KClass

class KoRepresentsTypeProviderListExtTest {
    @Test
    fun `parameter-with-imported-type`() {
        // given
        val sut = getSnippetFile("parameter-with-imported-type").functionParameters()

        // then
        assertMatches(sut, File::class)
        assertDoesNotMatch(sut, FixtureType::class)
        sut.first().representsTypeOf<File>() shouldBeEqualTo true
        sut.first().representsType("File") shouldBeEqualTo true
        sut.first().representsType("java.io.File") shouldBeEqualTo true
    }

    @Test
    fun `parameter-with-nullable-imported-type`() {
        // given
        val sut = getSnippetFile("parameter-with-nullable-imported-type").functionParameters()

        // then
        assertMatches(sut, File::class)
        sut.first().representsTypeOf<File>() shouldBeEqualTo true
    }

    @Test
    fun `parameter-with-same-name-type-in-different-package`() {
        // given
        val sut = getSnippetFile("parameter-with-same-name-type-in-different-package").functionParameters()

        // then
        assertDoesNotMatch(sut, File::class)
        sut.first().representsTypeOf<File>() shouldBeEqualTo false
        sut.first().representsType("File") shouldBeEqualTo true
        sut.first().representsType("com.lemonappdev.konsist.samplepackage.File") shouldBeEqualTo true
    }

    @Test
    fun `parameter-with-fully-qualified-type`() {
        // given
        val sut = getSnippetFile("parameter-with-fully-qualified-type").functionParameters()

        // then
        assertMatches(sut, File::class)
    }

    @Test
    fun `parameter-with-kotlin-type`() {
        // given
        val sut = getSnippetFile("parameter-with-kotlin-type").functionParameters()

        // then
        assertMatches(sut, String::class)
        assertDoesNotMatch(sut, File::class)
    }

    @Test
    fun `parameter-with-kotlin-type-alias-of-java-type`() {
        // given
        val sut = getSnippetFile("parameter-with-kotlin-type-alias-of-java-type").functionParameters()

        // then
        assertMatches(sut, RuntimeException::class)
    }

    @Test
    fun `parameter-with-project-type`() {
        // given
        val sut = getSnippetFile("parameter-with-project-type").functionParameters()

        // then
        assertMatches(sut, FixtureType::class)
        assertDoesNotMatch(sut, File::class)
    }

    @Test
    fun `context-parameter-with-imported-type`() {
        // given
        val sut = getSnippetFile("context-parameter-with-imported-type").functionContextParameters()

        // then
        assertMatches(sut, File::class)
        assertDoesNotMatch(sut, FixtureType::class)
    }

    @Test
    fun `context-parameter-with-nullable-imported-type`() {
        // given
        val sut = getSnippetFile("context-parameter-with-nullable-imported-type").functionContextParameters()

        // then
        assertMatches(sut, File::class)
    }

    @Test
    fun `context-parameter-with-same-name-type-in-different-package`() {
        // given
        val sut = getSnippetFile("context-parameter-with-same-name-type-in-different-package").functionContextParameters()

        // then
        assertDoesNotMatch(sut, File::class)
    }

    private fun assertMatches(
        sut: List<KoRepresentsTypeProvider>,
        kClass: KClass<*>,
    ) {
        assertSoftly(sut) {
            withRepresentedTypeOf(kClass) shouldBeEqualTo sut
            withoutRepresentedTypeOf(kClass) shouldBeEqualTo emptyList()
        }
    }

    private fun assertDoesNotMatch(
        sut: List<KoRepresentsTypeProvider>,
        kClass: KClass<*>,
    ) {
        assertSoftly(sut) {
            withRepresentedTypeOf(kClass) shouldBeEqualTo emptyList()
            withoutRepresentedTypeOf(kClass) shouldBeEqualTo sut
        }
    }

    private fun KoScope.functionParameters(): List<KoRepresentsTypeProvider> =
        functions()
            .parameters
            .also { it.size shouldBeEqualTo 1 }

    private fun KoScope.functionContextParameters(): List<KoRepresentsTypeProvider> =
        functions()
            .flatMap { it.contextParameters }
            .also { it.size shouldBeEqualTo 1 }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "api/ext/list/korepresentstype/snippet/",
            fileName,
        )
}
