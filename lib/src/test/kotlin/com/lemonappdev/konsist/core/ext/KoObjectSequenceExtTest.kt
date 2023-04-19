package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoObject
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectSequenceExtTest {
    @Test
    fun `withDataModifier() returns object with data modifier`() {
        // given
        val object1: KoObject = mockk {
            every { hasDataModifier() } returns true
        }
        val object2: KoObject = mockk {
            every { hasDataModifier() } returns false
        }
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object1)
    }

    @Test
    fun `withoutDataModifier() returns object without data modifier`() {
        // given
        val object1: KoObject = mockk {
            every { hasDataModifier() } returns true
        }
        val object2: KoObject = mockk {
            every { hasDataModifier() } returns false
        }
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object2)
    }
}
