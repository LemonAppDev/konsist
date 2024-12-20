package com.lemonappdev.konsist.core.architecture.validator.ascii

internal class AsciiTreeNodeFactory {
    fun create(
        string: String,
        children: List<AsciiTreeNode> = emptyList(),
    ): AsciiTreeNode = AsciiTreeNode(string, children)
}
