package com.lemonappdev.konsist.core.declaration.kocompanionobject

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoCompanionObjectTest {
    @Test
    fun `companion-object-has-declared-name`() {
        // given
        val sut = getSnippetFile("companion-object-has-declared-name")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        sut.run {
            name shouldBeEqualTo "SampleCompanionObject"
            hasExplicitName() shouldBeEqualTo true
        }
    }

    @Test
    fun `companion-object-has-no-declared-name`() {
        // given
        val sut = getSnippetFile("companion-object-has-no-declared-name")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        sut.run {
            name shouldBeEqualTo "Companion"
            hasExplicitName() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocompanionobject/snippet/", fileName)
}
