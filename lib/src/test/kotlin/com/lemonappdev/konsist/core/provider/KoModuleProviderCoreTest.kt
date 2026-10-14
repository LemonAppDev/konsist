package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.ext.list.withModule
import com.lemonappdev.konsist.api.ext.list.withoutModule
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

    private val parentModuleDeclaration =
        object : KoModuleProviderCore {
            override val psiElement: PsiElement = mockk()
            override val moduleName: String = "feature"
        }

    @Test
    fun `residesInModule returns true for module name with unix separator`() {
        // when
        val result = sut.residesInModule("feature/payment")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `residesInModule returns true for module name with windows separator`() {
        // when
        val result = sut.residesInModule("""feature\payment""")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `residesInModule returns false for different module name`() {
        // when
        val result = sut.residesInModule("""feature\data""")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `residesInModule returns true for gradle project path`() {
        // when
        val result = sut.residesInModule(":feature:payment")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `residesInModule returns true for gradle project path without leading colon`() {
        // when
        val result = sut.residesInModule("feature:payment")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `residesInModule returns false for different gradle project path`() {
        // when
        val result = sut.residesInModule(":feature:data")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `residesInModule returns true for module name with leading and trailing slashes`() {
        // when
        val result = sut.residesInModule("/feature/payment/")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `residesInModule returns true for module name with trailing slash`() {
        // when
        val result = sut.residesInModule("feature/payment/")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `residesInModule returns true for module name with leading and trailing backslashes`() {
        // when
        val result = sut.residesInModule("""\feature\payment\""")

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

    @Test
    fun `residesInModule returns true for three level gradle project path`() {
        // given
        val sut =
            object : KoModuleProviderCore {
                override val psiElement: PsiElement = mockk()
                override val moduleName: String = "feature/payment/api"
            }

        // when
        val result = sut.residesInModule(":feature:payment:api")

        // then
        result shouldBeEqualTo true
    }

    @Test
    fun `residesInModule returns false for parent of three level module`() {
        // given
        val sut =
            object : KoModuleProviderCore {
                override val psiElement: PsiElement = mockk()
                override val moduleName: String = "feature/payment/api"
            }

        // when
        val result = sut.residesInModule(":feature:payment")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `residesInModule returns false for parent module`() {
        // when
        val result = sut.residesInModule(":feature")

        // then
        result shouldBeEqualTo false
    }

    @Test
    fun `withModule for parent module does not return declarations from nested module`() {
        // given
        val declarations = listOf(parentModuleDeclaration, sut)

        // when
        val result = declarations.withModule(":feature")

        // then
        result shouldBeEqualTo listOf(parentModuleDeclaration)
    }

    @Test
    fun `withModule for nested module does not return declarations from parent module`() {
        // given
        val declarations = listOf(parentModuleDeclaration, sut)

        // when
        val result = declarations.withModule(":feature:payment")

        // then
        result shouldBeEqualTo listOf(sut)
    }

    @Test
    fun `withoutModule for parent module keeps declarations from nested module`() {
        // given
        val declarations = listOf(parentModuleDeclaration, sut)

        // when
        val result = declarations.withoutModule(":feature")

        // then
        result shouldBeEqualTo listOf(sut)
    }

    @Test
    fun `withoutModule for nested module keeps declarations from parent module`() {
        // given
        val declarations = listOf(parentModuleDeclaration, sut)

        // when
        val result = declarations.withoutModule("feature/payment")

        // then
        result shouldBeEqualTo listOf(parentModuleDeclaration)
    }
}
