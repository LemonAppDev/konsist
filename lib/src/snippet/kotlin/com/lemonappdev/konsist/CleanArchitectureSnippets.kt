package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.withAllAnnotationsOf
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.core.verify.assert
import org.springframework.stereotype.Repository

class CleanArchitectureSnippets {
    fun `clean architecture layers have correct dependencies`() {
        Konsist
            .scopeFromProject()
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
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assert { it.resideInPackage("..domain..usecase..") }
    }

    fun `classes with 'UseCase' suffix should have single method named 'invoke'`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assert { it.numDeclarations() == 1 && it.containsFunction("invoke") && it.isPublicOrDefault }
    }

    fun `interfaces with 'Repository' annotation should reside in 'data' package`() {
        Konsist.scopeFromProject()
            .interfaces()
//            .withAnnotationOf<Repository>()
            .withAllAnnotationsOf(Repository::class)
            .assert { it.resideInPackage("..data..") }
    }
}
