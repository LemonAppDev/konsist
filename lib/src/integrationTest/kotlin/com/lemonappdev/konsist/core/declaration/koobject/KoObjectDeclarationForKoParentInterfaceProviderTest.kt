package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import com.lemonappdev.konsist.testdata.SampleParentInterface2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoParentInterfaceProviderTest {
    @Test
    fun `object-has-no-parent-interface`() {
        // given
        val sut = getSnippetFile("object-has-no-parent-interface")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces shouldBeEqualTo emptyList()
            numParentInterfaces shouldBeEqualTo 0
            countParentInterfaces { it.name == "SampleParentInterface" } shouldBeEqualTo 0
            hasParentInterfaces() shouldBeEqualTo false
            hasParentInterfaceWithName("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo false
            hasParentInterface { it.name == "SampleParentInterface" } shouldBeEqualTo false
            hasAllParentInterfaces { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasParentInterfaceOf(SampleParentInterface1::class) shouldBeEqualTo false
            hasAllParentInterfacesOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-only-parent-interfaces`() {
        // given
        val sut = getSnippetFile("object-has-only-parent-interfaces")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.map { it.name } shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            numParentInterfaces shouldBeEqualTo 2
            countParentInterfaces { it.name == "SampleParentInterface1" } shouldBeEqualTo 1
            countParentInterfaces { it.hasNameStartingWith("SampleParentInterface") } shouldBeEqualTo 2
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaceWithName("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaceWithName("OtherInterface") shouldBeEqualTo false
            hasParentInterfaceWithName("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaceWithName("SampleParentInterface1", "OtherInterface") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
            hasParentInterface { it.name == "SampleParentInterface1" } shouldBeEqualTo true
            hasParentInterface { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllParentInterfaces { it.name == "SampleParentInterface1" } shouldBeEqualTo false
            hasAllParentInterfaces { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParentInterfaces { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentInterfaceOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasParentInterfaceOf(SampleParentInterface1::class, SampleParentClass::class) shouldBeEqualTo true
            hasAllParentInterfacesOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasAllParentInterfacesOf(SampleParentInterface1::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllParentInterfacesOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-parent-class-interfaces-and-external-parent`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-interfaces-and-external-parent")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.map { it.name } shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            numParentInterfaces shouldBeEqualTo 2
            countParentInterfaces { it.name == "SampleParentInterface1" } shouldBeEqualTo 1
            countParentInterfaces { it.hasNameStartingWith("SampleParentInterface") } shouldBeEqualTo 2
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaceWithName("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaceWithName("OtherInterface") shouldBeEqualTo false
            hasParentInterfaceWithName("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaceWithName("SampleParentInterface1", "OtherInterface") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("OtherInterface") shouldBeEqualTo false
            hasParentInterfacesWithAllNames("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfacesWithAllNames("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
            hasParentInterface { it.name == "SampleParentInterface1" } shouldBeEqualTo true
            hasParentInterface { it.name == "OtherInterface" } shouldBeEqualTo false
            hasAllParentInterfaces { it.name == "SampleParentInterface1" } shouldBeEqualTo false
            hasAllParentInterfaces { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParentInterfaces { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentInterfaceOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasParentInterfaceOf(SampleParentInterface1::class, SampleParentClass::class) shouldBeEqualTo true
            hasAllParentInterfacesOf(SampleParentInterface1::class) shouldBeEqualTo true
            hasAllParentInterfacesOf(SampleParentInterface1::class, SampleParentClass::class) shouldBeEqualTo false
            hasAllParentInterfacesOf(SampleParentInterface1::class, SampleParentInterface2::class) shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoparentinterfaceprovider/", fileName)
}
