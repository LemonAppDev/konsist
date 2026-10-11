package com.lemonappdev.konsist.core.util

import java.util.concurrent.ConcurrentHashMap

internal object ClasspathUtil {
    private val supertypes = ConcurrentHashMap<String, List<Supertype>>()

    /*
     * Kotlin built-in types that are compiled to Java types, so their classes can't be loaded by the Kotlin name.
     * Kotlin type aliases (e.g. `kotlin.RuntimeException`) and mutable collections are mapped first
     * by `TypeUtil.getKotlinTypeQualifiedName`.
     */
    private val kotlinBuiltInClasses: Map<String, Class<*>> =
        mapOf(
            "kotlin.Any" to Any::class.java,
            "kotlin.Annotation" to Annotation::class.java,
            "kotlin.Array" to Array<Any>::class.java,
            "kotlin.Boolean" to Boolean::class.javaObjectType,
            "kotlin.BooleanArray" to BooleanArray::class.java,
            "kotlin.Byte" to Byte::class.javaObjectType,
            "kotlin.ByteArray" to ByteArray::class.java,
            "kotlin.Char" to Char::class.javaObjectType,
            "kotlin.CharArray" to CharArray::class.java,
            "kotlin.CharSequence" to CharSequence::class.java,
            "kotlin.Cloneable" to Cloneable::class.java,
            "kotlin.Comparable" to Comparable::class.java,
            "kotlin.Double" to Double::class.javaObjectType,
            "kotlin.DoubleArray" to DoubleArray::class.java,
            "kotlin.Enum" to Enum::class.java,
            "kotlin.Float" to Float::class.javaObjectType,
            "kotlin.FloatArray" to FloatArray::class.java,
            "kotlin.Int" to Int::class.javaObjectType,
            "kotlin.IntArray" to IntArray::class.java,
            "kotlin.Long" to Long::class.javaObjectType,
            "kotlin.LongArray" to LongArray::class.java,
            "kotlin.Nothing" to Nothing::class.java,
            "kotlin.Number" to Number::class.java,
            "kotlin.Short" to Short::class.javaObjectType,
            "kotlin.ShortArray" to ShortArray::class.java,
            "kotlin.String" to String::class.java,
            "kotlin.Throwable" to Throwable::class.java,
            "kotlin.Throws" to Throws::class.java,
            "kotlin.jvm.JvmRepeatable" to JvmRepeatable::class.java,
            "kotlin.collections.Collection" to Collection::class.java,
            "kotlin.collections.Iterable" to Iterable::class.java,
            "kotlin.collections.Iterator" to Iterator::class.java,
            "kotlin.collections.List" to List::class.java,
            "kotlin.collections.ListIterator" to ListIterator::class.java,
            "kotlin.collections.Map" to Map::class.java,
            "kotlin.collections.Map.Entry" to Map.Entry::class.java,
            "kotlin.collections.MutableMap.MutableEntry" to MutableMap.MutableEntry::class.java,
            "kotlin.collections.Set" to Set::class.java,
        )

    /*
     * The class is loaded without initialization, so static initializers of the analyzed code are not executed.
     * Nested classes are tried with `$` separators, e.g. `com.Outer.Inner` is loaded as `com.Outer$Inner`.
     */
    internal fun loadClass(fullyQualifiedName: String): Class<*>? {
        val qualifiedName = TypeUtil.getKotlinTypeQualifiedName(fullyQualifiedName)

        kotlinBuiltInClasses[qualifiedName]?.let { return it }

        var binaryName = qualifiedName

        while (true) {
            runCatching { Class.forName(binaryName, false, ClasspathUtil::class.java.classLoader) }
                .getOrNull()
                ?.let { return it }

            val lastDotIndex = binaryName.lastIndexOf('.')
            if (lastDotIndex == -1) return null
            binaryName = binaryName.replaceRange(lastDotIndex, lastDotIndex + 1, "$")
        }
    }

    /*
     * Direct and indirect supertypes of the class read from the classpath, starting from the closest ones.
     * `Any` (`java.lang.Object`) is skipped, because it is not a parent in Konsist (like in the Kotlin code).
     * Empty when the class is not on the classpath.
     */
    internal fun getSupertypes(fullyQualifiedName: String): List<Supertype> =
        supertypes.computeIfAbsent(fullyQualifiedName) {
            val clazz = loadClass(it) ?: return@computeIfAbsent emptyList()

            val allSupertypes = LinkedHashSet<Class<*>>()
            val queue = ArrayDeque(getDirectSupertypes(clazz))

            while (queue.isNotEmpty()) {
                val supertype = queue.removeFirst()

                if (allSupertypes.add(supertype)) {
                    queue += getDirectSupertypes(supertype)
                }
            }

            allSupertypes.mapNotNull { supertype -> getSupertype(supertype) }
        }

    private fun getDirectSupertypes(clazz: Class<*>): List<Class<*>> =
        listOfNotNull(clazz.superclass?.takeIf { it != Any::class.java }) + clazz.interfaces

    /*
     * Java types are named like in Kotlin, e.g. `java.lang.Throwable` is `kotlin.Throwable`
     * and `java.lang.RuntimeException` is `kotlin.RuntimeException`.
     */
    private fun getSupertype(clazz: Class<*>): Supertype? {
        val qualifiedName = clazz.kotlin.qualifiedName ?: return null

        // Name of the nested type includes the names of the outer types, e.g. `Map.Entry`
        val name =
            generateSequence(clazz) { it.enclosingClass }
                .toList()
                .asReversed()
                .joinToString(".") { it.simpleName }

        return Supertype(name, TypeUtil.getKotlinTypeAliasName(qualifiedName))
    }

    internal class Supertype(
        val name: String,
        val fullyQualifiedName: String,
    )
}
