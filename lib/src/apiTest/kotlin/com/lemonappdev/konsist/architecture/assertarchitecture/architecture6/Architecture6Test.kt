package com.lemonappdev.konsist.architecture.assertarchitecture.architecture6

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture6Test {
    private val layer =
        Layer(
            "EmptyLayer",
            "com.lemonappdev.konsist.assertarchitecture.architecture6.project.emptylayer..",
        )

    private val scope =
        Konsist.scopeFromPackage(
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture6.project",
        )

    // region fails when layer contains no files
    @Test
    fun `fails when layer contains no files (lambda scope)`() {
        // when
        val func = {
            scope.assertArchitecture {
                layer.dependsOnNothing()
            }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Layer EmptyLayer doesn't contain any files."
    }

    @Test
    fun `fails when layer contains no files (lambda files)`() {
        // when
        val func = {
            scope
                .files
                .assertArchitecture {
                    layer.dependsOnNothing()
                }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Layer EmptyLayer doesn't contain any files."
    }

    @Test
    fun `fails when layer contains no files (parameter scope)`() {
        // when
        val func = {
            val layerDependencies =
                architecture {
                    scope.assertArchitecture {
                        layer.dependsOnNothing()
                    }
                }

            scope.assertArchitecture(layerDependencies)
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Layer EmptyLayer doesn't contain any files."
    }

    @Test
    fun `fails when layer contains no files (parameter files)`() {
        // when
        val func = {
            val layerDependencies =
                architecture {
                    scope.assertArchitecture {
                        layer.dependsOnNothing()
                    }
                }

            scope
                .files
                .assertArchitecture(layerDependencies)
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Layer EmptyLayer doesn't contain any files."
    }

    // endregion

    // region fails when architecture contains no layers
    @Test
    fun `fails when architecture contains no layers (lambda scope)`() {
        // when
        val func = {
            scope.assertArchitecture { }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    @Test
    fun `fails when architecture contains no layers (lambda files)`() {
        // when
        val func = {
            scope
                .files
                .assertArchitecture { }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    @Test
    fun `fails when architecture contains no layers (parameter scope)`() {
        // when
        val layerDependencies = architecture { }

        val func = {
            scope.assertArchitecture(layerDependencies)
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    @Test
    fun `fails when architecture contains no layers (parameter files)`() {
        // when
        val layerDependencies = architecture { }

        val func = {
            scope
                .files
                .assertArchitecture(layerDependencies)
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    // endregion

    // region fails when architecture contains no dependencies

    @Test
    fun `fails when architecture contains no dependencies (lambda scope)`() {
        // when
        val func = {
            scope.assertArchitecture {
                layer
            }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    @Test
    fun `fails when architecture contains no dependencies (lambda files)`() {
        // when
        val func = {
            scope
                .files
                .assertArchitecture {
                    layer
                }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    @Test
    fun `fails when architecture contains no dependencies (parameter scope)`() {
        // when
        val layerDependencies =
            architecture {
                layer
            }

        val func = {
            scope.assertArchitecture(layerDependencies)
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    @Test
    fun `fails when architecture contains no dependencies (parameter files)`() {
        // when
        val layerDependencies =
            architecture {
                layer
            }

        val func = {
            scope
                .files
                .assertArchitecture(layerDependencies)
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    // endregion
}
