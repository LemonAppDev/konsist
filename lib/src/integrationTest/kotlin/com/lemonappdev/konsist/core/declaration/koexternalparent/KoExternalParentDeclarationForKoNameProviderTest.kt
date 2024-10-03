package com.lemonappdev.konsist.core.declaration.koexternalparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.externalParents
import com.lemonappdev.konsist.api.ext.list.provider.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExternalParentDeclarationForKoNameProviderTest {
    @Test
    fun `class-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalClass"
    }

    @Test
    fun `class-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalGenericClass"
    }

    @Test
    fun `class-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalClassWithParameter"
    }

    @Test
    fun `class-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalGenericClassWithParameter"
    }

    @Test
    fun `class-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-interface")
                .classes()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalInterface"
    }

    @Test
    fun `class-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-interface")
                .classes()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalGenericInterface"
    }

    @Test
    fun `class-with-external-parent-by-delegation`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-by-delegation")
                .classes()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalInterface"
    }

    @Test
    fun `class-with-multiline-external-parent`() {
        // given
        val sut =
            getSnippetFile("class-with-multiline-external-parent")
                .classes()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalClassWithParameter"
    }

    @Test
    fun `interface-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-external-parent-interface")
                .interfaces()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalInterface"
    }

    @Test
    fun `interface-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-external-parent-interface")
                .interfaces()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalGenericInterface"
    }

    @Test
    fun `object-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalClass"
    }

    @Test
    fun `object-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalGenericClass"
    }

    @Test
    fun `object-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalClassWithParameter"
    }

    @Test
    fun `object-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalGenericClassWithParameter"
    }

    @Test
    fun `object-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-interface")
                .objects()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalInterface"
    }

    @Test
    fun `object-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-interface")
                .objects()
                .externalParents()
                .first()

        // then
        sut.name shouldBeEqualTo "SampleExternalGenericInterface"
    }

    @Test
    fun `object-with-multiline-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-external-parent")
                .objects()
                .first()
                .externalParents()
                .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SampleExternalClassWithParameter")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koexternalparent/snippet/forkonameprovider/", fileName)
}
