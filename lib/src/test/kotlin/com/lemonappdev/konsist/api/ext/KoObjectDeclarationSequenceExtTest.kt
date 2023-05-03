package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.ext.withDataModifier
import com.lemonappdev.konsist.api.ext.withoutDataModifier
import com.lemonappdev.konsist.core.declaration.KoObjectDeclaration
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationSequenceExtTest {
    @Test
    fun `withDataModifier() returns object with data modifier`() {
        // given
        val object1: KoObjectDeclaration = mockk {
            every { hasDataModifier() } returns true
        }
        val object2: KoObjectDeclaration = mockk {
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
        val object1: KoObjectDeclaration = mockk {
            every { hasDataModifier() } returns true
        }
        val object2: KoObjectDeclaration = mockk {
            every { hasDataModifier() } returns false
        }
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object2)
    }
}
