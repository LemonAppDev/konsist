package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.reflect.typeOf

class KoExplicitBackingFieldDeclarationForKoNullableTypeProviderTest {
    @Test
    fun `explicit-backing-field-has-no-type`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-has-no-type")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly(sut) {
            it?.type shouldBeEqualTo null
            it?.hasType() shouldBeEqualTo false
            it?.hasType { type -> type.name == "MutableList<Int>" } shouldBeEqualTo false
            it?.hasTypeOf(MutableList::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `explicit-backing-field-has-type`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-has-type")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly(sut) {
            it?.type?.name shouldBeEqualTo "MutableList<Int>"
            it?.hasType() shouldBeEqualTo true
            it?.hasType { type -> type.name == "MutableList<Int>" } shouldBeEqualTo true
            it?.hasType { type -> type.name == "List<Int>" } shouldBeEqualTo false
            it?.hasTypeOf(MutableList::class) shouldBeEqualTo true
            it?.hasTypeOf(Set::class) shouldBeEqualTo false
            it?.hasTypeOf(typeOf<MutableList<Int>>()) shouldBeEqualTo true
            it?.hasTypeOf(typeOf<MutableList<String>>()) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forkonullabletypeprovider/", fileName)
}
