package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoLocalClassProviderTest {
    @Test
    fun `enum-constant-contains-no-local-classes`() {
        // given
        val sut = getSnippetFile("enum-constant-contains-no-local-classes")
            .classes()
            .enumConstants
            .first()

        // then
        assertSoftly(sut) {
            numLocalClasses shouldBeEqualTo 0
            countLocalClasses { it.name == "SampleLocalClass" } shouldBeEqualTo 0
            containsLocalClass { it.name == "SampleLocalClass" } shouldBeEqualTo false
            localClasses shouldBeEqualTo emptyList()
        }
    }

    @Test
    fun `enum-constant-contains-local-class`() {
        // given
        val sut = getSnippetFile("enum-constant-contains-local-class")
            .classes()
            .enumConstants
            .first()

        // then
        assertSoftly(sut) {
            numLocalClasses shouldBeEqualTo 2
            countLocalClasses { it.name == "SampleLocalClass1" } shouldBeEqualTo 1
            containsLocalClass { it.name == "SampleLocalClass1" } shouldBeEqualTo true
            containsLocalClass { it.name == "OtherLocalClass" } shouldBeEqualTo false
            localClasses
                .map { it.name }
                .shouldBeEqualTo(listOf("SampleLocalClass1", "SampleLocalClass2"))
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkolocalclassprovider/", fileName)
}
