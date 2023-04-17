package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoObject
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectSequenceExtTest {
    @Test
    fun `withDataModifier() returns object with data modifier`() {
        // given
        val object1: KoObject = mockk()
        every { object1.hasDataModifier() } returns true
        val object2: KoObject = mockk()
        every { object2.hasDataModifier() } returns false
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object1)
    }

    @Test
    fun `withDataModifier() returns empty list when none object has data modifier`() {
        // given
        val object1: KoObject = mockk()
        every { object1.hasDataModifier() } returns false
        val object2: KoObject = mockk()
        every { object2.hasDataModifier() } returns false
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withDataModifier()

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutDataModifier() returns object without data modifier`() {
        // given
        val object1: KoObject = mockk()
        every { object1.hasDataModifier() } returns true
        val object2: KoObject = mockk()
        every { object2.hasDataModifier() } returns false
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object2)
    }

    @Test
    fun `withoutDataModifier() returns empty list when all objects have data modifier`() {
        // given
        val object1: KoObject = mockk()
        every { object1.hasDataModifier() } returns true
        val object2: KoObject = mockk()
        every { object2.hasDataModifier() } returns true
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutDataModifier() returns list with objects when none object has data modifier`() {
        // given
        val object1: KoObject = mockk()
        every { object1.hasDataModifier() } returns false
        val object2: KoObject = mockk()
        every { object2.hasDataModifier() } returns false
        val objects = sequenceOf(object1, object2)

        // when
        val sut = objects.withoutDataModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(object1, object2)
    }
}
