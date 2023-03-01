package com.mango.archunit

import com.mango.archunit.utils.PackageIdentifier.ANY_BUSINESS_ANY
import com.mango.archunit.utils.PackageIdentifier.ANY_PERSISTENCE_ANY
import com.mango.archunit.utils.PackageIdentifier.ANY_PRESENTATION_ANY
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO_BUSINESS
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO_COMMON
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO_PERSISTENCE
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO_PRESENTATION
import com.mango.archunit.utils.ProjectClassesProvider
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.jupiter.api.Test

class LayeredArchitectureRulesTest {
    private val importedClasses = ProjectClassesProvider.allClasses

    @Test
    fun `layer dependencies are respected`() {
        val presentationLayer = "Presentation"
        val businessLayer = "Business"
        val persistenceLayer = "Persistence"

        layeredArchitecture().consideringAllDependencies()
            .layer(presentationLayer).definedBy(ANY_PRESENTATION_ANY)
            .layer(businessLayer).definedBy(ANY_BUSINESS_ANY)
            .layer(persistenceLayer).definedBy(ANY_PERSISTENCE_ANY)
            .whereLayer(presentationLayer).mayNotBeAccessedByAnyLayer()
            .whereLayer(businessLayer).mayOnlyBeAccessedByLayers(presentationLayer, persistenceLayer)
            .whereLayer(persistenceLayer).mayOnlyBeAccessedByLayers(businessLayer)
            .check(importedClasses)
    }

    @Test
    fun `every class should reside in one of the specified packages`() {
        classes().should().resideInAnyPackage(
            COM_MANGO_PRESENTATION,
            COM_MANGO_BUSINESS,
            COM_MANGO_PERSISTENCE,
            COM_MANGO_COMMON,
        )
    }
}
