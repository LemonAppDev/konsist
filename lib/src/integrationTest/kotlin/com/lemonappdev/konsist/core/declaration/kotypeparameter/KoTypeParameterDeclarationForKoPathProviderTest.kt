package com.lemonappdev.konsist.core.declaration.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoPathProviderTest {
    @Test
    fun `function-type-parameter-file-path`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-path")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeparameter/snippet/forkopathprovider/function-type-parameter-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `function-type-parameter-project-file-path`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-path")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeparameter/snippet/" +
                    "forkopathprovider/function-type-parameter-path.kt",
            )
    }

    @Test
    fun `function-type-parameter-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-path")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..function-type-parameter-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-type-parameter-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-path")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..function-type-parameter-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type-parameter-file-path`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-path")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeparameter/snippet/forkopathprovider/class-type-parameter-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `class-type-parameter-project-file-path`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-path")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeparameter/snippet/" +
                    "forkopathprovider/class-type-parameter-path.kt",
            )
    }

    @Test
    fun `class-type-parameter-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-path")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..class-type-parameter-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type-parameter-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-path")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..class-type-parameter-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-parameter-file-path`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-path")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeparameter/snippet/forkopathprovider/interface-type-parameter-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-type-parameter-project-file-path`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-path")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeparameter/snippet/" +
                    "forkopathprovider/interface-type-parameter-path.kt",
            )
    }

    @Test
    fun `interface-type-parameter-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-path")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..interface-type-parameter-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-parameter-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-path")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..interface-type-parameter-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-type-parameter-file-path`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-path")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeparameter/snippet/forkopathprovider/property-type-parameter-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `property-type-parameter-project-file-path`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-path")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeparameter/snippet/" +
                    "forkopathprovider/property-type-parameter-path.kt",
            )
    }

    @Test
    fun `property-type-parameter-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-path")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..property-type-parameter-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-type-parameter-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-path")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..property-type-parameter-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-type-parameter-file-path`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-path")
                .typeAliases
                .first()
                .type
                .typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypeparameter/snippet/forkopathprovider/typealias-type-parameter-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-type-parameter-project-file-path`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-path")
                .typeAliases
                .first()
                .type
                .typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypeparameter/snippet/" +
                    "forkopathprovider/typealias-type-parameter-path.kt",
            )
    }

    @Test
    fun `typealias-type-parameter-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-path")
                .typeAliases
                .first()
                .type
                .typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..typealias-type-parameter-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-type-parameter-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-path")
                .typeAliases
                .first()
                .type
                .typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypeparameter..typealias-type-parameter-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("kotypeparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeparameter/snippet/forkopathprovider/",
            fileName,
        )
}
