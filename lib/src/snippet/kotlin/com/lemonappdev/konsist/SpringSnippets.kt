package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withAllAnnotationsOf
import com.lemonappdev.konsist.api.ext.sequence.withAnnotationOf
import com.lemonappdev.konsist.core.verify.assert
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RestController
import java.lang.ModuleLayer.Controller

class SpringSnippets {
    fun `interfaces with 'Repository' annotation should have 'Repository' suffix`() {
        Konsist
            .scopeFromProject()
            .interfaces()
//            .withAnnotationOf<Repository>()
            .withAllAnnotationsOf(Repository::class)
            .assert { it.hasNameEndingWith("Repository") }
    }

    fun `classes with 'RestController' annotation should have 'Controller' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
//            .withAnnotationOf<RestController>()
            .withAllAnnotationsOf(RestController::class)
            .assert { it.hasNameEndingWith("Controller") }
    }

    fun `classes with 'RestController' annotation should reside in 'controller' package`() {
        Konsist
            .scopeFromProject()
            .classes()
//            .withAnnotationOf<RestController>()
            .withAllAnnotationsOf(RestController::class)
            .assert { it.resideInPackage("..controller..") }
    }
}
