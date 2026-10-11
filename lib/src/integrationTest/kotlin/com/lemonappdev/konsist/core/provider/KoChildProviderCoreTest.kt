package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
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
}
