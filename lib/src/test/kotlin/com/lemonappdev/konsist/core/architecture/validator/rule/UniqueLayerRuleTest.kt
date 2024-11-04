package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeCreator
import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeNode
import com.lemonappdev.konsist.core.architecture.validator.ascii.AsciiTreeNodeFactory
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class UniqueLayerRuleTest {
    private val asciiTreeCreator: AsciiTreeCreator = mockk()
    private val asciiTreeNodeFactory: AsciiTreeNodeFactory = mockk()

    private val sut =
        UniqueLayerRule(
            asciiTreeCreator = asciiTreeCreator,
            asciiTreeNodeFactory = asciiTreeNodeFactory,
        )

    @Test
    fun `validate passes for empty dependencies`() {
        // Given
        val dependencies = emptySet<LayerDependency>()

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate passes for unique layers`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 2", "package2..")
        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate passes for single layer with DEPEND_ON_NOTHING`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate throws when layers have duplicate names`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 1", "package2..")
        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
            )

        val violationNode = mockk<AsciiTreeNode>()
        val rootNode = mockk<AsciiTreeNode>()

        every {
            asciiTreeNodeFactory.create("Layer name must be unique. Duplicated name: 'layer 1'.")
        } returns violationNode

        every {
            asciiTreeNodeFactory.create("Invalid layers configuration:", listOf(violationNode))
        } returns rootNode

        every {
            asciiTreeCreator.invoke(rootNode)
        } returns "Invalid layers configuration:\nLayer name must be unique. Duplicated name: 'layer 1'."

        // When
        val func = {
            sut.validate(dependencies)
        }

        // Then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Invalid layers configuration:\nLayer name must be unique. Duplicated name: 'layer 1'."
    }

    @Test
    fun `validate throws when layers have duplicate packages`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 2", "package1..")
        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
            )

        val violationNode = mockk<AsciiTreeNode>()
        val rootNode = mockk<AsciiTreeNode>()

        every {
            asciiTreeNodeFactory.create("Layer rootPackage must be unique. Duplicated rootPackage: 'package1..'.")
        } returns violationNode

        every {
            asciiTreeNodeFactory.create("Invalid layers configuration:", listOf(violationNode))
        } returns rootNode

        every {
            asciiTreeCreator.invoke(rootNode)
        } returns "Invalid layers configuration:\nLayer rootPackage must be unique. Duplicated rootPackage: 'package1..'."

        // When
        val func = {
            sut.validate(dependencies)
        }

        // Then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Invalid layers configuration:\nLayer rootPackage must be unique. Duplicated rootPackage: 'package1..'."
    }

    @Test
    fun `validate throws when layers have both duplicate names and packages`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 1", "package1..")
        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
            )

        val nameViolation = mockk<AsciiTreeNode>()
        val packageViolation = mockk<AsciiTreeNode>()
        val rootNode = mockk<AsciiTreeNode>()

        every {
            asciiTreeNodeFactory.create("Layer name must be unique. Duplicated name: 'layer 1'.")
        } returns nameViolation

        every {
            asciiTreeNodeFactory.create("Layer rootPackage must be unique. Duplicated rootPackage: 'package1..'.")
        } returns packageViolation

        every {
            asciiTreeNodeFactory.create(
                "Invalid layers configuration:",
                listOf(nameViolation, packageViolation),
            )
        } returns rootNode

        every {
            asciiTreeCreator.invoke(rootNode)
        } returns
            """
            Invalid layers configuration:
            Layer name must be unique. Duplicated name: 'layer 1'.
            Layer rootPackage must be unique. Duplicated rootPackage: 'package1..'.
            """.trimIndent()

        // When
        val func = {
            sut.validate(dependencies)
        }
        // Then
        func shouldThrow KoPreconditionFailedException::class withMessage
            """
            Invalid layers configuration:
            Layer name must be unique. Duplicated name: 'layer 1'.
            Layer rootPackage must be unique. Duplicated rootPackage: 'package1..'.
            """.trimIndent()
    }

    @Test
    fun `validate passes for single layer with multiple dependency types`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 2", "package2..")
        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer1,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate passes for multiple dependencies for same layer`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 2", "package2..")
        val layer3 = Layer("layer 3", "package3..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer3,
                ),
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer3,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate passes for self-referential dependencies`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer1,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate passes for complex dependency graph`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 2", "package2..")
        val layer3 = Layer("layer 3", "package3..")
        val layer4 = Layer("layer 4", "package4..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer3,
                ),
                LayerDependency(
                    layer1 = layer3,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer4,
                ),
                LayerDependency(
                    layer1 = layer4,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer1,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate passes for mixed dependency types`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 2", "package2..")
        val layer3 = Layer("layer 3", "package3..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER,
                    layer2 = layer3,
                ),
                LayerDependency(
                    layer1 = layer3,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate passes for cyclic dependencies`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 2", "package2..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer2,
                ),
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                    layer2 = layer1,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }

    @Test
    fun `validate passes for multiple layers with DEPEND_ON_NOTHING`() {
        // Given
        val layer1 = Layer("layer 1", "package1..")
        val layer2 = Layer("layer 2", "package2..")
        val layer3 = Layer("layer 3", "package3..")

        val dependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
                LayerDependency(
                    layer1 = layer2,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
                LayerDependency(
                    layer1 = layer3,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
            )

        // When
        sut.validate(dependencies)

        // Then
        verify(exactly = 0) { asciiTreeNodeFactory.create(any()) }
        verify(exactly = 0) { asciiTreeCreator.invoke(any()) }
    }
}
