package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withAnnotationOf
import com.lemonappdev.konsist.api.ext.sequence.withNameEndingWith
import com.lemonappdev.konsist.core.verify.assert
import org.springframework.stereotype.Repository

class ArchitectureSnippets {
    fun `classes with 'Repository' annotation should reside in 'data' package`() {
        Konsist.scopeFromProject()
            .classes()
            .withAnnotationOf<Repository>()
            .assert { it.resideInPackage("..data..") }
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
            .assert { it.declarations().toList().size == 1 && it.containsFunction("invoke") }
    }
}
