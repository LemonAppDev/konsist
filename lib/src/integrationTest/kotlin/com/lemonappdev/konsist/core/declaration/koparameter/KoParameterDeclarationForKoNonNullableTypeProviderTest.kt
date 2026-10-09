package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.FixtureType
import net.bytebuddy.matcher.ElementMatchers.hasType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoNonNullableTypeProviderTest {
    @Test
    fun `class-has-complex-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-complex-default-parameter-value")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.type?.name shouldBeEqualTo "FixtureType"
            it?.hasType { type -> type.name == "FixtureType" } shouldBeEqualTo true
            it?.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasTypeOf(FixtureType::class) shouldBeEqualTo true
            it?.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-complex-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-complex-default-parameter-value")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            type.name shouldBeEqualTo "FixtureType"
            hasType { type -> type.name == "FixtureType" } shouldBeEqualTo true
            hasType { type -> type.name == "Int" } shouldBeEqualTo false
            hasTypeOf(FixtureType::class) shouldBeEqualTo true
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-annotated-complex-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("class-has-annotated-complex-default-parameter-value")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.type?.name shouldBeEqualTo "FixtureType"
            it?.hasType { type -> type.name == "FixtureType" } shouldBeEqualTo true
            it?.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasTypeOf(FixtureType::class) shouldBeEqualTo true
            it?.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-annotated-complex-default-parameter-value`() {
        // given
        val sut =
            getSnippetFile("function-has-annotated-complex-default-parameter-value")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            type.name shouldBeEqualTo "FixtureType"
            hasType { type -> type.name == "FixtureType" } shouldBeEqualTo true
            hasType { type -> type.name == "Int" } shouldBeEqualTo false
            hasTypeOf(FixtureType::class) shouldBeEqualTo true
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-one-parameter-with-import-alias`() {
        // given
        val sut =
            getSnippetFile("class-has-one-parameter-with-import-alias")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.type?.name shouldBeEqualTo "ImportAlias"
            it?.hasType { type -> type.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasTypeOf(FixtureType::class) shouldBeEqualTo false
            it?.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-one-parameter-with-import-alias`() {
        // given
        val sut =
            getSnippetFile("function-has-one-parameter-with-import-alias")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            type.name shouldBeEqualTo "ImportAlias"
            hasType { type -> type.name == "ImportAlias" } shouldBeEqualTo true
            hasType { type -> type.name == "Int" } shouldBeEqualTo false
            hasTypeOf(FixtureType::class) shouldBeEqualTo false
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-one-parameter-with-annotated-import-alias`() {
        // given
        val sut =
            getSnippetFile("class-has-one-parameter-with-annotated-import-alias")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.type?.name shouldBeEqualTo "ImportAlias"
            it?.hasType { type -> type.name == "ImportAlias" } shouldBeEqualTo true
            it?.hasType { type -> type.name == "Int" } shouldBeEqualTo false
            it?.hasTypeOf(FixtureType::class) shouldBeEqualTo false
            it?.hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-one-parameter-with-annotated-import-alias`() {
        // given
        val sut =
            getSnippetFile("function-has-one-parameter-with-annotated-import-alias")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            type.name shouldBeEqualTo "ImportAlias"
            hasType { type -> type.name == "ImportAlias" } shouldBeEqualTo true
            hasType { type -> type.name == "Int" } shouldBeEqualTo false
            hasTypeOf(FixtureType::class) shouldBeEqualTo false
            hasTypeOf(Int::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkononnullabletypeprovider/", fileName)
}
