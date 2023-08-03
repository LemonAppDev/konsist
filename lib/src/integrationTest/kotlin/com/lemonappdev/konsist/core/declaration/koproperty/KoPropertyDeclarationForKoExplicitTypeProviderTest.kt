package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoExplicitTypeProviderTest {
    @Test
    fun `property-without-explicit-type`() {
        // given
        val sut = getSnippetFile("property-without-explicit-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            explicitType shouldBeEqualTo null
            hasExplicitType() shouldBeEqualTo false
            hasExplicitType("String") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-explicit-type`() {
        // given
        val sut = getSnippetFile("property-with-explicit-type")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            explicitType?.name shouldBeEqualTo "String"
            hasExplicitType() shouldBeEqualTo true
            hasExplicitType("String") shouldBeEqualTo true
            hasExplicitType("Int") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forkoexplicittypeprovider/", fileName)
}
