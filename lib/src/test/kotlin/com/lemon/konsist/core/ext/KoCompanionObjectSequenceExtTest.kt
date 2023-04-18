package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoCompanionObject
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoCompanionObjectSequenceExtTest {
    @Test
    fun `withExplicitName() returns companion object with explicit name`() {
        // given
        val companionObject1: KoCompanionObject = mockk {
            every { hasExplicitName() } returns true
        }
        val companionObject2: KoCompanionObject = mockk {
            every { hasExplicitName() } returns false
        }
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withExplicitName()

        // then
        sut.toList() shouldBeEqualTo listOf(companionObject1)
    }

    @Test
    fun `withoutExplicitName() returns companion object without explicit name`() {
        // given
        val companionObject1: KoCompanionObject = mockk {
            every { hasExplicitName() } returns true
        }
        val companionObject2: KoCompanionObject = mockk {
            every { hasExplicitName() } returns false
        }
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withoutExplicitName()

        // then
        sut.toList() shouldBeEqualTo listOf(companionObject2)
    }
}
