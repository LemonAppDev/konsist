package com.lemonappdev.konsist.api.ext.sequence.koclassdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withInitBlocks
import com.lemonappdev.konsist.api.ext.sequence.withoutInitBlocks
import com.lemonappdev.konsist.core.declaration.KoClassDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoClassDeclarationForInitBlockSequenceExtTest {
    @Test
    fun `withInitBlocks() returns class with init blocks`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasInitBlocks() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasInitBlocks() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withInitBlocks()

        // then
        sut.toList() shouldBeEqualTo listOf(class1)
    }

    @Test
    fun `withoutInitBlocks() returns class without init blocks`() {
        // given
        val class1: KoClassDeclarationImpl = mockk {
            every { hasInitBlocks() } returns true
        }
        val class2: KoClassDeclarationImpl = mockk {
            every { hasInitBlocks() } returns false
        }
        val classes = sequenceOf(class1, class2)

        // when
        val sut = classes.withoutInitBlocks()

        // then
        sut.toList() shouldBeEqualTo listOf(class2)
    }
}
