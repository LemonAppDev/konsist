package com.lemonappdev.konsist.core.architecture.validator.ascii

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

data class AsciiTreeNode(
    val string: String,
    val children: List<AsciiTreeNode> = emptyList(),
) {
    constructor(
        koFileDeclaration: KoFileDeclaration,
        children: List<AsciiTreeNode>,
    ) : this(
        "File ${koFileDeclaration.path}",
        children,
    )

    constructor(
        koImportDeclaration: KoImportDeclaration,
        children: List<AsciiTreeNode>,
    ) : this(
        "Import ${koImportDeclaration.name}",
        children,
    )
}
