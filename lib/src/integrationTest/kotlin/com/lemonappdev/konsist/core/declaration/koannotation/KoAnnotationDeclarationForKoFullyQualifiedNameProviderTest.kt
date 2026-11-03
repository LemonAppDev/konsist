package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `annotation-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.FixtureAnnotation"
    }

    /*
    The "FixtureAnnotation" contains "Annotation" in name.
    Test makes sure that correct fully qualified name is returned.
     */
    @Test
    fun `annotation-fully-qualified-name-when-other-import-contains-its-name`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-when-other-import-contains-its-name")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.testpackage.Annotation"
    }

    @Test
    fun `annotation-fully-qualified-name-where-annotation-is-defined-in-the-same-file-with-package`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-where-annotation-is-defined-in-the-same-file-with-package")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.FixtureAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name-where-annotation-is-defined-in-the-same-file-without-package`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-where-annotation-is-defined-in-the-same-file-without-package")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "FixtureAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name-with-default-import`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-with-default-import")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "kotlin.Deprecated"
    }

    @Test
    fun `annotation-fully-qualified-name-with-single-wildcard-import`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-with-single-wildcard-import")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.FixtureAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name-with-multiple-wildcard-import`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-with-multiple-wildcard-import")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.FixtureAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name-from-the-same-package`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-from-the-same-package")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.FixtureAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name-with-import-alias`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-with-import-alias")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.FixtureAnnotation"
    }

    @Test
    fun `annotation-in-file-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("annotation-in-file-fully-qualified-name")
                .files
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.FixtureAnnotation"
    }

    @Test
    fun `annotation-in-file-fully-qualified-name-where-annotation-is-defined-in-the-same-file-with-package`() {
        // given
        val sut =
            getSnippetFile("annotation-in-file-fully-qualified-name-where-annotation-is-defined-in-the-same-file-with-package")
                .files
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.fixturepackage.FixtureAnnotation"
    }

    @Test
    fun `annotation-in-file-fully-qualified-name-where-annotation-is-defined-in-the-same-file-without-package`() {
        // given
        val sut =
            getSnippetFile("annotation-in-file-fully-qualified-name-where-annotation-is-defined-in-the-same-file-without-package")
                .files
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "FixtureAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name-of-nested-annotation-used-by-members`() {
        // given
        val scope = getSnippetFile("annotation-fully-qualified-name-of-nested-annotation-used-by-members")
        val function = scope.functions().first()
        val sut =
            listOfNotNull(
                function.annotations.first(),
                function.parameters
                    .first()
                    .annotations
                    .first(),
                scope
                    .properties()
                    .first { it.name == "sampleProperty" }
                    .annotations
                    .first(),
                scope
                    .classes()
                    .first()
                    .primaryConstructor
                    ?.parameters
                    ?.first()
                    ?.annotations
                    ?.first(),
            )

        // then
        sut.size shouldBeEqualTo 4
        sut.forEach { it.shouldHaveFullyQualifiedName("com.fixturepackage.Outer.FixtureAnnotation") }
    }

    @Test
    fun `annotation-fully-qualified-name-of-nested-annotation-used-by-nested-class`() {
        // given
        val scope = getSnippetFile("annotation-fully-qualified-name-of-nested-annotation-used-by-nested-class")
        val sut =
            listOf(
                scope
                    .classes()
                    .first { it.name == "Nested" }
                    .annotations
                    .first(),
                scope
                    .functions()
                    .first()
                    .annotations
                    .first(),
            )

        // then
        sut.forEach { it.shouldHaveFullyQualifiedName("com.fixturepackage.Outer.FixtureAnnotation") }
    }

    /*
    Nested declarations of a class are not visible in the annotations of that class,
    so the class annotation refers to the top-level annotation, while the member annotation refers to the nested one.
     */
    @Test
    fun `annotation-fully-qualified-name-of-nested-annotation-shadowing-top-level-annotation`() {
        // given
        val scope = getSnippetFile("annotation-fully-qualified-name-of-nested-annotation-shadowing-top-level-annotation")
        val classAnnotation =
            scope
                .classes()
                .first { it.name == "Outer" }
                .annotations
                .first()
        val functionAnnotation =
            scope
                .functions()
                .first()
                .annotations
                .first()

        // then
        classAnnotation.shouldHaveFullyQualifiedName("com.fixturepackage.FixtureAnnotation")
        functionAnnotation.shouldHaveFullyQualifiedName("com.fixturepackage.Outer.FixtureAnnotation")
    }

    @Test
    fun `annotation-fully-qualified-name-of-nested-annotation-inherited-from-parent-class`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-of-nested-annotation-inherited-from-parent-class")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.shouldHaveFullyQualifiedName("com.fixturepackage.Parent.FixtureAnnotation")
    }

    @Test
    fun `annotation-fully-qualified-name-of-qualified-nested-annotation`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-of-qualified-nested-annotation")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.shouldHaveFullyQualifiedName("com.fixturepackage.Outer.FixtureAnnotation")
    }

    private fun KoAnnotationDeclaration.shouldHaveFullyQualifiedName(fullyQualifiedName: String) {
        assertSoftly(this) {
            this.fullyQualifiedName shouldBeEqualTo fullyQualifiedName
            (sourceDeclaration as? KoFullyQualifiedNameProvider)?.fullyQualifiedName shouldBeEqualTo fullyQualifiedName
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koannotation/snippet/forkofullyqualifiednameprovider/", fileName)
}
