package com.lemonappdev.konsist.api.ext.sequence.kofile

import com.lemonappdev.konsist.api.ext.sequence.withAnnotationOf
import com.lemonappdev.konsist.api.ext.sequence.withAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withSomeAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withSomeAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotationOf
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotations
import com.lemonappdev.konsist.api.ext.sequence.withoutAnnotationsOf
import com.lemonappdev.konsist.core.container.KoFileImpl
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoFileForAnnotationSequenceExtTest {
    @Test
    fun `withAnnotation() returns file which has any annotation`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasAnnotations() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotations() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotations()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotation() returns file which has not any annotation`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasAnnotations() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotations() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotations()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotations(String) returns file with given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val file1: KoFileImpl = mockk {
            every { hasAnnotations(annotation) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotations(annotation) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotations(annotation)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotations(String) returns file without given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val file1: KoFileImpl = mockk {
            every { hasAnnotations(annotation) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotations(annotation) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotations(annotation)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotations(String) returns file with all of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFileImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotations(String) returns file without any of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFileImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotations(annotation1, annotation2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeAnnotations(String) returns files with at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFileImpl = mockk {
            every { hasAnnotations(annotation1) } returns true
            every { hasAnnotations(annotation2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { hasAnnotations(annotation1) } returns false
            every { hasAnnotations(annotation2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withAnnotationOf(KClass) returns file with given annotation`() {
        // given
        val qualifiedName1 = "com.lemonappdev.konsist.testdata.SampleAnnotation"
        val qualifiedName2 = "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val file1: KoFileImpl = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val file2: KoFileImpl = mockk {
            every { annotations } returns listOf(annotation2)
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotationOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotationOf(KClass) returns file without given annotation`() {
        // given
        val qualifiedName1 = "com.lemonappdev.konsist.testdata.SampleAnnotation"
        val qualifiedName2 = "com.lemonappdev.konsist.testdata.NonExistingAnnotation"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns qualifiedName2
        }
        val file1: KoFileImpl = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val file2: KoFileImpl = mockk {
            every { annotations } returns listOf(annotation2)
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotationOf<SampleAnnotation>()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotationsOf(KClass) returns file with all of given annotations`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotationsOf(KClass) returns file without any of given annotations`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeAnnotationsOf(KClass) returns files with at least one of given annotations`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns true
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { hasAnnotationsOf(SampleAnnotation1::class) } returns false
            every { hasAnnotationsOf(SampleAnnotation2::class) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeAnnotationsOf(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }
}
