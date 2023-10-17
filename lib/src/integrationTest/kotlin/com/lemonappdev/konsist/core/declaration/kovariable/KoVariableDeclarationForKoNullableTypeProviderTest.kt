package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoNullableTypeProviderTest {
    @Test
    fun `variable-without-type`() {
        // given
        val sut = getSnippetFile("variable-without-type")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            type shouldBeEqualTo null
            hasType() shouldBeEqualTo false
            hasType { it.name == "String" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo false
            hasType("String") shouldBeEqualTo false
        }
    }

    @Test
    fun `variable-with-type`() {
        // given
        val sut = getSnippetFile("variable-with-type")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            type?.name shouldBeEqualTo "String"
            hasType() shouldBeEqualTo true
            hasType { it.name == "String" } shouldBeEqualTo true
            hasType { it.name == "Int" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo true
            hasTypeOf(Int::class) shouldBeEqualTo false
            hasType("String") shouldBeEqualTo true
            hasType("Int") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kovariable/snippet/forkonullabletypeprovider/", fileName)
}
