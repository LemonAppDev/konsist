package com.lemonappdev.konsist.core.declaration.kocompanionobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoCompanionObjectDeclarationTest {
    @Test
    fun `companion-object-has-declared-name`() {
        // given
        val sut = getSnippetFile("companion-object-has-declared-name")
            .classes()
            .first()
            .companionObjects()
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleCompanionObject"
            hasName() shouldBeEqualTo true
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
        assertSoftly(sut) {
            name shouldBeEqualTo "Companion"
            hasName() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocompanionobjectdeclaration/snippet/", fileName)
}
