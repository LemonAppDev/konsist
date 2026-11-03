package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal class CircularDependencyDependenciesRule : LayerDependenciesRule {
    override fun validate(layerDependencies: Set<LayerDependency>) {
        requireNoCircularDependencies(layerDependencies)
    }

    private fun requireNoCircularDependencies(dependencies: Set<LayerDependency>) {
        val dependencyGraph = buildDependencyGraph(dependencies)
        val visited = mutableSetOf<Layer>()

        dependencyGraph.keys.forEach { layer ->
            if (!visited.contains(layer)) {
                val cycle = findCycle(layer, dependencyGraph, visited, mutableListOf())

                if (cycle != null) {
                    throw KoPreconditionFailedException(formatCircularDependencyMessage(cycle))
                }
            }
        }
    }

    private fun buildDependencyGraph(dependencies: Set<LayerDependency>): Map<Layer, Set<Layer>> =
        dependencies
            .filter { it.dependencyType == LayerDependencyType.DEPENDS_ON_LAYER && it.layer2 != null }
            .groupBy { it.layer1 }
            .mapValues { (_, deps) -> deps.mapNotNull { it.layer2 }.toSet() }

    /**
     * Depth-first search keeping the current path. A dependency on a layer already present in the path closes a cycle,
     * which may not contain the start layer, e.g. start 'A', path 'A' -> 'B' -> 'C' -> 'B' gives cycle 'B' -> 'C' -> 'B'.
     *
     * @return the cycle with the same layer at start and end, or null if no cycle is reachable from [layer]
     */
    private fun findCycle(
        layer: Layer,
        graph: Map<Layer, Set<Layer>>,
        visited: MutableSet<Layer>,
        path: MutableList<Layer>,
    ): List<Layer>? {
        val indexInPath = path.indexOf(layer)

        if (indexInPath != -1) {
            return path.subList(indexInPath, path.size) + layer
        }

        if (!visited.add(layer)) {
            return null
        }

        path.add(layer)

        graph[layer]?.forEach { dependentLayer ->
            findCycle(dependentLayer, graph, visited, path)?.let { return it }
        }

        path.removeAt(path.lastIndex)
        return null
    }

    private fun formatCircularDependencyMessage(cycle: List<Layer>): String {
        if (cycle.size < MINIMUM_CYCLE_SIZE) {
            throw KoInternalException("Cycle must have at least $MINIMUM_CYCLE_SIZE layers.")
        }

        return when (cycle.size) {
            DIRECT_CIRCULAR_DEPENDENCY_SIZE -> {
                // Direct circular dependency between two layers (cycle contains same layer at start/end)
                "Circular dependency detected: '${cycle[0].name}' <-> '${cycle[1].name}'."
            }

            else -> {
                // Indirect circular dependency involving multiple layers
                val cycleString =
                    buildString {
                        append(cycle.dropLast(1).joinToString(" -> ") { "'${it.name}'" })
                        append(" -> '${cycle[0].name}'") // Add the first layer again at the end to complete the cycle
                    }
                "Circular dependency detected: $cycleString."
            }
        }
    }

    companion object {
        private const val MINIMUM_CYCLE_SIZE = 2
        private const val DIRECT_CIRCULAR_DEPENDENCY_SIZE = 3
    }
}
