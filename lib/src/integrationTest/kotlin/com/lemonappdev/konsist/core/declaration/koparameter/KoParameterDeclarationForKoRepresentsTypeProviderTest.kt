package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parameters
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoRepresentsTypeProviderTest {
    @Test
    fun `parameter-represents-type`() {
        // given
        val sut =
            getSnippetFile("parameter-represents-type")
                .functions()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            representsType("() -> Unit") shouldBeEqualTo true
            representsType("OtherType") shouldBeEqualTo false
            representsType(null) shouldBeEqualTo false
        }
    }

    @Suppress("SameParameterValue")
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkorepresentstypeprovider/", fileName)
}
