package com.lemonappdev.konsist.core.architecture.validator.ascii

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.architecture.FileDependency
import com.lemonappdev.konsist.core.util.HyperlinkUtil

data class AsciiTreeNode(
    val string: String,
    val children: List<AsciiTreeNode> = emptyList(),
) {
    constructor(
        koFileDeclaration: KoFileDeclaration,
        children: List<AsciiTreeNode>,
    ) : this(
        "File ${HyperlinkUtil.toHyperlink(koFileDeclaration.path)}",
        children,
    )

    internal constructor(
        fileDependency: FileDependency,
        children: List<AsciiTreeNode>,
    ) : this(
        "${fileDependency.type.displayName} ${fileDependency.name} (${HyperlinkUtil.toHyperlink(fileDependency.location)})",
        children,
    )
}
