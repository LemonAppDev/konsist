package com.mango.archunit

import com.mango.archunit.utils.PackageIdentifier.ANY_APPLICATION_ANY
import com.mango.archunit.utils.PackageIdentifier.ANY_DATA_ANY
import com.mango.archunit.utils.PackageIdentifier.ANY_DOMAIN_ANY
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO_APPLICATION_ANY
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO_COMMON_ANY
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO_DATA_ANY
import com.mango.archunit.utils.PackageIdentifier.COM_MANGO_DOMAIN_ANY
import com.mango.archunit.utils.ProjectClassesProvider
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.jupiter.api.Test

class LayeredArchitectureRulesTest {
    private val importedClasses = ProjectClassesProvider.allClasses

    @Test
    fun `layer dependencies are respected`() {
        val applicationLayer = "Application"
        val domainLayer = "Domain"
        val dataLayer = "Data"

        layeredArchitecture().consideringAllDependencies()
            .layer(applicationLayer).definedBy(ANY_APPLICATION_ANY)
            .layer(domainLayer).definedBy(ANY_DOMAIN_ANY)
            .layer(dataLayer).definedBy(ANY_DATA_ANY)
            .whereLayer(applicationLayer).mayNotBeAccessedByAnyLayer()
            .whereLayer(domainLayer).mayOnlyBeAccessedByLayers(applicationLayer, dataLayer)
            .whereLayer(dataLayer).mayOnlyBeAccessedByLayers(domainLayer)
            .check(importedClasses)
    }

    @Test
    fun `every class should reside in one of the specified packages`() {
        classes()
            .that()
            .resideOutsideOfPackages(COM_MANGO, "..archunit..")
            .should()
            .resideInAnyPackage(COM_MANGO_APPLICATION_ANY, COM_MANGO_DOMAIN_ANY, COM_MANGO_DATA_ANY, COM_MANGO_COMMON_ANY)
            .check(importedClasses)
    }
}
