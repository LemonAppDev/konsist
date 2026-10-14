package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotContain
import org.junit.jupiter.api.Test

/*
Children are searched in the whole project, so this test uses declarations of the Konsist library.
 */
class KoChildProviderCoreTest {
    private val sut =
        Konsist
            .scopeFromPackage("com.lemonappdev.konsist.api.provider", sourceSetName = "main")
            .interfaces()
            .first { it.name == "KoParentProvider" }

    @Test
    fun `should return direct children declared in project`() {
        // when
        val result = sut.children().map { it.name }

        // then
        result shouldContain "KoParentProviderCore"
        result shouldNotContain "KoClassDeclarationCore"
    }

    @Test
    fun `should return direct and indirect children declared in project`() {
        // when
        val result = sut.children(indirectChildren = true).map { it.name }

        // then
        result shouldContain "KoParentProviderCore"
        result shouldContain "KoParentClassProviderCore"
        result shouldContain "KoClassDeclarationCore"
    }

    @Test
    fun `hasChildOf(vararg) should not match indirect child by default`() {
        // when
        val result = sut.hasChildOf(KoClassDeclaration::class)

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `hasChildOf(vararg) should match indirect child when indirectChildren is true`() {
        // when
        val result = sut.hasChildOf(KoClassDeclaration::class, indirectChildren = true)

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `hasAllChildrenOf(vararg) should not match indirect child by default`() {
        // when
        val result =
            sut.hasAllChildrenOf(
                KoClassAndInterfaceAndObjectDeclaration::class,
                KoClassDeclaration::class,
            )

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `hasAllChildrenOf(vararg) should match direct and indirect children when indirectChildren is true`() {
        // when
        val result =
            sut.hasAllChildrenOf(
                KoClassAndInterfaceAndObjectDeclaration::class,
                KoClassDeclaration::class,
                indirectChildren = true,
            )

        // then
        result shouldBeEqualTo true
    }
}
