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
        val companionObject1: KoCompanionObject = mockk()
        every { companionObject1.hasExplicitName() } returns true
        val companionObject2: KoCompanionObject = mockk()
        every { companionObject2.hasExplicitName() } returns false
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withExplicitName()

        // then
        sut.toList() shouldBeEqualTo listOf(companionObject1)
    }

    @Test
    fun `withExplicitName() returns empty list when none companion object has explicit type`() {
        // given
        val companionObject1: KoCompanionObject = mockk()
        every { companionObject1.hasExplicitName() } returns false
        val companionObject2: KoCompanionObject = mockk()
        every { companionObject2.hasExplicitName() } returns false
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withExplicitName()

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutExplicitName() returns companion object without explicit name`() {
        // given
        val companionObject1: KoCompanionObject = mockk()
        every { companionObject1.hasExplicitName() } returns true
        val companionObject2: KoCompanionObject = mockk()
        every { companionObject2.hasExplicitName() } returns false
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withoutExplicitName()

        // then
        sut.toList() shouldBeEqualTo listOf(companionObject2)
    }

    @Test
    fun `withoutExplicitName() returns empty list when all companion objects have explicit type`() {
        // given
        val companionObject1: KoCompanionObject = mockk()
        every { companionObject1.hasExplicitName() } returns true
        val companionObject2: KoCompanionObject = mockk()
        every { companionObject2.hasExplicitName() } returns true
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withoutExplicitName()

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutExplicitName() returns list with companion objects when none companion object has explicit type`() {
        // given
        val companionObject1: KoCompanionObject = mockk()
        every { companionObject1.hasExplicitName() } returns false
        val companionObject2: KoCompanionObject = mockk()
        every { companionObject2.hasExplicitName() } returns false
        val companionObjects = sequenceOf(companionObject1, companionObject2)

        // when
        val sut = companionObjects.withoutExplicitName()

        // then
        sut.toList() shouldBeEqualTo listOf(companionObject1, companionObject2)
    }
}
