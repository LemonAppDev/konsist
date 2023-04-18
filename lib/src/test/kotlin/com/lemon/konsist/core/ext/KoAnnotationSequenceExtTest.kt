package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoAnnotation
import com.lemon.konsist.testdata.SampleAnnotation
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationSequenceExtTest {
    @Test
    fun `withType() returns annotation with type type1`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotation = mockk {
            every { type } returns type1
        }
        val annotation2: KoAnnotation = mockk {
            every { type } returns type2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withType("type1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withoutType() returns annotation without type type1`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotation = mockk {
            every { type } returns type1
        }
        val annotation2: KoAnnotation = mockk {
            every { type } returns type2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutType("type1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withName() returns annotation with name1`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val annotation1: KoAnnotation = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotation = mockk {
            every { name } returns name2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withName("name1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withName() with KClass syntax returns SampleAnnotation`() {
        // given
        val name1 = "SampleAnnotation"
        val name2 = "OtherAnnotation"
        val annotation1: KoAnnotation = mockk {
        every { name } returns name1
        }
        val annotation2: KoAnnotation = mockk {
        every { name } returns name2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withoutName() returns annotation without name1`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val annotation1: KoAnnotation = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotation = mockk {
            every { name } returns name2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutName("name1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutName() with KClass syntax returns annotation without SampleAnnotation`() {
        // given
        val name1 = "SampleAnnotation"
        val name2 = "OtherAnnotation"
        val annotation1: KoAnnotation = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotation = mockk {
            every { name } returns name2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withFullyQualifiedClassName() returns annotation with fullyQualifiedName1`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val annotation1: KoAnnotation = mockk {
        every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotation = mockk {
        every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withFullyQualifiedClassName("fullyQualifiedName1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withFullyQualifiedClassName() with KClass syntax returns SampleAnnotation`() {
        // given
        val fullyQualifiedName1 = "com.lemon.konsist.testdata.SampleAnnotation"
        val fullyQualifiedName2 = "com.lemon.konsist.testdata.OtherAnnotation"
        val annotation1: KoAnnotation = mockk {
        every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotation = mockk {
        every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withFullyQualifiedClassName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withoutFullyQualifiedClassName() returns annotation without fullyQualifiedName1`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val annotation1: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutFullyQualifiedClassName("fullyQualifiedName1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutFullyQualifiedClassName() with KClass syntax returns annotation without SampleAnnotation`() {
        // given
        val fullyQualifiedName1 = "com.lemon.konsist.testdata.SampleAnnotation"
        val fullyQualifiedName2 = "com.lemon.konsist.testdata.OtherAnnotation"
        val annotation1: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutFullyQualifiedClassName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }
}
