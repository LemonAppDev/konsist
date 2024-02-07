package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RestController

class SpringSnippets {
    fun `interfaces with 'Repository' annotation should have 'Repository' suffix`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withAnnotationOf(Repository::class)
            .assertTrue { it.hasNameEndingWith("Repository") }
    }

    fun `classes with 'RestController' annotation should have 'Controller' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(RestController::class)
            .assertTrue { it.hasNameEndingWith("Controller") }
    }

    fun `controllers never returns collection types`() {
        /*
        Avoid returning collection types directly. Structuring the response as
        an object that contains a collection field is preferred. This approach
        allows for future expansion (e.g., adding more properties like "totalPages")
        without disrupting the existing API contract, which would happen if a JSON
        array were returned directly.
         */
        Konsist
            .scopeFromPackage("story.controller..")
            .classes()
            .withAnnotationOf(RestController::class)
            .functions()
            .assertFalse { function ->
                function.hasReturnType { it.isKotlinCollectionType }
            }
    }

    fun `classes with 'RestController' annotation should reside in 'controller' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(RestController::class)
            .assertTrue { it.resideInPackage("..controller..") }
    }

    fun `classes with 'RestController' annotation should never return collection`() {
        Konsist
            .scopeFromPackage("story.controller..")
            .classes()
            .withAnnotationOf(RestController::class)
            .functions()
            .assertFalse { function ->
                function.hasReturnType { it.hasNameStartingWith("List") }
            }
    }
}
