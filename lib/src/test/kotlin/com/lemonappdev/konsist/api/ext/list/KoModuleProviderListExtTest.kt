package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoModuleProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoModuleProviderListExtTest {
    @Test
    fun `withModule(String) returns declaration with given module`() {
        // given
        val module = "module"
        val declaration1: KoModuleProvider =
            mockk {
                every { resideInModule(module) } returns true
            }
        val declaration2: KoModuleProvider =
            mockk {
                every { resideInModule(module) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withModule(module)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withModule(String) returns declarations with one of given modules`() {
        // given
        val module1 = "module1"
        val module2 = "module2"
        val declaration1: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns true
                every { resideInModule(module2) } returns false
            }
        val declaration2: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns true
            }
        val declaration3: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withModule(module1, module2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutModule(String) returns declaration without given module`() {
        // given
        val module = "module"
        val declaration1: KoModuleProvider =
            mockk {
                every { resideInModule(module) } returns true
            }
        val declaration2: KoModuleProvider =
            mockk {
                every { resideInModule(module) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModule(module)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutModule(String) returns declaration without any of given modules`() {
        // given
        val module1 = "module1"
        val module2 = "module2"
        val declaration1: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns true
                every { resideInModule(module2) } returns false
            }
        val declaration2: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns true
            }
        val declaration3: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutModule(module1, module2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
