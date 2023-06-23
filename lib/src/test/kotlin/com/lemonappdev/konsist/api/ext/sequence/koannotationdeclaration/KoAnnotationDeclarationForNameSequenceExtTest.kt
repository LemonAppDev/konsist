package com.lemonappdev.konsist.api.ext.sequence.koannotationdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withFullyQualifiedClassName
import com.lemonappdev.konsist.api.ext.sequence.withName
import com.lemonappdev.konsist.api.ext.sequence.withoutFullyQualifiedClassName
import com.lemonappdev.konsist.api.ext.sequence.withoutName
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForNameSequenceExtTest {
    @Test
    fun `withName() returns annotations with given name`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withName(name1)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
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
    fun `withoutName() returns annotation without given name`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { name } returns name2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutName(name1)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutName() returns annotation without any of given names`() {
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
    fun `withFullyQualifiedClassName() returns annotation with given fullyQualifiedName`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withFullyQualifiedClassName(fullyQualifiedName1)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation1)
    }

    @Test
    fun `withFullyQualifiedClassName() returns annotations with one of given fullyQualifiedName`() {
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
    fun `withoutFullyQualifiedClassName() returns annotation without given fullyQualifiedName`() {
        // given
        val fullyQualifiedName1 = "fullyQualifiedName1"
        val fullyQualifiedName2 = "fullyQualifiedName2"
        val annotation1: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName1
        }
        val annotation2: KoAnnotationDeclarationImpl = mockk {
            every { fullyQualifiedName } returns fullyQualifiedName2
        }
        val annotations = sequenceOf(annotation1, annotation2)

        // when
        val sut = annotations.withoutFullyQualifiedClassName(fullyQualifiedName1)

        // then
        sut.toList() shouldBeEqualTo listOf(annotation2)
    }

    @Test
    fun `withoutFullyQualifiedClassName() returns annotation without any of given fullyQualifiedNames`() {
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
