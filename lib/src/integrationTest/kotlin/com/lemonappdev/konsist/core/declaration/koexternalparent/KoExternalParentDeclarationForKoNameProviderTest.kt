package com.lemonappdev.konsist.core.declaration.koexternalparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.externalParents
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExternalParentDeclarationForKoNameProviderTest {
    @Test
    fun `class-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-class")
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalClass"
            hasName("FixtureExternalClass") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalclass", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalclass", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalGenericClass<Int>"
            hasName("FixtureExternalGenericClass<Int>") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalgenericclass<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalgenericclass<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalClassWithParameter"
            hasName("FixtureExternalClassWithParameter") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalclasswithparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalclasswithparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-with-parametrized-and-generic-external-parent-class")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalGenericClassWithParameter<Int>"
            hasName("FixtureExternalGenericClassWithParameter<Int>") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalgenericclasswithparameter<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalgenericclasswithparameter<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-interface")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalInterface"
            hasName("FixtureExternalInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("fixtureexternalinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("class-with-generic-external-parent-interface")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalGenericInterface<Int>"
            hasName("FixtureExternalGenericInterface<Int>") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("fixtureexternalgenericinterface<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalgenericinterface<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-external-parent-by-delegation`() {
        // given
        val sut =
            getSnippetFile("class-with-external-parent-by-delegation")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalInterface"
            hasName("FixtureExternalInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("fixtureexternalinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-with-multiline-external-parent`() {
        // given
        val sut =
            getSnippetFile("class-with-multiline-external-parent")
                .classes()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalClassWithParameter"
            hasName("FixtureExternalClassWithParameter") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalclasswithparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalclasswithparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-external-parent-interface")
                .interfaces()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalInterface"
            hasName("FixtureExternalInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("fixtureexternalinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("interface-with-generic-external-parent-interface")
                .interfaces()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalGenericInterface<Int>"
            hasName("FixtureExternalGenericInterface<Int>") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("fixtureexternalgenericinterface<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalgenericinterface<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalClass"
            hasName("FixtureExternalClass") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalclass", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalclass", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalGenericClass<Int>"
            hasName("FixtureExternalGenericClass<Int>") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalgenericclass<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalgenericclass<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-parametrized-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalClassWithParameter"
            hasName("FixtureExternalClassWithParameter") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalclasswithparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalclasswithparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-parametrized-and-generic-external-parent-class`() {
        // given
        val sut =
            getSnippetFile("object-with-parametrized-and-generic-external-parent-class")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalGenericClassWithParameter<Int>"
            hasName("FixtureExternalGenericClassWithParameter<Int>") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalgenericclasswithparameter<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalgenericclasswithparameter<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-external-parent-interface")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalInterface"
            hasName("FixtureExternalInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("fixtureexternalinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-generic-external-parent-interface`() {
        // given
        val sut =
            getSnippetFile("object-with-generic-external-parent-interface")
                .objects()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalGenericInterface<Int>"
            hasName("FixtureExternalGenericInterface<Int>") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("fixtureexternalgenericinterface<int>", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalgenericinterface<int>", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object-with-multiline-external-parent`() {
        // given
        val sut =
            getSnippetFile("object-with-multiline-external-parent")
                .objects()
                .first()
                .externalParents()
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "FixtureExternalClassWithParameter"
            hasName("FixtureExternalClassWithParameter") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("fixtureexternalclasswithparameter", ignoreCase = false) shouldBeEqualTo false
            hasName("fixtureexternalclasswithparameter", ignoreCase = true) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koexternalparent/snippet/forkonameprovider/", fileName)
}
