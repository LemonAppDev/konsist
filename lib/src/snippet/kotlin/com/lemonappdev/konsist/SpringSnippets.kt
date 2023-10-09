package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
import com.lemonappdev.konsist.api.verify.assert
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RestController

class SpringSnippets {
    fun `interfaces with 'Repository' annotation should have 'Repository' suffix`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withAnnotationOf(Repository::class)
            .assert { it.hasNameEndingWith("Repository") }
    }

    fun `classes with 'RestController' annotation should have 'Controller' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(RestController::class)
            .assert { it.hasNameEndingWith("Controller") }
    }

    fun `classes with 'RestController' annotation should reside in 'controller' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(RestController::class)
            .assert { it.resideInPackage("..controller..") }
    }
}
