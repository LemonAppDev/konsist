package com.lemonappdev.konsist.core.architecture.validator.ascii

data class AsciiTreeNode(
    val string: String,
    val children: List<AsciiTreeNode> = emptyList(),
)
