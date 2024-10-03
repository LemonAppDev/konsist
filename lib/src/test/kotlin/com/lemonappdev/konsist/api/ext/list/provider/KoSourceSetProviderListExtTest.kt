package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSourceSetProviderListExtTest {
    @Test
    fun `withSourceSet(empty list) returns all declarations`() {
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
        val sut = declarations.withSourceSet(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceSet(empty set) returns all declarations`() {
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
        val sut = declarations.withSourceSet(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSourceSet(empty list) returns none declaration`() {
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
        val sut = declarations.withoutSourceSet(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutSourceSet(empty set) returns none declaration`() {
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
        val sut = declarations.withoutSourceSet(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

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
    fun `withSourceSet(list of String) returns declarations with one of given source sets`() {
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
        val sourceSets = listOf(sourceSetName1, sourceSetName2)

        // when
        val sut = declarations.withSourceSet(sourceSets)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withSourceSet(set of String) returns declarations with one of given source sets`() {
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
        val sourceSets = setOf(sourceSetName1, sourceSetName2)

        // when
        val sut = declarations.withSourceSet(sourceSets)

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

    @Test
    fun `withoutSourceSet(list of String) returns declaration without any of given source sets`() {
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
        val sourceSets = listOf(sourceSetName1, sourceSetName2)

        // when
        val sut = declarations.withoutSourceSet(sourceSets)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutSourceSet(set of String) returns declaration without any of given source sets`() {
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
        val sourceSets = setOf(sourceSetName1, sourceSetName2)

        // when
        val sut = declarations.withoutSourceSet(sourceSets)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
