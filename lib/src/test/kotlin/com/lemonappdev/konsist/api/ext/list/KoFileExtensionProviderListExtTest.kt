package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoFileExtensionProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoFileExtensionProviderListExtTest {
    @Test
    fun `withExtension() returns declaration with given extension`() {
        // given
        val extension = "sampleExtension"
        val declaration1: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension) } returns true
            }
        val declaration2: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExtension(extension)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withExtension() returns declaration with one of given extensions`() {
        // given
        val extension1 = "sampleExtension1"
        val extension2 = "sampleExtension2"
        val extension3 = "sampleExtension3"
        val declaration1: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension1) } returns true
                every { hasExtension(extension2) } returns false
                every { hasExtension(extension3) } returns false
            }
        val declaration2: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension1) } returns false
                every { hasExtension(extension2) } returns true
                every { hasExtension(extension3) } returns false
            }
        val declaration3: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension1) } returns false
                every { hasExtension(extension2) } returns false
                every { hasExtension(extension3) } returns true
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withExtension(extension1, extension2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutExtension() returns declaration without given extension`() {
        // given
        val extension = "sampleExtension"
        val declaration1: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension) } returns true
            }
        val declaration2: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExtension(extension)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutExtension() returns declaration without any of given extensions`() {
        // given
        val extension1 = "sampleExtension1"
        val extension2 = "sampleExtension2"
        val extension3 = "sampleExtension3"
        val declaration1: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension1) } returns true
                every { hasExtension(extension2) } returns false
                every { hasExtension(extension3) } returns false
            }
        val declaration2: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension1) } returns false
                every { hasExtension(extension2) } returns true
                every { hasExtension(extension3) } returns false
            }
        val declaration3: KoFileExtensionProvider =
            mockk {
                every { hasExtension(extension1) } returns false
                every { hasExtension(extension2) } returns false
                every { hasExtension(extension3) } returns true
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutExtension(extension1, extension2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
