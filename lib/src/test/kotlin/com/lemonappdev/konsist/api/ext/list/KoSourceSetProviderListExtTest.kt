package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSourceSetProviderListExtTest {
    @Test
    fun `withSourceSet(String) returns declaration with given source set`() {
        // given
        val sourceSetName = "sourceSetName"
        val declaration1: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName) } returns true
            }
        val declaration2: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSourceSet(sourceSetName)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSourceSet(String) returns declarations with one of given source sets`() {
        // given
        val sourceSetName1 = "sourceSetName1"
        val sourceSetName2 = "sourceSetName2"
        val declaration1: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName1) } returns true
                every { resideInSourceSet(sourceSetName2) } returns false
            }
        val declaration2: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName1) } returns false
                every { resideInSourceSet(sourceSetName2) } returns true
            }
        val declaration3: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName1) } returns false
                every { resideInSourceSet(sourceSetName2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSourceSet(sourceSetName1, sourceSetName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSourceSet(String) returns declaration without given source set`() {
        // given
        val sourceSetName = "sourceSetName"
        val declaration1: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName) } returns true
            }
        val declaration2: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSourceSet(sourceSetName)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSourceSet(String) returns declaration without any of given source sets`() {
        // given
        val sourceSetName1 = "sourceSetName1"
        val sourceSetName2 = "sourceSetName2"
        val declaration1: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName1) } returns true
                every { resideInSourceSet(sourceSetName2) } returns false
            }
        val declaration2: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName1) } returns false
                every { resideInSourceSet(sourceSetName2) } returns true
            }
        val declaration3: KoSourceSetProvider =
            mockk {
                every { resideInSourceSet(sourceSetName1) } returns false
                every { resideInSourceSet(sourceSetName2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSourceSet(sourceSetName1, sourceSetName2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
