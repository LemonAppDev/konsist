package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoNameProviderTest {
    @Test
    fun `variable-name`() {
        // given
        val sut = getSnippetFile("variable-name")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "sampleProperty"
            hasNameStartingWith("sample") shouldBeEqualTo true
            hasNameStartingWith("other") shouldBeEqualTo false
            hasNameEndingWith("erty") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameContaining("lePro") shouldBeEqualTo true
            hasNameContaining("lepro") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkonameprovider/", fileName)
}
