package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `class-with-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-class-from-file")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-class-from-file")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parametrized-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-parent-class-from-file")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parametrized-and-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-parent-class-from-file")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-interface-from-file")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-generic-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-interface-from-file")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parent-by-delegation-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-by-delegation-from-file")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-multiline-parent-from-file`() {
        // given
        val sut =
            getSnippetFile("class-with-multiline-parent-from-file")
                .classes()
                .first()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-class-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-class-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parametrized-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-parent-class-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parametrized-and-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-parent-class-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-interface-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-generic-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-parent-interface-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parent-by-delegation-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-parent-by-delegation-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-multiline-parent-from-import`() {
        // given
        val sut =
            getSnippetFile("class-with-multiline-parent-from-import")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-interface")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-interface")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `class-with-external-parent-by-delegation`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-by-delegation")
                .classes()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoClassDeclaration::class
    }

    @Test
    fun `interface-with-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("interface-with-parent-interface-from-file")
                .interfaces()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
    }

    @Test
    fun `interface-with-generic-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-parent-interface-from-file")
                .interfaces()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
    }

    @Test
    fun `interface-with-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("interface-with-parent-interface-from-import")
                .interfaces()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
    }

    @Test
    fun `interface-with-generic-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-parent-interface-from-import")
                .interfaces()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
    }

    @Test
    fun `interface-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-external-parent-interface")
                .interfaces()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
    }

    @Test
    fun `interface-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-external-parent-interface")
                .interfaces()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
    }

    @Test
    fun `object-with-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-parent-class-from-file")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-parent-class-from-file")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parametrized-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-parent-class-from-file")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parametrized-and-generic-parent-class-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-parent-class-from-file")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-parent-interface-from-file")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-generic-parent-interface-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-parent-interface-from-file")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-multiline-parent-from-file`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-parent-from-file")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-parent-class-from-import")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-parent-class-from-import")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parametrized-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-parent-class-from-import")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parametrized-and-generic-parent-class-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-parent-class-from-import")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-parent-interface-from-import")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-generic-parent-interface-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-parent-interface-from-import")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-multiline-parent-from-import`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-parent-from-import")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-class")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-class")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-external-parent-class")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-external-parent-class")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-interface")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-interface")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    @Test
    fun `object-with-multiline-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-external-parent")
                .objects()
                .parents()
                .first()

        // then
        sut.containingDeclaration shouldBeInstanceOf KoObjectDeclaration::class
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koparent/snippet/forkocontainingdeclarationprovider/",
            fileName,
        )
}
