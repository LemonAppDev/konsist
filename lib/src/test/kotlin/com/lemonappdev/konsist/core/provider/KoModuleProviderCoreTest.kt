package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.ext.list.withModule
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.junit.jupiter.api.Test

class KoModuleProviderCoreTest {
    private val sut =
        object : KoModuleProviderCore {
            override val psiElement: PsiElement = mockk()
            override val moduleName: String = "feature/payment"
        }

    @Test
    fun `resideInModule returns true for module name with unix separator`() {
        // when
        val result = sut.resideInModule("feature/payment")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `resideInModule returns true for module name with windows separator`() {
        // when
        val result = sut.resideInModule("""feature\payment""")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `resideInModule returns false for different module name`() {
        // when
        val result = sut.resideInModule("""feature\data""")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `resideInModule returns true for gradle project path`() {
        // when
        val result = sut.resideInModule(":feature:payment")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `resideInModule returns true for gradle project path without leading colon`() {
        // when
        val result = sut.resideInModule("feature:payment")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `resideInModule returns false for different gradle project path`() {
        // when
        val result = sut.resideInModule(":feature:data")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `resideInModule returns true for module name with leading and trailing slashes`() {
        // when
        val result = sut.resideInModule("/feature/payment/")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `resideInModule returns true for module name with trailing slash`() {
        // when
        val result = sut.resideInModule("feature/payment/")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `resideInModule returns true for module name with leading and trailing backslashes`() {
        // when
        val result = sut.resideInModule("""\feature\payment\""")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `withModule returns declaration for module name with leading and trailing slashes`() {
        // given
        val declarations = listOf(sut)

        // when
        val result = declarations.withModule("/feature/payment/")

        // then
        result shouldBeEqualTo listOf(sut)
    }
}
