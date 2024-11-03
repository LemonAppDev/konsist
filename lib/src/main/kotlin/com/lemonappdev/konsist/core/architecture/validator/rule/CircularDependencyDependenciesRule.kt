package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal class CircularDependencyDependenciesRule : LayerDependenciesRule {
    override fun validate(dependencies: Set<LayerDependency>) {
        requireNoCircularDependencies(dependencies)
    }

    private fun requireNoCircularDependencies(dependencies: Set<LayerDependency>) {
        val dependencyGraph = buildDependencyGraph(dependencies)
        val visited = mutableSetOf<Layer>()
        val recursionStack = mutableSetOf<Layer>()

        dependencyGraph.keys.forEach { layer ->
            if (hasCycle(layer, dependencyGraph, visited, recursionStack)) {
                val cycle = findCycle(layer, dependencyGraph)
                throw KoPreconditionFailedException(
                    "Circular dependency detected: ${formatSimpleCycle(cycle)}",
                )
            }
        }
    }

    private fun buildDependencyGraph(dependencies: Set<LayerDependency>): Map<Layer, Set<Layer>> =
        dependencies
            .filter { it.dependencyType == LayerDependencyType.DEPEND_ON_LAYER && it.layer2 != null }
            .groupBy { it.layer1 }
            .mapValues { (_, deps) -> deps.mapNotNull { it.layer2 }.toSet() }

    private fun hasCycle(
        layer: Layer,
        graph: Map<Layer, Set<Layer>>,
        visited: MutableSet<Layer>,
        recursionStack: MutableSet<Layer>,
    ): Boolean {
        if (recursionStack.contains(layer)) {
            return true
        }

        if (visited.contains(layer)) {
            return false
        }

        visited.add(layer)
        recursionStack.add(layer)

        graph[layer]?.forEach { dependentLayer ->
            if (hasCycle(dependentLayer, graph, visited, recursionStack)) {
                return true
            }
        }

        recursionStack.remove(layer)

        return false
    }

    private fun findCycle(
        start: Layer,
        graph: Map<Layer, Set<Layer>>,
    ): List<Layer> {
        val path = mutableListOf<Layer>()
        val visited = mutableSetOf<Layer>()
        findCycleDFS(start, start, graph, path, visited)

        return path
    }

    private fun findCycleDFS(
        current: Layer,
        target: Layer,
        graph: Map<Layer, Set<Layer>>,
        path: MutableList<Layer>,
        visited: MutableSet<Layer>,
    ): Boolean {
        path.add(current)
        visited.add(current)

        graph[current]?.forEach { next ->
            if (next == target && path.size > 1) {
                path.add(next)
                return true
            }
            if (!visited.contains(next) && findCycleDFS(next, target, graph, path, visited)) {
                return true
            }
        }

        path.removeAt(path.lastIndex)

        return false
    }

    private fun formatSimpleCycle(cycle: List<Layer>): String =
        when {
            isSimpleTwoLayerCycle(cycle) -> "${cycle[0].name}$CIRCULAR_DEPENDENCY_SEPARATOR${cycle[1].name}"
            else -> cycle.dropLast(1).joinToString(CIRCULAR_DEPENDENCY_SEPARATOR) { it.name }
        }

    private fun isSimpleTwoLayerCycle(cycle: List<Layer>): Boolean = cycle.size == SIMPLE_CYCLE_SIZE && cycle.first() == cycle.last()

    private companion object {
        private const val SIMPLE_CYCLE_SIZE = 3
        private const val CIRCULAR_DEPENDENCY_SEPARATOR = " <-> "
    }
}
