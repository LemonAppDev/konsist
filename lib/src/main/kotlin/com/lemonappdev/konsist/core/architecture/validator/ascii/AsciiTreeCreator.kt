package com.lemonappdev.konsist.core.architecture.validator.ascii

class AsciiTreeCreator {
    operator fun invoke(node: AsciiTreeNode, indent: String = ""): String = buildString {
        append(node.string)

        if (node.children.isNotEmpty()) {
            appendLine()

            node.children.forEachIndexed { index, child ->
                val isLast = index == node.children.lastIndex
                val childIndent = indent + if (isLast) "    " else "│   "
                val marker = if (isLast) "└── " else "├── "

                append(indent + marker)
                append(invoke(child, childIndent).trimEnd())

                if (index < node.children.lastIndex) {
                    appendLine()
                }
            }
        }
    }
}
