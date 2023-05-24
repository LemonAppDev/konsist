package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.container.KoFileImpl
import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileSequenceExtTest {
    @Test
    fun `withName() returns file with one of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val file1: KoFileImpl = mockk {
            every { name } returns name1
        }
        val file2: KoFileImpl = mockk {
            every { name } returns name2
        }
        val file3: KoFileImpl = mockk {
            every { name } returns name3
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutName() returns file without any of given names`() {
        // given
        val name1 = "sampleName1"
        val name2 = "sampleName2"
        val name3 = "sampleName3"
        val file1: KoFileImpl = mockk {
            every { name } returns name1
        }
        val file2: KoFileImpl = mockk {
            every { name } returns name2
        }
        val file3: KoFileImpl = mockk {
            every { name } returns name3
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutName(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withNameStartingWith() returns file which names starts with one of given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val file1: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns false
            every { hasNameStartingWith(prefix2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withNameStartingWith(prefix1, prefix2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutNameStartingWith() returns file which name not starts with given prefixes`() {
        // given
        val prefix1 = "prefix1"
        val prefix2 = "prefix2"
        val file1: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns true
            every { hasNameStartingWith(prefix2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameStartingWith(prefix1) } returns false
            every { hasNameStartingWith(prefix2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutNameStartingWith(prefix1, prefix2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withNameEndingWith() returns file which names ends with one of suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val file1: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns false
            every { hasNameEndingWith(suffix2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withNameEndingWith(suffix1, suffix2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutNameEndingWith() returns file which name not ends with given suffixes`() {
        // given
        val suffix1 = "suffix1"
        val suffix2 = "suffix2"
        val file1: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns true
            every { hasNameEndingWith(suffix2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameEndingWith(suffix1) } returns false
            every { hasNameEndingWith(suffix2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutNameEndingWith(suffix1, suffix2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withNameContaining() returns file which names contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val file1: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns false
            every { hasNameContaining(text2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withNameContaining(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutNameContaining() returns file which name not contains any of given texts`() {
        // given
        val text1 = "text1"
        val text2 = "text2"
        val file1: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns true
            every { hasNameContaining(text2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameContaining(text1) } returns false
            every { hasNameContaining(text2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutNameContaining(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withNameMatching() returns file which names contains given one of texts`() {
        // given
        val text1 = Regex("[1-9]")
        val text2 = Regex("[a-z]")
        val file1: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns false
            every { hasNameMatching(text2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withNameMatching(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutNameMatching() returns file which name not contains given texts`() {
        // given
        val text1 = Regex("[1-9]")
        val text2 = Regex("[a-z]")
        val file1: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns true
            every { hasNameMatching(text2) } returns false
        }
        val file3: KoFileImpl = mockk {
            every { hasNameMatching(text1) } returns false
            every { hasNameMatching(text2) } returns false
        }
        val file = sequenceOf(file1, file2, file3)

        // when
        val sut = file.withoutNameMatching(text1, text2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withPath(String) returns files with one of given paths`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val file1: KoFileImpl = mockk {
            every { resideInPath(path1) } returns true
            every { resideInPath(path2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(path1) } returns false
            every { resideInPath(path2) } returns true
        }
        val file3: KoFileImpl = mockk {
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
    fun `withoutPath(String) returns file without any of given path`() {
        // given
        val path1 = "com/sample/samplepath1.."
        val path2 = "..samplepath2"
        val file1: KoFileImpl = mockk {
            every { resideInPath(path1) } returns true
            every { resideInPath(path2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInPath(path1) } returns false
            every { resideInPath(path2) } returns true
        }
        val file3: KoFileImpl = mockk {
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
    fun `withProjectPath(String) returns files with one of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val file1: KoFileImpl = mockk {
            every { resideInRootProjectPath(projectPath1) } returns true
            every { resideInRootProjectPath(projectPath2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInRootProjectPath(projectPath1) } returns false
            every { resideInRootProjectPath(projectPath2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { resideInRootProjectPath(projectPath1) } returns false
            every { resideInRootProjectPath(projectPath2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withRootProjectPath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }

    @Test
    fun `withoutProjectPath(String) returns file without any of given project paths`() {
        // given
        val projectPath1 = "com/sample/sampleProjectPath1.."
        val projectPath2 = "..sampleProjectPath2"
        val file1: KoFileImpl = mockk {
            every { resideInRootProjectPath(projectPath1) } returns true
            every { resideInRootProjectPath(projectPath2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { resideInRootProjectPath(projectPath1) } returns false
            every { resideInRootProjectPath(projectPath2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { resideInRootProjectPath(projectPath1) } returns false
            every { resideInRootProjectPath(projectPath2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withoutRootProjectPath(projectPath1, projectPath2)

        // then
        sut.toList() shouldBeEqualTo listOf(file3)
    }

    @Test
    fun `withImport() returns file with any import`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasImports() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImport() returns file without any import`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasImports() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file with given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFileImpl = mockk {
            every { hasImports(import) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file without given import`() {
        // given
        val import = "SampleImport"
        val file1: KoFileImpl = mockk {
            every { hasImports(import) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports(import)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withImports(String) returns file with all of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileImpl = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import1, import2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutImports(String) returns file without any of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileImpl = mockk {
            every { hasImports(import1, import2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import1, import2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutImports(import1, import2)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeImports(String) returns files with at least one of given imports`() {
        // given
        val import1 = "SampleImport1"
        val import2 = "SampleImport2"
        val file1: KoFileImpl = mockk {
            every { hasImports(import1) } returns true
            every { hasImports(import2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { hasImports(import1) } returns false
            every { hasImports(import2) } returns false
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
        val file1: KoFileImpl = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: KoFileImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: KoFileImpl = mockk {
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
    fun `withoutPackage(String) returns file without any of given package names`() {
        // given
        val package1 = "SamplePackage1"
        val package2 = "SamplePackage2"
        val file1: KoFileImpl = mockk {
            every { hasPackage(package1) } returns true
            every { hasPackage(package2) } returns false
        }
        val file2: KoFileImpl = mockk {
            every { hasPackage(package1) } returns false
            every { hasPackage(package2) } returns true
        }
        val file3: KoFileImpl = mockk {
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

    @Test
    fun `withTypeAlias() returns file with typealias`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases()

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAlias() returns file without typealias`() {
        // given
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases() } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases() } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases()

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withTypeAliases(String) returns file with given typealias`() {
        // given
        val typeAliasName = "SampleTypeAlias"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases(typeAliasName)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAliases(String) returns file without given typealias`() {
        // given
        val typeAliasName = "SampleTypeAlias"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases(typeAliasName)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withTypeAliases(String) returns files with all of given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName1, typeAliasName2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName1, typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1)
    }

    @Test
    fun `withoutTypeAliases(String) returns file without any of given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName1, typeAliasName2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName1, typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2)

        // when
        val sut = files.withoutTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file2)
    }

    @Test
    fun `withSomeTypeAliases(String) returns files with at least one of given typeAliasNames`() {
        // given
        val typeAliasName1 = "SampleTypeAlias1"
        val typeAliasName2 = "SampleTypeAlias2"
        val file1: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName1) } returns true
            every { hasTypeAliases(typeAliasName2) } returns true
        }
        val file2: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName1) } returns false
            every { hasTypeAliases(typeAliasName2) } returns true
        }
        val file3: KoFileImpl = mockk {
            every { hasTypeAliases(typeAliasName1) } returns false
            every { hasTypeAliases(typeAliasName2) } returns false
        }
        val files = sequenceOf(file1, file2, file3)

        // when
        val sut = files.withSomeTypeAliases(typeAliasName1, typeAliasName2)

        // then
        sut.toList() shouldBeEqualTo listOf(file1, file2)
    }
}
