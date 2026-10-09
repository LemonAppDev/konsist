package com.lemonappdev.konsist.api.ext.provider.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasParentOf
import com.lemonappdev.konsist.externalfixture.FixtureExternalClass
import com.lemonappdev.konsist.externalfixture.FixtureExternalGenericInterface
import com.lemonappdev.konsist.externalfixture.FixtureExternalInterface
import com.lemonappdev.konsist.testdata.FixtureClass
import com.lemonappdev.konsist.testdata.FixtureInterface
import com.lemonappdev.konsist.testdata.FixtureParentClass
import com.lemonappdev.konsist.testdata.FixtureParentInterface
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentProviderExtTest {
    @Test
    fun `class-has-each-type-of-parents`() {
        // given
        val sut =
            getSnippetFile("class-has-each-type-of-parents")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasParentOf<FixtureParentClass>() shouldBeEqualTo true
            hasParentOf<FixtureExternalInterface>() shouldBeEqualTo true
            hasParentOf<FixtureClass>() shouldBeEqualTo false
            hasParentOf<FixtureInterface>() shouldBeEqualTo false
            hasParentOf<FixtureExternalClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-each-type-of-parents`() {
        // given
        val sut =
            getSnippetFile("object-has-each-type-of-parents")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasParentOf<FixtureParentClass>() shouldBeEqualTo true
            hasParentOf<FixtureExternalInterface>() shouldBeEqualTo true
            hasParentOf<FixtureClass>() shouldBeEqualTo false
            hasParentOf<FixtureInterface>() shouldBeEqualTo false
            hasParentOf<FixtureExternalClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-each-type-of-parents`() {
        // given
        val sut =
            getSnippetFile("interface-has-each-type-of-parents")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasParentOf<FixtureParentInterface>() shouldBeEqualTo true
            hasParentOf<FixtureExternalInterface>() shouldBeEqualTo true
            hasParentOf<FixtureInterface>() shouldBeEqualTo false
            hasParentOf<FixtureExternalGenericInterface<Int>>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("api/ext/provider/koparent/snippet/", fileName)
}
