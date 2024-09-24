package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoTestClassProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTestClassProviderListExtTest {
    @Test
    fun `testClasses() returns test classes from all declarations`() {
        // given
        val testClass1: KoClassDeclaration = mockk()
        val testClass2: KoClassDeclaration = mockk()
        val testClass3: KoClassDeclaration = mockk()
        val declaration1: KoTestClassProvider =
            mockk {
                every { testClasses() } returns listOf(testClass1, testClass2)
            }
        val declaration2: KoTestClassProvider =
            mockk {
                every { testClasses() } returns listOf(testClass3)
            }
        val declaration3: KoTestClassProvider =
            mockk {
                every { testClasses() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.testClasses()

        // then
        sut shouldBeEqualTo listOf(testClass1, testClass2, testClass3)
    }

    @Test
    fun `testClasses() returns test classes matching the predicate from all declarations`() {
        // given
        val predicate: (KoClassDeclaration) -> Boolean = { it.numFunctions() == 2 }
        val testClass1: KoClassDeclaration = mockk()
        val testClass2: KoClassDeclaration = mockk()
        val testClass3: KoClassDeclaration = mockk()
        val declaration1: KoTestClassProvider =
            mockk {
                every { testClasses(predicate = predicate) } returns listOf(testClass1, testClass2)
            }
        val declaration2: KoTestClassProvider =
            mockk {
                every { testClasses(predicate = predicate) } returns listOf(testClass3)
            }
        val declaration3: KoTestClassProvider =
            mockk {
                every { testClasses(predicate = predicate) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.testClasses(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(testClass1, testClass2, testClass3)
    }

    @Test
    fun `withTestClass() returns declaration with test`() {
        // given
        val declaration1: KoTestClassProvider =
            mockk {
                every { hasTestClasses() } returns true
            }
        val declaration2: KoTestClassProvider =
            mockk {
                every { hasTestClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTestClass()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTestClass() returns declaration without test`() {
        // given
        val declaration1: KoTestClassProvider =
            mockk {
                every { hasTestClasses() } returns true
            }
        val declaration2: KoTestClassProvider =
            mockk {
                every { hasTestClasses() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTestClass()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTestClass() returns declaration with test matching the predicate`() {
        // given
        val predicate: (KoClassDeclaration) -> Boolean = { it.numFunctions() == 2 }
        val declaration1: KoTestClassProvider =
            mockk {
                every { hasTestClass(predicate = predicate) } returns true
            }
        val declaration2: KoTestClassProvider =
            mockk {
                every { hasTestClass(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTestClass(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTestClass() returns declaration without test matching the predicate`() {
        // given
        val predicate: (KoClassDeclaration) -> Boolean = { it.numFunctions() == 2 }
        val declaration1: KoTestClassProvider =
            mockk {
                every { hasTestClass(predicate = predicate) } returns true
            }
        val declaration2: KoTestClassProvider =
            mockk {
                every { hasTestClass(predicate = predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTestClass(predicate = predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
