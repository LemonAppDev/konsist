package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.withAllAnnotationsOf
import com.lemonappdev.konsist.api.ext.list.withAllParents
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import org.springframework.stereotype.Repository

class CleanArchitectureSnippets {
    fun `clean architecture layers have correct dependencies`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                // Define layers
                val domain = Layer("Domain", "com.myapp.domain..")
                val presentation = Layer("Presentation", "com.myapp.presentation..")
                val data = Layer("Data", "com.myapp.data..")

                // Define architecture assertions
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }

    fun `classes with 'UseCase' suffix should reside in 'domain' and 'usecase' packages`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assert { it.resideInPackage("..domain..usecase..") }
    }

    fun `classes with 'UseCase' suffix should have single public method named 'invoke'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assert {
                val hasSingleInvokeOperatorMethod = it.containsFunction { function ->
                    function.name == "invoke" && function.hasPublicOrDefaultModifier && function.hasOperatorModifier
                }

                hasSingleInvokeOperatorMethod && it.numPublicOrDefaultDeclarations() == 1
            }
    }

    fun `interfaces with 'Repository' annotation should reside in 'data' package`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withAllAnnotationsOf(Repository::class)
            .assert { it.resideInPackage("..data..") }
    }

    fun `every UseCase class has test`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withAllParents("UseCase")
            .assert { it.hasTestClass() }
    }
}
