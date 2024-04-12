package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoNullableTypeProviderTest {
    @Test
    fun `property-without-type`() {
        // given
        val sut =
            getSnippetFile("property-without-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            type shouldBeEqualTo null
            hasType() shouldBeEqualTo false
            hasType { it.name == "String" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-with-type`() {
        // given
        val sut =
            getSnippetFile("property-with-type")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            type?.name shouldBeEqualTo "String"
            hasType() shouldBeEqualTo true
            hasType { it.name == "String" } shouldBeEqualTo true
            hasType { it.name == "Int" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo true
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkonullabletypeprovider/", fileName)
}
