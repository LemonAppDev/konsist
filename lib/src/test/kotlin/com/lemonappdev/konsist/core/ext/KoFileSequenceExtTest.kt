package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoAnnotation
import com.lemonappdev.konsist.core.declaration.KoFile
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileSequenceExtTest {
    @Test
    fun `withImport() returns file1 with given import`() {
        // given
        val file1: KoFile = mockk {
            every { hasImport() } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImport()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImport() returns file2 without given import`() {
        // given
        val file1: KoFile = mockk {
            every { hasImport() } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImport()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file1 with given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFile = mockk {
            every { hasImport(import) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file2 without given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFile = mockk {
            every { hasImport(import) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file1 with all given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFile = mockk {
            every { hasImport(import1) } returns true
            every { hasImport(import2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file3 without given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFile = mockk {
            every { hasImport(import1) } returns true
            every { hasImport(import2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withSomeImports(String) returns files which have at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFile = mockk {
            every { hasImport(import1) } returns true
            every { hasImport(import2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasImport(import1) } returns false
            every { hasImport(import2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withPackage(String) returns files with one of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: KoFile = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: KoFile = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutPackage(String) returns file3 without given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: KoFile = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: KoFile = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutPackage(package1, package2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withPath(String) returns files with one of given path names`() {
        // given
        val path1 = "SamplePath1"
        val path2 = "SamplePath2"
        val file1: KoFile = mockk {
            every { resideInPath(path1) } returns true
            every { resideInPath(path2) } returns false
        }
        val file2: KoFile = mockk {
            every { resideInPath(path1) } returns false
            every { resideInPath(path2) } returns true
        }
        val file3: KoFile = mockk {
            every { resideInPath(path1) } returns false
            every { resideInPath(path2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withPath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutPath(String) returns file3 without given path names`() {
        // given
        val path1 = "SamplePath1"
        val path2 = "SamplePath2"
        val file1: KoFile = mockk {
            every { resideInPath(path1) } returns true
            every { resideInPath(path2) } returns false
        }
        val file2: KoFile = mockk {
            every { resideInPath(path1) } returns false
            every { resideInPath(path2) } returns true
        }
        val file3: KoFile = mockk {
            every { resideInPath(path1) } returns false
            every { resideInPath(path2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutPath(path1, path2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withAnnotation() returns file1 which has annotation`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val file1: KoFile = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val file2: KoFile = mockk {
            every { annotations } returns listOf()
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotation()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotation() returns file2 which has not annotation`() {
        // given
        val annotation1: KoAnnotation = mockk()
        val file1: KoFile = mockk {
            every { annotations } returns listOf(annotation1)
        }
        val file2: KoFile = mockk {
            every { annotations } returns listOf()
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotation()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotations(String) returns file1 with given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotations(annotation)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotations(String) returns file2 without given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotations(annotation)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotations(String) returns file1 with all given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns true
            every { hasAnnotation(annotation2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotations(String) returns file3 without given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns true
            every { hasAnnotation(annotation2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withSomeAnnotations(String) returns file1 and file2 which have at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns true
            every { hasAnnotation(annotation2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeAnnotations(annotation1, annotation2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withAnnotations(KClass) returns file1 with given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withAnnotations(SampleAnnotation::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotations(KClass) returns file2 without given annotation`() {
        // given
        val annotation = "SampleAnnotation"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutAnnotations(SampleAnnotation::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withAnnotations(KClass) returns file1 with all given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns true
            every { hasAnnotation(annotation2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withAnnotations(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutAnnotations(KClass) returns file3 without given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns true
            every { hasAnnotation(annotation2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutAnnotations(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withSomeAnnotations(KClass) returns file1 and file2 which have at least one of given annotations`() {
        // given
        val annotation1 = "SampleAnnotation1"
        val annotation2 = "SampleAnnotation2"
        val file1: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns true
            every { hasAnnotation(annotation2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasAnnotation(annotation1) } returns false
            every { hasAnnotation(annotation2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeAnnotations(SampleAnnotation1::class, SampleAnnotation2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withTypeAlias() returns file1 which has typealias`() {
        // given
        val file1: KoFile = mockk {
            every { hasTypeAlias() } returns true
        }
        val file2: KoFile = mockk {
            every { hasTypeAlias() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAlias()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAlias() returns file2 which has not typealias`() {
        // given
        val file1: KoFile = mockk {
            every { hasTypeAlias() } returns true
        }
        val file2: KoFile = mockk {
            every { hasTypeAlias() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAlias()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withTypeAliases(String) returns file1 with given typealias`() {
        // given
        val typeAliasName = "SampleTypeAlias"
        val file1: KoFile = mockk {
            every { hasTypeAlias(typeAliasName) } returns true
        }
        val file2: KoFile = mockk {
            every { hasTypeAlias(typeAliasName) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases(typeAliasName)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAliases(String) returns file2 without given typealias`() {
        // given
        val typeAliasName = "SampleTypeAlias"
        val file1: KoFile = mockk {
            every { hasTypeAlias(typeAliasName) } returns true
        }
        val file2: KoFile = mockk {
            every { hasTypeAlias(typeAliasName) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases(typeAliasName)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withTypeAliases(String) returns files with all given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns true
            every { hasTypeAlias(typeAliasName2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns false
            every { hasTypeAlias(typeAliasName2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns false
            every { hasTypeAlias(typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAliases(String) returns file3 without given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns true
            every { hasTypeAlias(typeAliasName2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns false
            every { hasTypeAlias(typeAliasName2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns false
            every { hasTypeAlias(typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withSomeTypeAliases(String) returns files which have at least one of given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns true
            every { hasTypeAlias(typeAliasName2) } returns true
        }
        val file2: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns false
            every { hasTypeAlias(typeAliasName2) } returns true
        }
        val file3: KoFile = mockk {
            every { hasTypeAlias(typeAliasName1) } returns false
            every { hasTypeAlias(typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }
}
