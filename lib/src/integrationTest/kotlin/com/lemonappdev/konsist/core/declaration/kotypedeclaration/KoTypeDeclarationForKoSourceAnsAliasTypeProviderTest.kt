package com.lemonappdev.konsist.core.declaration.kotypedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForKoSourceAnsAliasTypeProviderTest {
    @Test
    fun `simple-type`() {
        // given
        val sut = getSnippetFile("simple-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo null
            it?.isAlias() shouldBeEqualTo false
        }
    }

    @Test
    fun `simple-nullable-type`() {
        // given
        val sut = getSnippetFile("simple-nullable-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo null
            it?.isAlias() shouldBeEqualTo false
        }
    }

    @Test
    fun `import-alias`() {
        // given
        val sut = getSnippetFile("import-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo "ImportAlias"
            it?.isAlias() shouldBeEqualTo true
        }
    }

    @Test
    fun `nullable-import-alias`() {
        // given
        val sut = getSnippetFile("nullable-import-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.aliasType shouldBeEqualTo "ImportAlias"
            it?.isAlias() shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forkosourceandaliastypeprovider/", fileName)
}
