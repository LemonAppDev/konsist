package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoNameProviderTest {
    @Test
    fun `class-with-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("class-with-parent-class-from-file")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperClass"
    }

    @Test
    fun `class-with-generic-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("class-with-generic-parent-class-from-file")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperClass"
    }

    @Test
    fun `class-with-parametrized-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("class-with-parametrized-parent-class-from-file")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParametrizedSuperClass"
    }

    @Test
    fun `class-with-parametrized-and-generic-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("class-with-parametrized-and-generic-parent-class-from-file")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParametrizedSuperClass"
    }

    @Test
    fun `class-with-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("class-with-parent-interface-from-file")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperInterface"
    }

    @Test
    fun `class-with-generic-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("class-with-generic-parent-interface-from-file")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperInterface"
    }

    @Test
    fun `class-with-parent-by-delegation-from-file`() {
        // given
        val sut = getSnippetFile("class-with-parent-by-delegation-from-file")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperInterface"
    }

    @Test
    fun `class-with-multiline-parent-from-file`() {
        // given
        val sut = getSnippetFile("class-with-multiline-parent-from-file")
            .classes()
            .first()
            .parents
            .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SomeParentClass")
    }

    @Test
    fun `class-with-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("class-with-parent-class-from-import")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `class-with-generic-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("class-with-generic-parent-class-from-import")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleCollection1"
    }

    @Test
    fun `class-with-parametrized-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("class-with-parametrized-parent-class-from-import")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClassWithParameter"
    }

    @Test
    fun `class-with-parametrized-and-generic-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("class-with-parametrized-and-generic-parent-class-from-import")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericClassWithParameter"
    }

    @Test
    fun `class-with-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("class-with-parent-interface-from-import")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleInterface"
    }

    @Test
    fun `class-with-generic-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("class-with-generic-parent-interface-from-import")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperInterface"
    }

    @Test
    fun `class-with-parent-by-delegation-from-import`() {
        // given
        val sut = getSnippetFile("class-with-parent-by-delegation-from-import")
            .classes()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleInterface"
    }

    @Test
    fun `interface-with-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("interface-with-parent-interface-from-file")
            .interfaces()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperInterface"
    }

    @Test
    fun `interface-with-generic-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("interface-with-generic-parent-interface-from-file")
            .interfaces()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperInterface"
    }

    @Test
    fun `interface-with-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("interface-with-parent-interface-from-import")
            .interfaces()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParentInterface"
    }

    @Test
    fun `interface-with-generic-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("interface-with-generic-parent-interface-from-import")
            .interfaces()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperInterface"
    }

    @Test
    fun `object-with-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("object-with-parent-class-from-file")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperClass"
    }

    @Test
    fun `object-with-generic-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("object-with-generic-parent-class-from-file")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperClass"
    }

    @Test
    fun `object-with-parametrized-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("object-with-parametrized-parent-class-from-file")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParametrizedSuperClass"
    }

    @Test
    fun `object-with-parametrized-and-generic-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("object-with-parametrized-and-generic-parent-class-from-file")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParametrizedSuperClass"
    }

    @Test
    fun `object-with-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("object-with-parent-interface-from-file")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperInterface"
    }

    @Test
    fun `object-with-generic-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("object-with-generic-parent-interface-from-file")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperInterface"
    }

    @Test
    fun `object-with-multiline-parent-from-file`() {
        // given
        val sut = getSnippetFile("object-with-multiline-parent-from-file")
            .objects()
            .first()
            .parents
            .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SomeParentClass")
    }

    @Test
    fun `object-with-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("object-with-parent-class-from-import")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `object-with-generic-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("object-with-generic-parent-class-from-import")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleCollection1"
    }

    @Test
    fun `object-with-parametrized-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("object-with-parametrized-parent-class-from-import")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClassWithParameter"
    }

    @Test
    fun `object-with-parametrized-and-generic-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("object-with-parametrized-and-generic-parent-class-from-import")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericClassWithParameter"
    }

    @Test
    fun `object-with-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("object-with-parent-interface-from-import")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleInterface"
    }

    @Test
    fun `object-with-generic-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("object-with-generic-parent-interface-from-import")
            .objects()
            .parents
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperInterface"
    }

    @Test
    fun `object-with-multiline-parent-from-import`() {
        // given
        val sut = getSnippetFile("object-with-multiline-parent-from-import")
            .objects()
            .first()
            .parents
            .first()

        // then
        sut
            .name
            .shouldBeEqualTo("SampleClassWithParameter")
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkonameprovider/", fileName)
}

