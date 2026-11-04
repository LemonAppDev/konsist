package com.lemonappdev.konsist.core.model

import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Finds children of the declaration without checking parents of all [declarations] again for every declaration.
 *
 * Declarations are indexed by the source declarations of their direct parents. Indirect children are the children
 * of the children (and so on).
 */
internal class ChildrenIndex(
    declarations: List<KoChildDeclaration>,
) {
    // Children are returned in the order of the declarations
    private val positions = mutableMapOf<KoChildDeclaration, Int>()

    // Keys are source declarations of the parents (`null` when the source declaration is unknown)
    private val directChildren = mutableMapOf<Any?, MutableList<KoChildDeclaration>>()

    init {
        declarations.forEachIndexed { index, declaration ->
            positions.putIfAbsent(declaration, index)

            (declaration as? KoParentProvider)
                ?.parents(indirectParents = false)
                ?.forEach { directChildren.getOrPut(it.sourceDeclaration) { mutableListOf() } += declaration }
        }
    }

    /**
     * Returns declarations having the given source declaration as a parent.
     */
    fun getChildren(
        sourceDeclaration: Any?,
        indirectChildren: Boolean,
    ): List<KoChildDeclaration> {
        val children = directChildren[sourceDeclaration].orEmpty()

        if (!indirectChildren) {
            return children
        }

        val allChildren = LinkedHashSet<KoChildDeclaration>()
        val queue = ArrayDeque(children)

        while (queue.isNotEmpty()) {
            val child = queue.removeFirst()

            if (allChildren.add(child)) {
                queue += directChildren[child].orEmpty()
            }
        }

        return allChildren.sortedBy { positions[it] }
    }
}
