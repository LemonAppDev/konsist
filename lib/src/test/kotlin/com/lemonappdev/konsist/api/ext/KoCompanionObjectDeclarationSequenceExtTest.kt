package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoCompanionObjectDeclarationSequenceExtTest {
    @Test
    fun `withName() returns companion object with name`() {
        // given
        val companionObject1: KoCompanionObjectDeclarationImpl = mockk {
            every { hasName() } returns true
        }
        val companionObject2: KoCompanionObjectDeclarationImpl = mockk {
            every { hasName() } returns false
        }
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withName()

        // then
        sut.toList() shouldBeEqualTo listOf(companionObject1)
    }

    @Test
    fun `withoutName() returns companion object without name`() {
        // given
        val companionObject1: KoCompanionObjectDeclarationImpl = mockk {
            every { hasName() } returns true
        }
        val companionObject2: KoCompanionObjectDeclarationImpl = mockk {
            every { hasName() } returns false
        }
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withoutName()

        // then
        sut.toList() shouldBeEqualTo listOf(companionObject2)
    }
}
