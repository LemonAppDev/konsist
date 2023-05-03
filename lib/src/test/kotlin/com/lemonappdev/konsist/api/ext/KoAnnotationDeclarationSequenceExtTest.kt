package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.ext.sequence.withFullyQualifiedClassName
import com.lemonappdev.konsist.api.ext.sequence.withName
import com.lemonappdev.konsist.api.ext.sequence.withType
import com.lemonappdev.konsist.api.ext.sequence.withTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutFullyQualifiedClassName
import com.lemonappdev.konsist.api.ext.sequence.withoutName
import com.lemonappdev.konsist.api.ext.sequence.withoutType
import com.lemonappdev.konsist.api.ext.sequence.withoutTypeOf
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationSequenceExtTest {
    @Test
    fun `withType(String) returns annotations with one of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns true
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
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
    fun `withoutType(String) returns annotation3 without given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns true
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    @Test
    fun `withTypeOf(KClass) returns annotations with one of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleAnnotation1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleAnnotation2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withTypeOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns annotation without any of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleAnnotation1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleAnnotation2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutTypeOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    //  'every { representsType<SampleAnnotation>() } returns true' doesn't work because there is a bug in mockk
    @Test
    fun `withTypeOf() with KClass syntax returns SampleAnnotation`() {
        // given
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleAnnotation"
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withTypeOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withoutTypeOf() with KClass syntax returns annotation without SampleAnnotation`() {
        // given
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleAnnotation"
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutTypeOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withName() returns annotations with one of given names`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val name3 = "name3"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name2
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name3
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutName() returns annotation without any given name`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val name3 = "name3"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name2
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name3
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }

    @Test
    fun `withFullyQualifiedClassName() returns annotations with one of given fullyQualifiedNames`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName3
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withFullyQualifiedClassName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1, annotation2)
    }

    @Test
    fun `withoutFullyQualifiedClassName() returns annotation without any given fullyQualifiedName`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val fullyQualifiedName3 = "fullyQualifiedName3"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotation3: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName3
        }
        val annotations = sequenceOf(annotation1, annotation2, annotation3)

        // when
        val sut = annotations.withoutFullyQualifiedClassName(fullyQualifiedName1, fullyQualifiedName2)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation3)
    }
}
