package com.lemonappdev.konsist.api.ext.sequence.kopropertydeclaration

import com.lemonappdev.konsist.api.ext.sequence.withExtension
import com.lemonappdev.konsist.api.ext.sequence.withoutExtension
import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForExtensionSequenceExtTest {
    @Test
    fun `withExtension() returns property which is extension`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isExtension() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isExtension() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(property1)
    }

    @Test
    fun `withoutExtension() returns property which is not extension`() {
        // given
        val property1: KoPropertyDeclarationImpl = mockk {
            every { isExtension() } returns true
        }
        val property2: KoPropertyDeclarationImpl = mockk {
            every { isExtension() } returns false
        }
        val properties = sequenceOf(property1, property2)

        // when
        val sut = properties.withoutExtension()

        // then
        sut.toList() shouldBeEqualTo listOf(property2)
    }
}
