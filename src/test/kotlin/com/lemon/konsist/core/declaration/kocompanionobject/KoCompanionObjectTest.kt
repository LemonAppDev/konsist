package com.lemon.konsist.core.declaration.kocompanionobject

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoCompanionObjectTest {
    @Test
    fun `companion-object-has-declared-name`() {
        // given
        val sut = getSut("companion-object-has-declared-name")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        with(sut) {
            name shouldBeEqualTo "SampleCompanionObject"
            hasExplicitName() shouldBeEqualTo true
        }
    }

    @Test
    fun `companion-object-has-no-declared-name`() {
        // given
        val sut = getSut("companion-object-has-no-declared-name")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        with(sut) {
            name shouldBeEqualTo "Companion"
            hasExplicitName() shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("kocompanionobject/snippet/", fileName)
}
