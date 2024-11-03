package com.lemonappdev.konsist.core.architecture.validator.ascii

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AsciiTreeCreatorTest {

    private val sut = AsciiTreeCreator()

    @Test
    fun `creates simple tree with single node`() {
        // Given
        val node = AsciiTreeNode(
            string = "Root",
        )

        // When
        val result = sut(node)

        // Then
        result shouldBeEqualTo
            """
            Root
            """.trimIndent()
    }

    @Test
    fun `creates tree with multiple children`() {
        // Given
        val node = AsciiTreeNode(
            string = "Root",
            children = listOf(
                AsciiTreeNode("Child 1"),
                AsciiTreeNode("Child 2"),
                AsciiTreeNode("Child 3"),
            ),
        )

        // When
        val result = sut(node)

        // Then
        result shouldBeEqualTo
            """
            Root
            ├── Child 1
            ├── Child 2
            └── Child 3
            """.trimIndent()
    }

    @Test
    fun `creates nested tree`() {
        // Given
        val node = AsciiTreeNode(
            string = "Root",
            children = listOf(
                AsciiTreeNode(
                    string = "Child 1",
                    children = listOf(
                        AsciiTreeNode("Subchild 1.1"),
                        AsciiTreeNode("Subchild 1.2"),
                    ),
                ),
                AsciiTreeNode(
                    string = "Child 2",
                    children = listOf(
                        AsciiTreeNode("Subchild 2.1"),
                    ),
                ),
            ),
        )

        // When
        val result = sut(node)

        // Then
        result shouldBeEqualTo
            """
            Root
            ├── Child 1
            │   ├── Subchild 1.1
            │   └── Subchild 1.2
            └── Child 2
                └── Subchild 2.1
            """.trimIndent()
    }

    @Test
    fun `creates empty tree`() {
        // Given
        val node = AsciiTreeNode(
            string = "Root",
            children = emptyList(),
        )

        // When
        val result = sut(node)

        // Then
        result shouldBeEqualTo "Root"
    }
}

