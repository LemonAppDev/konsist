package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoModuleProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoModuleProviderListExtTest {
    @Test
    fun `withModule(empty list) returns all declarations`() {
        // given
        val declaration1: KoModuleProvider = mockk()
        val declaration2: KoModuleProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withModule(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withModule(empty set) returns all declarations`() {
        // given
        val declaration1: KoModuleProvider = mockk()
        val declaration2: KoModuleProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withModule(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

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
    fun `withModule(list) returns declaration with one of given modules`() {
        // given
        val module1 = "sampleModule1"
        val module2 = "sampleModule2"
        val module3 = "sampleModule3"
        val declaration1: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns true
                every { resideInModule(module2) } returns false
                every { resideInModule(module3) } returns false
            }
        val declaration2: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns true
                every { resideInModule(module3) } returns false
            }
        val declaration3: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns false
                every { resideInModule(module3) } returns true
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val modules = listOf(module1, module2)

        // when
        val sut = declarations.withModule(modules)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withModule(set) returns declaration with one of given modules`() {
        // given
        val module1 = "sampleModule1"
        val module2 = "sampleModule2"
        val module3 = "sampleModule3"
        val declaration1: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns true
                every { resideInModule(module2) } returns false
                every { resideInModule(module3) } returns false
            }
        val declaration2: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns true
                every { resideInModule(module3) } returns false
            }
        val declaration3: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns false
                every { resideInModule(module3) } returns true
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val modules = setOf(module1, module2)

        // when
        val sut = declarations.withModule(modules)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutModule(empty list) returns none declaration`() {
        // given
        val declaration1: KoModuleProvider = mockk()
        val declaration2: KoModuleProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModule(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutModule(empty set) returns none declaration`() {
        // given
        val declaration1: KoModuleProvider = mockk()
        val declaration2: KoModuleProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModule(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
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

    @Test
    fun `withoutModule(list) returns declaration without any of given modules`() {
        // given
        val module1 = "sampleModule1"
        val module2 = "sampleModule2"
        val module3 = "sampleModule3"
        val declaration1: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns true
                every { resideInModule(module2) } returns false
                every { resideInModule(module3) } returns false
            }
        val declaration2: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns true
                every { resideInModule(module3) } returns false
            }
        val declaration3: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns false
                every { resideInModule(module3) } returns true
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val modules = listOf(module1, module2)

        // when
        val sut = declarations.withoutModule(modules)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutModule(set) returns declaration without any of given modules`() {
        // given
        val module1 = "sampleModule1"
        val module2 = "sampleModule2"
        val module3 = "sampleModule3"
        val declaration1: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns true
                every { resideInModule(module2) } returns false
                every { resideInModule(module3) } returns false
            }
        val declaration2: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns true
                every { resideInModule(module3) } returns false
            }
        val declaration3: KoModuleProvider =
            mockk {
                every { resideInModule(module1) } returns false
                every { resideInModule(module2) } returns false
                every { resideInModule(module3) } returns true
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val modules = setOf(module1, module2)

        // when
        val sut = declarations.withoutModule(modules)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
