// package com.lemonappdev.konsist.core.declaration.koparent
//
// import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
// import com.lemonappdev.konsist.api.ext.list.parents
// import org.amshove.kluent.assertSoftly
// import org.amshove.kluent.shouldBeEqualTo
// import org.junit.jupiter.api.Test
//
// class KoParentDeclarationForKoPathProviderTest {
//    @Test
//    fun `parent-from-file-of-class-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-class-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("koparent/snippet/forkopathprovider/parent-from-file-of-class-file-path.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-class-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-class-project-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo(
//                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
//                    "forkopathprovider/parent-from-file-of-class-project-file-path.kt",
//            )
//    }
//
//    @Test
//    fun `parent-from-file-of-class-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-class-reside-in-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent..parent-from-file-of-class-reside-in-file-path.kt", true) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-class-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-class-reside-in-project-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent..parent-from-file-of-class-reside-in-project-file-path.kt", false) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-interface-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-interface-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("koparent/snippet/forkopathprovider/parent-from-file-of-interface-file-path.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-interface-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-interface-project-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo(
//                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
//                    "forkopathprovider/parent-from-file-of-interface-project-file-path.kt",
//            )
//    }
//
//    @Test
//    fun `parent-from-file-of-interface-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-interface-reside-in-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent..parent-from-file-of-interface-reside-in-file-path.kt", true) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-interface-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-interface-reside-in-project-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent..parent-from-file-of-interface-reside-in-project-file-path.kt", false) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-object-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-object-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("koparent/snippet/forkopathprovider/parent-from-file-of-object-file-path.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-object-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-object-project-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo(
//                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
//                    "forkopathprovider/parent-from-file-of-object-project-file-path.kt",
//            )
//    }
//
//    @Test
//    fun `parent-from-file-of-object-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-object-reside-in-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent..parent-from-file-of-object-reside-in-file-path.kt", true) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-file-of-object-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-file-of-object-reside-in-project-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent..parent-from-file-of-object-reside-in-project-file-path.kt", false) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-class-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-class-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-class-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-class-project-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo("/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt")
//    }
//
//    @Test
//    fun `parent-from-import-of-class-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-class-reside-in-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..testdata..", true) shouldBeEqualTo true
//            resideInPath("..konsist/testdata..", true) shouldBeEqualTo true
//            resideInPath("..konsist..TestData.kt", true) shouldBeEqualTo true
//            resideInPath("konsist/testdata/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-class-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-class-reside-in-project-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..testdata..", false) shouldBeEqualTo true
//            resideInPath("..konsist/testdata..", false) shouldBeEqualTo true
//            resideInPath("..konsist..TestData.kt", false) shouldBeEqualTo true
//            resideInPath("konsist/testdata/", false) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-interface-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-interface-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-interface-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-interface-project-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo("/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt")
//    }
//
//    @Test
//    fun `parent-from-import-of-interface-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-interface-reside-in-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..testdata..", true) shouldBeEqualTo true
//            resideInPath("..konsist/testdata..", true) shouldBeEqualTo true
//            resideInPath("..konsist..TestData.kt", true) shouldBeEqualTo true
//            resideInPath("konsist/testdata/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-interface-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-interface-reside-in-project-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..testdata..", false) shouldBeEqualTo true
//            resideInPath("..konsist/testdata..", false) shouldBeEqualTo true
//            resideInPath("..konsist..TestData.kt", false) shouldBeEqualTo true
//            resideInPath("konsist/testdata/", false) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-object-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-object-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-object-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-object-project-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo("/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/testdata/TestData.kt")
//    }
//
//    @Test
//    fun `parent-from-import-of-object-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-object-reside-in-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..testdata..", true) shouldBeEqualTo true
//            resideInPath("..konsist/testdata..", true) shouldBeEqualTo true
//            resideInPath("..konsist..TestData.kt", true) shouldBeEqualTo true
//            resideInPath("konsist/testdata/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `parent-from-import-of-object-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("parent-from-import-of-object-reside-in-project-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..testdata..", false) shouldBeEqualTo true
//            resideInPath("..konsist/testdata..", false) shouldBeEqualTo true
//            resideInPath("..konsist..TestData.kt", false) shouldBeEqualTo true
//            resideInPath("konsist/testdata/", false) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `external-parent-of-class-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-class-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("koparent/snippet/forkopathprovider/external-parent-of-class-file-path.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `external-parent-of-class-project-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-class-project-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo(
//                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
//                    "forkopathprovider/external-parent-of-class-project-file-path.kt",
//            )
//    }
//
//    @Test
//    fun `external-parent-of-class-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-class-reside-in-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent..external-parent-of-class-reside-in-file-path.kt", true) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `external-parent-of-class-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-class-reside-in-project-file-path")
//            .classes()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent..external-parent-of-class-reside-in-project-file-path.kt", false) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `external-parent-of-interface-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-interface-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("koparent/snippet/forkopathprovider/external-parent-of-interface-file-path.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `external-parent-of-interface-project-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-interface-project-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo(
//                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
//                    "forkopathprovider/external-parent-of-interface-project-file-path.kt",
//            )
//    }
//
//    @Test
//    fun `external-parent-of-interface-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-interface-reside-in-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent..external-parent-of-interface-reside-in-file-path.kt", true) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `external-parent-of-interface-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-interface-reside-in-project-file-path")
//            .interfaces()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent..external-parent-of-interface-reside-in-project-file-path.kt", false) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `external-parent-of-object-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-object-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut.path) {
//            startsWith("//") shouldBeEqualTo false
//            endsWith("koparent/snippet/forkopathprovider/external-parent-of-object-file-path.kt") shouldBeEqualTo true
//        }
//    }
//
//    @Test
//    fun `external-parent-of-object-project-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-object-project-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        sut
//            .projectPath
//            .shouldBeEqualTo(
//                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
//                    "forkopathprovider/external-parent-of-object-project-file-path.kt",
//            )
//    }
//
//    @Test
//    fun `external-parent-of-object-reside-in-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-object-reside-in-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
//            resideInPath("..koparent..external-parent-of-object-reside-in-file-path.kt", true) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
//        }
//    }
//
//    @Test
//    fun `external-parent-of-object-reside-in-project-file-path`() {
//        // given
//        val sut = getSnippetFile("external-parent-of-object-reside-in-project-file-path")
//            .objects()
//            .parents
//            .first()
//
//        // then
//        assertSoftly(sut) {
//            resideInPath("..snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
//            resideInPath("..koparent..external-parent-of-object-reside-in-project-file-path.kt", false) shouldBeEqualTo true
//            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
//        }
//    }
//
//    private fun getSnippetFile(fileName: String) =
//        getSnippetKoScope("core/declaration/koparent/snippet/forkopathprovider/", fileName)
// }
