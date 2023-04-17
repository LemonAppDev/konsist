package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoAnnotation
import com.lemon.konsist.testdata.NonExistingAnnotation
import com.lemon.konsist.testdata.SampleAnnotation
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationsSequenceExtTest {
    @Test
    fun `withType() returns annotation with type type1`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val type1 = "type1"
        every { annotation1.type } returns type1
        val annotation2: KoAnnotation = mockk()
        val type2 = "type2"
        every { annotation2.type } returns type2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withType("type1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withType() returns empty list when there is no annotation with given type`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val type1 = "type1"
        every { annotation1.type } returns type1
        val annotation2: KoAnnotation = mockk()
        val type2 = "type2"
        every { annotation2.type } returns type2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withType("type")

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutType() returns annotation without type type1`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val type1 = "type1"
        every { annotation1.type } returns type1
        val annotation2: KoAnnotation = mockk()
        val type2 = "type2"
        every { annotation2.type } returns type2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutType("type1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutType() returns annotations list when there is no annotation with given type`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val type1 = "type1"
        every { annotation1.type } returns type1
        val annotation2: KoAnnotation = mockk()
        val type2 = "type2"
        every { annotation2.type } returns type2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutType("type")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withName() returns annotation with name1`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val name1 = "name1"
        every { annotation1.name } returns name1
        val annotation2: KoAnnotation = mockk()
        val name2 = "name2"
        every { annotation2.name } returns name2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withName("name1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withName() returns empty list when there is no annotation with given name`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val name1 = "name1"
        every { annotation1.name } returns name1
        val annotation2: KoAnnotation = mockk()
        val name2 = "name2"
        every { annotation2.name } returns name2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withName("name")

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withName() with KClass syntax returns SampleAnnotation`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val name1 = "SampleAnnotation"
        every { annotation1.name } returns name1
        val annotation2: KoAnnotation = mockk()
        val name2 = "OtherAnnotation"
        every { annotation2.name } returns name2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withName() with KClass syntax returns empty list when there is no annotation with given name`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val name1 = "FirstAnnotation"
        every { annotation1.name } returns name1
        val annotation2: KoAnnotation = mockk()
        val name2 = "SecondAnnotation"
        every { annotation2.name } returns name2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutName() returns annotation without name1`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val name1 = "name1"
        every { annotation1.name } returns name1
        val annotation2: KoAnnotation = mockk()
        val name2 = "name2"
        every { annotation2.name } returns name2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutName("name1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutName() returns annotations list when there is no annotation with given name`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val name1 = "name1"
        every { annotation1.name } returns name1
        val annotation2: KoAnnotation = mockk()
        val name2 = "name2"
        every { annotation2.name } returns name2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutName("name")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutName() with KClass syntax returns annotation without SampleAnnotation`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val name1 = "SampleAnnotation"
        every { annotation1.name } returns name1
        val annotation2: KoAnnotation = mockk()
        val name2 = "OtherAnnotation"
        every { annotation2.name } returns name2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutName() with KClass syntax returns annotations list when there is no annotation with given name`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val name1 = "FirstAnnotation"
        every { annotation1.name } returns name1
        val annotation2: KoAnnotation = mockk()
        val name2 = "SecondAnnotation"
        every { annotation2.name } returns name2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withFullyQualifiedClassName() returns annotation with fullyQualifiedName1`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val fullyQualifiedName1 = "fullyQualifiedName1"
        every { annotation1.fullyQualifiedName } returns fullyQualifiedName1
        val annotation2: KoAnnotation = mockk()
        val fullyQualifiedName2 = "fullyQualifiedName2"
        every { annotation2.fullyQualifiedName } returns fullyQualifiedName2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withFullyQualifiedClassName("fullyQualifiedName1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withFullyQualifiedClassName() returns empty list when there is no annotation with given name`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val fullyQualifiedName1 = "fullyQualifiedName1"
        every { annotation1.fullyQualifiedName } returns fullyQualifiedName1
        val annotation2: KoAnnotation = mockk()
        val fullyQualifiedName2 = "fullyQualifiedName2"
        every { annotation2.fullyQualifiedName } returns fullyQualifiedName2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withFullyQualifiedClassName("fullyQualifiedName")

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withFullyQualifiedClassName() with KClass syntax returns SampleAnnotation`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val fullyQualifiedName1 = "com.lemon.konsist.testdata.SampleAnnotation"
        every { annotation1.fullyQualifiedName } returns fullyQualifiedName1
        val annotation2: KoAnnotation = mockk()
        val fullyQualifiedName2 = "com.lemon.konsist.testdata.OtherAnnotation"
        every { annotation2.fullyQualifiedName } returns fullyQualifiedName2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withFullyQualifiedClassName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withFullyQualifiedClassName() with KClass syntax returns empty list when there is no annotation with given name`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val fullyQualifiedName1 = "com.lemon.konsist.testdata.FirstAnnotation"
        every { annotation1.fullyQualifiedName } returns fullyQualifiedName1
        val annotation2: KoAnnotation = mockk()
        val fullyQualifiedName2 = "com.lemon.konsist.testdata.SecondAnnotation"
        every { annotation2.fullyQualifiedName } returns fullyQualifiedName2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withFullyQualifiedClassName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutFullyQualifiedClassName() returns annotation without fullyQualifiedName1`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val fullyQualifiedName1 = "fullyQualifiedName1"
        every { annotation1.fullyQualifiedName } returns fullyQualifiedName1
        val annotation2: KoAnnotation = mockk()
        val fullyQualifiedName2 = "fullyQualifiedName2"
        every { annotation2.fullyQualifiedName } returns fullyQualifiedName2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutFullyQualifiedClassName("fullyQualifiedName1")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutFullyQualifiedClassName() returns annotations list when there is no annotation with given name`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val fullyQualifiedName1 = "fullyQualifiedName1"
        every { annotation1.fullyQualifiedName } returns fullyQualifiedName1
        val annotation2: KoAnnotation = mockk()
        val fullyQualifiedName2 = "fullyQualifiedName2"
        every { annotation2.fullyQualifiedName } returns fullyQualifiedName2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutFullyQualifiedClassName("fullyQualifiedName")

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutFullyQualifiedClassName() with KClass syntax returns annotation without SampleAnnotation`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val fullyQualifiedName1 = "com.lemon.konsist.testdata.SampleAnnotation"
        every { annotation1.fullyQualifiedName } returns fullyQualifiedName1
        val annotation2: KoAnnotation = mockk()
        val fullyQualifiedName2 = "com.lemon.konsist.testdata.OtherAnnotation"
        every { annotation2.fullyQualifiedName } returns fullyQualifiedName2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutFullyQualifiedClassName<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutFullyQualifiedClassName() with KClass syntax returns annotations list when there is no annotation with given name`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val fullyQualifiedName1 = "com.lemon.konsist.testdata.FirstAnnotation"
        every { annotation1.fullyQualifiedName } returns fullyQualifiedName1
        val annotation2: KoAnnotation = mockk()
        val fullyQualifiedName2 = "com.lemon.konsist.testdata.SecondAnnotation"
        every { annotation2.fullyQualifiedName } returns fullyQualifiedName2
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutFullyQualifiedClassName<NonExistingAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }
}
