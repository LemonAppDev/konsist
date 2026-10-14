package com.lemonappdev.konsist.api.ext.list.kosourcetype

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.withBareSourceTypeOf
import com.lemonappdev.konsist.api.ext.list.withSourceTypeOf
import com.lemonappdev.konsist.api.ext.list.withoutBareSourceTypeOf
import com.lemonappdev.konsist.api.ext.list.withoutSourceTypeOf
import com.lemonappdev.konsist.externalfixture.FixtureExternalClass
import com.lemonappdev.konsist.testdata.FixtureType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

class KoSourceTypeProviderListExtTest {
    @Test
    fun `class-type`() {
        // given
        val sut = getSnippetFile("class-type").propertyTypes()

        // then
        assertMatches(sut, FixtureType::class)
    }

    @Test
    fun `nullable-class-type`() {
        // given
        val sut = getSnippetFile("nullable-class-type").propertyTypes()

        // then
        assertMatches(sut, FixtureType::class)
    }

    @Test
    fun `generic-type`() {
        // given
        val sut = getSnippetFile("generic-type").propertyTypes()

        // then
        assertMatches(sut, List::class)
        assertDoesNotMatch(sut, FixtureType::class)
    }

    @Test
    fun `class-type-with-same-name-in-different-package`() {
        // given
        val sut = getSnippetFile("class-type-with-same-name-in-different-package").propertyTypes()

        // then
        assertDoesNotMatch(sut, FixtureType::class)
    }

    @Test
    fun `import-alias-type`() {
        // given
        val sut = getSnippetFile("import-alias-type").propertyTypes()

        // then
        assertMatches(sut, FixtureType::class)
    }

    @Test
    fun `typealias-type`() {
        // given
        val sut = getSnippetFile("typealias-type").propertyTypes()

        // then
        assertMatches(sut, FixtureType::class)
    }

    @Test
    fun `kotlin-type`() {
        // given
        val sut = getSnippetFile("kotlin-type").propertyTypes()

        // then
        assertMatches(sut, String::class)
        assertDoesNotMatch(sut, FixtureType::class)
    }

    @Test
    fun `external-type`() {
        // given
        val sut = getSnippetFile("external-type").propertyTypes()

        // then
        assertMatches(sut, FixtureExternalClass::class)
        assertDoesNotMatch(sut, FixtureType::class)
    }

    private fun assertMatches(
        sut: List<KoTypeDeclaration>,
        kClass: KClass<*>,
    ) {
        assertSoftly(sut) {
            withSourceTypeOf(kClass) shouldBeEqualTo sut
            withBareSourceTypeOf(kClass) shouldBeEqualTo sut
            withoutSourceTypeOf(kClass) shouldBeEqualTo emptyList()
            withoutBareSourceTypeOf(kClass) shouldBeEqualTo emptyList()
        }
    }

    private fun assertDoesNotMatch(
        sut: List<KoTypeDeclaration>,
        kClass: KClass<*>,
    ) {
        assertSoftly(sut) {
            withSourceTypeOf(kClass) shouldBeEqualTo emptyList()
            withBareSourceTypeOf(kClass) shouldBeEqualTo emptyList()
            withoutSourceTypeOf(kClass) shouldBeEqualTo sut
            withoutBareSourceTypeOf(kClass) shouldBeEqualTo sut
        }
    }

    private fun KoScope.propertyTypes(): List<KoTypeDeclaration> =
        properties()
            .mapNotNull { it.type }
            .also { it.size shouldBeEqualTo 1 }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "api/ext/list/kosourcetype/snippet/",
            fileName,
        )
}
