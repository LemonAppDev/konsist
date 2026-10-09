package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoNameProviderTest {
    @Test
    fun `parameter-in-constructor-name`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-name")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "fixtureParameter"
            it?.hasName("fixtureParameter") shouldBeEqualTo true
            it?.hasName("otherParameter") shouldBeEqualTo false
            it?.hasName("FIXTUREPARAMETER", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("FIXTUREPARAMETER", ignoreCase = true) shouldBeEqualTo true
            it?.hasNameStartingWith("fixture") shouldBeEqualTo true
            it?.hasNameStartingWith("Other") shouldBeEqualTo false
            it?.hasNameStartingWith("FIXTURE", ignoreCase = false) shouldBeEqualTo false
            it?.hasNameStartingWith("FIXTURE", ignoreCase = true) shouldBeEqualTo true
            it?.hasNameEndingWith("meter") shouldBeEqualTo true
            it?.hasNameEndingWith("other") shouldBeEqualTo false
            it?.hasNameEndingWith("METER", ignoreCase = false) shouldBeEqualTo false
            it?.hasNameEndingWith("METER", ignoreCase = true) shouldBeEqualTo true
            it?.hasNameContaining("rePar") shouldBeEqualTo true
            it?.hasNameContaining("other") shouldBeEqualTo false
            it?.hasNameContaining("repar", ignoreCase = false) shouldBeEqualTo false
            it?.hasNameContaining("repar", ignoreCase = true) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-in-function-invocation-name`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-name")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "fixtureParameter"
            hasName("fixtureParameter") shouldBeEqualTo true
            hasName("otherParameter") shouldBeEqualTo false
            hasName("FIXTUREPARAMETER", ignoreCase = false) shouldBeEqualTo false
            hasName("FIXTUREPARAMETER", ignoreCase = true) shouldBeEqualTo true
            hasNameStartingWith("fixture") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameStartingWith("FIXTURE", ignoreCase = false) shouldBeEqualTo false
            hasNameStartingWith("FIXTURE", ignoreCase = true) shouldBeEqualTo true
            hasNameEndingWith("meter") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameEndingWith("METER", ignoreCase = false) shouldBeEqualTo false
            hasNameEndingWith("METER", ignoreCase = true) shouldBeEqualTo true
            hasNameContaining("rePar") shouldBeEqualTo true
            hasNameContaining("other") shouldBeEqualTo false
            hasNameContaining("repar", ignoreCase = false) shouldBeEqualTo false
            hasNameContaining("repar", ignoreCase = true) shouldBeEqualTo true
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/forkonameprovider/", fileName)
}
