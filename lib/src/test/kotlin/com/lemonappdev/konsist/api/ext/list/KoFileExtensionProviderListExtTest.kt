<<<<<<<< HEAD:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/list/KoFileExtensionProviderListExtTest.kt
package com.lemonappdev.konsist.api.ext.list
========
package com.lemonappdev.konsist.api.ext.sequence
>>>>>>>> main:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/sequence/KoFileExtensionProviderSequenceExtTest.kt

import com.lemonappdev.konsist.api.provider.KoFileExtensionProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
<<<<<<<< HEAD:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/list/KoFileExtensionProviderListExtTest.kt
class KoFileExtensionProviderListExtTest {
========
class KoFileExtensionProviderSequenceExtTest {
>>>>>>>> main:lib/src/test/kotlin/com/lemonappdev/konsist/api/ext/sequence/KoFileExtensionProviderSequenceExtTest.kt
    @Test
    fun `withExtension() returns file with given extension`() {
        // given
        val extension = "sampleExtension"
        val file1: KoFileExtensionProvider = mockk {
            every { hasExtension(extension) } returns true
        }
        val file2: KoFileExtensionProvider = mockk {
            every { hasExtension(extension) } returns false
        }
        val file = listOf(file1, file2)

        // when
        val sut = file.withExtension(extension)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withExtension() returns file with one of given extensions`() {
        // given
        val extension1 = "sampleExtension1"
        val extension2 = "sampleExtension2"
        val extension3 = "sampleExtension3"
        val file1: KoFileExtensionProvider = mockk {
            every { hasExtension(extension1) } returns true
            every { hasExtension(extension2) } returns false
            every { hasExtension(extension3) } returns false
        }
        val file2: KoFileExtensionProvider = mockk {
            every { hasExtension(extension1) } returns false
            every { hasExtension(extension2) } returns true
            every { hasExtension(extension3) } returns false
        }
        val file3: KoFileExtensionProvider = mockk {
            every { hasExtension(extension1) } returns false
            every { hasExtension(extension2) } returns false
            every { hasExtension(extension3) } returns true
        }
        val file = listOf(file1, file2, file3)

        // when
        val sut = file.withExtension(extension1, extension2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutExtension() returns file without given extension`() {
        // given
        val extension = "sampleExtension"
        val file1: KoFileExtensionProvider = mockk {
            every { hasExtension(extension) } returns true
        }
        val file2: KoFileExtensionProvider = mockk {
            every { hasExtension(extension) } returns false
        }
        val file = listOf(file1, file2)

        // when
        val sut = file.withoutExtension(extension)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutExtension() returns file without any of given extensions`() {
        // given
        val extension1 = "sampleExtension1"
        val extension2 = "sampleExtension2"
        val extension3 = "sampleExtension3"
        val file1: KoFileExtensionProvider = mockk {
            every { hasExtension(extension1) } returns true
            every { hasExtension(extension2) } returns false
            every { hasExtension(extension3) } returns false
        }
        val file2: KoFileExtensionProvider = mockk {
            every { hasExtension(extension1) } returns false
            every { hasExtension(extension2) } returns true
            every { hasExtension(extension3) } returns false
        }
        val file3: KoFileExtensionProvider = mockk {
            every { hasExtension(extension1) } returns false
            every { hasExtension(extension2) } returns false
            every { hasExtension(extension3) } returns true
        }
        val file = listOf(file1, file2, file3)

        // when
        val sut = file.withoutExtension(extension1, extension2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }
}
