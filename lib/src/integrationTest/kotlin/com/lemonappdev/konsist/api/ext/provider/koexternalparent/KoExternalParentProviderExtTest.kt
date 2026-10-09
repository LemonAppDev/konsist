package com.lemonappdev.konsist.api.ext.provider.koexternalparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasExternalParentOf
import com.lemonappdev.konsist.externalfixture.FixtureExternalClass
import com.lemonappdev.konsist.externalfixture.FixtureExternalInterface
import com.lemonappdev.konsist.testdata.FixtureParentClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExternalParentProviderExtTest {
    @Test
    fun `class-has-external-parent`() {
        // given
        val sut =
            getSnippetFile("class-has-external-parent")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasExternalParentOf<FixtureExternalInterface>() shouldBeEqualTo true
            hasExternalParentOf<FixtureExternalClass>() shouldBeEqualTo false
            hasExternalParentOf<FixtureParentClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-has-external-parent")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasExternalParentOf<FixtureExternalInterface>() shouldBeEqualTo true
            hasExternalParentOf<FixtureExternalClass>() shouldBeEqualTo false
            hasExternalParentOf<FixtureParentClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-external-parent`() {
        // given
        val sut =
            getSnippetFile("interface-has-external-parent")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasExternalParentOf<FixtureExternalInterface>() shouldBeEqualTo true
            hasExternalParentOf<FixtureExternalClass>() shouldBeEqualTo false
            hasExternalParentOf<FixtureParentClass>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koexternalparent/snippet/", fileName)
}
