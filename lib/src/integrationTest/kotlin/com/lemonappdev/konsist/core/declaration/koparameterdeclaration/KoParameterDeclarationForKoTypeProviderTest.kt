package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoTypeProviderTest {
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
        assertSoftly(sut?.type) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo null
            it?.name shouldBeEqualTo "SampleType"
            it?.isAlias() shouldBeEqualTo false
            it?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
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
        assertSoftly(sut?.type) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo "ImportAlias"
            it?.name shouldBeEqualTo "ImportAlias"
            it?.isAlias() shouldBeEqualTo true
            it?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleType"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forkotypeprovider/", fileName)
}
