package com.lemonappdev.konsist.core.declaration.kocontextparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.testdata.FixtureAnnotation
import com.lemonappdev.konsist.testdata.FixtureClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoContextParameterDeclarationTest {
    @Test
    fun `function-has-context-parameter`() {
        // given
        val function =
            getSnippetFile("function-has-context-parameter")
                .functions()
                .first()

        val sut = function.contextParameters.first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "fixtureParameter"
            toString() shouldBeEqualTo "fixtureParameter"
            text shouldBeEqualTo "@FixtureAnnotation fixtureParameter: FixtureClass"
            type.name shouldBeEqualTo "FixtureClass"
            hasTypeOf(FixtureClass::class) shouldBeEqualTo true
            hasTypeOf(String::class) shouldBeEqualTo false
            representsType("FixtureClass") shouldBeEqualTo true
            representsType("OtherClass") shouldBeEqualTo false
            hasAnnotationOf(FixtureAnnotation::class) shouldBeEqualTo true
            containingDeclaration shouldBeEqualTo function
            containingFile.name shouldBeEqualTo "function-has-context-parameter"
            packagee?.name shouldBeEqualTo "com.samplepackage"
            resideInPackage("com.samplepackage") shouldBeEqualTo true
            location shouldBeEqualTo "$path:6:9"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kocontextparameter/snippet/forgeneral/", fileName)
}
