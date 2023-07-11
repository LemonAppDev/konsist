package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withAnnotationOf
import com.lemonappdev.konsist.core.verify.assert
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RestController

class SpringSnippets {
    fun `declarations with 'Repository' annotation should have 'Repository' suffix`() {
        Konsist
            .scopeFromProject()
            .declarations()
            .withAnnotationOf<Repository>()
            .assert { it.hasNameEndingWith("Repository") }
    }

    fun `classes with 'RestController' annotation should have 'Controller' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf<RestController>()
            .assert { it.hasNameEndingWith("Controller") }
    }

    fun `classes with 'RestController' annotation should reside in 'controller' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf<RestController>()
            .assert { it.resideInPackage("..controller..") }
    }
}
