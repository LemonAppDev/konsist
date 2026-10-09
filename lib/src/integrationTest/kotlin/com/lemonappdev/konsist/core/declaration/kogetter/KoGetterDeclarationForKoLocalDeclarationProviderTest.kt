package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoLocalDeclarationProviderTest {
    @Test
    fun `getter-contains-no-local-declarations`() {
        // given
        val sut =
            getSnippetFile("getter-contains-no-local-declarations")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.localDeclarations shouldBeEqualTo emptyList()
            it?.numLocalDeclarations shouldBeEqualTo 0
            it?.countLocalDeclarations { decl -> (decl as KoNameProvider).name == "fixtureLocalProperty" } shouldBeEqualTo 0
            it?.hasLocalDeclarations() shouldBeEqualTo false
            it?.hasLocalDeclaration { decl -> (decl as KoNameProvider).name == "FixtureLocalDeclaration" } shouldBeEqualTo false
            it?.hasAllLocalDeclarations { decl -> (decl as KoNameProvider).name == "FixtureLocalDeclaration" } shouldBeEqualTo true
        }
    }

    @Test
    fun `getter-contains-local-declarations`() {
        // given
        val sut =
            getSnippetFile("getter-contains-local-declarations")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.numLocalDeclarations shouldBeEqualTo 3
            it?.countLocalDeclarations { decl -> (decl as KoNameProvider).hasNameStartingWith("fixtureLocal") } shouldBeEqualTo 2
            it?.hasLocalDeclarations() shouldBeEqualTo true
            it?.hasLocalDeclaration { decl -> (decl as KoNameProvider).name == "fixtureLocalProperty" } shouldBeEqualTo true
            it?.hasLocalDeclaration { decl -> (decl as KoNameProvider).name == "otherLocalProperty" } shouldBeEqualTo false
            it?.hasAllLocalDeclarations { decl -> (decl as KoNameProvider).hasNameContaining("Local") } shouldBeEqualTo true
            it?.hasAllLocalDeclarations { decl -> (decl as KoNameProvider).hasNameStartingWith("fixture") } shouldBeEqualTo false
            it
                ?.localDeclarations
                ?.filterIsInstance<KoNameProvider>()
                ?.map { decl -> decl.name }
                .shouldBeEqualTo(
                    listOf(
                        "fixtureLocalProperty",
                        "fixtureLocalFunction",
                        "FixtureLocalClass",
                    ),
                )
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kogetter/snippet/forkolocaldeclarationprovider/", fileName)
}
