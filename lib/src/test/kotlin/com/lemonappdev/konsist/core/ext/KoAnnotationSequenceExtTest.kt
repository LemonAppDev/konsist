package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationSequenceExtTest {
    @Test
    fun `withType() returns annotations with type type1 or type2`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotation = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns true
        }
        val annotation2: KoAnnotation = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation3: KoAnnotation = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutType() returns annotation without type type1`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotation = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns true
        }
        val annotation2: KoAnnotation = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation3: KoAnnotation = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    //  'every { representsType<SampleAnnotation>() } returns true' doesn't work because there is a bug in mockk
    @Test
    fun `withType() with KClass syntax returns SampleAnnotation`() {
        // given
        val annotation1: KoAnnotation = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleAnnotation"
        }
        val annotation2: KoAnnotation = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withType<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withoutType() with KClass syntax returns annotation without SampleAnnotation`() {
        // given
        val annotation1: KoAnnotation = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleAnnotation"
        }
        val annotation2: KoAnnotation = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutType<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withName() returns annotations with name1 or name2`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val name3 = "name3"
        val annotation1: KoAnnotation = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotation = mockk {
            every { name } returns name2
        }
        val annotation3: KoAnnotation = mockk {
            every { name } returns name3
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutName() returns annotation without name1 and name2`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val name3 = "name3"
        val annotation1: KoAnnotation = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotation = mockk {
            every { name } returns name2
        }
        val annotation3: KoAnnotation = mockk {
            every { name } returns name3
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    @Test
    fun `withFullyQualifiedClassName() returns annotation with fullyQualifiedName1 or fullyQualifiedName1`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val annotation1: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotation3: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName3
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withFullyQualifiedClassName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutFullyQualifiedClassName() returns annotation without fullyQualifiedName1 and fullyQualifiedName3`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val annotation1: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotation3: KoAnnotation = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName3
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutFullyQualifiedClassName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }
}
