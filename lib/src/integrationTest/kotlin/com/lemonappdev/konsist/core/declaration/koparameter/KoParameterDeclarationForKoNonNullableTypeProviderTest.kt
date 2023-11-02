package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleType
import net.bytebuddy.matcher.ElementMatchers.hasType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoNonNullableTypeProviderTest {
    @Test
    fun `class-has-complex-default-parameter-value`() {
        // given
        val sut = getSnippetFile("class-has-complex-default-parameter-value")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.type?.name shouldBeEqualTo "SampleType"
            it?.hasType { type -> type.name == "SampleType" } shouldBeEqualTo true
            it?.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasTypeOf(SampleType::class) shouldBeEqualTo true
            it?.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-one-parameter-with-import-alias`() {
        // given
        val sut = getSnippetFile("class-has-one-parameter-with-import-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.type?.name shouldBeEqualTo "ImportAlias"
            it?.hasType { type -> type.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasTypeOf(SampleType::class) shouldBeEqualTo false
            it?.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkononnullabletypeprovider/", fileName)
}
