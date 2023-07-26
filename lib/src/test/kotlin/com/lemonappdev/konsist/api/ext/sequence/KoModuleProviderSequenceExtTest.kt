package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoModuleProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoModuleProviderSequenceExtTest {
    @Test
    fun `withModule(String) returns file with given module`() {
        // given
        val module = "module"
        val file1: KoModuleProvider = mockk {
            every { resideInModule(module) } returns true
        }
        val file2: KoModuleProvider = mockk {
            every { resideInModule(module) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withModule(module)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withModule(String) returns files with one of given modules`() {
        // given
        val module1 = "module1"
        val module2 = "module2"
        val file1: KoModuleProvider = mockk {
            every { resideInModule(module1) } returns true
            every { resideInModule(module2) } returns false
        }
        val file2: KoModuleProvider = mockk {
            every { resideInModule(module1) } returns false
            every { resideInModule(module2) } returns true
        }
        val file3: KoModuleProvider = mockk {
            every { resideInModule(module1) } returns false
            every { resideInModule(module2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withModule(module1, module2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutModule(String) returns file without given module`() {
        // given
        val module = "module"
        val file1: KoModuleProvider = mockk {
            every { resideInModule(module) } returns true
        }
        val file2: KoModuleProvider = mockk {
            every { resideInModule(module) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutModule(module)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withoutModule(String) returns file without any of given modules`() {
        // given
        val module1 = "module1"
        val module2 = "module2"
        val file1: KoModuleProvider = mockk {
            every { resideInModule(module1) } returns true
            every { resideInModule(module2) } returns false
        }
        val file2: KoModuleProvider = mockk {
            every { resideInModule(module1) } returns false
            every { resideInModule(module2) } returns true
        }
        val file3: KoModuleProvider = mockk {
            every { resideInModule(module1) } returns false
            every { resideInModule(module2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutModule(module1, module2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }
}
